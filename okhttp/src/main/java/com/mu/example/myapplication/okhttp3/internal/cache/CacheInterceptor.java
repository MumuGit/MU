package com.mu.example.myapplication.okhttp3.internal.cache;

/**
 * Created by mu on 2018/3/15.
 */

import com.mu.example.myapplication.okhttp3.Headers;
import com.mu.example.myapplication.okhttp3.Interceptor;
import com.mu.example.myapplication.okhttp3.Request;
import com.mu.example.myapplication.okhttp3.Response;
import com.mu.example.myapplication.okhttp3.internal.Internal;
import com.mu.example.myapplication.okhttp3.internal.Util;
import com.mu.example.myapplication.okhttp3.internal.http.HttpCodec;
import com.mu.example.myapplication.okhttp3.internal.http.HttpHeaders;
import com.mu.example.myapplication.okhttp3.internal.http.HttpMethod;
import com.mu.example.myapplication.okhttp3.internal.http.RealResponseBody;

import java.io.IOException;
import java.nio.Buffer;

import javax.xml.transform.Source;

/**
 * Servers requests from the cache and writes responses to the
 */
public final class CacheInterceptor implements Interceptor {
    final InternalCache cache;

    public CacheInterceptor(InternalCache cache) {
        this.cache = cache;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response cacheCandidate = cache != null ? cache.get(chain.request()) : null;
        long now = System.currentTimeMillis();
        CacheStrategy strategy = new CacheStrategy.Factory(now, chain.request(), cacheCandidate).get();
        Request networkRequest = strategy.networkRequest;
        Response cacheResponse = strategy.cacheResponse;
        if (cache != null) {
            cache.trackResponse(strategy);
        }
        if (cacheCandidate != null && cacheResponse == null) {
            closeQuietly(cacheCandidate.body());// The cache candidate wasn't applicable. Close it.
        }
        //If we're forbidden from using the network and the cache is insufficient,fail.
        if (networkRequest == null && cacheResponse == null) {
            return new Response.Builder().request(chain.request())
                    .protocol(Protocol.HTTP_1_1)
                    .code(504)
                    .message("Unsatisfiable Request (only-if-cached)")
                    .body(Util.EMPTY_RESPONSE)
                    .sentRequestAtMillis(-1L)
                    .receivedResponseAtMillis(System.currentTimeMillis())
                    .build();
        }
        // If we don't need the network, we're done.
        if (networkRequest == null) {
            return cacheResponse.newBuilder().cacheResponse
                    (stripBody(cacheResponse))
                    .build();
        }
        Response networkResponse = null;
        try {
            networkResponse = chain.proceed(networkRequest);
        } finally {
            // If we're crashing on I/O or otherwise, don't leak the cache body.
            if (networkResponse == null && cacheCandidate != null) {
                closeQuietly(cacheCandidate.body());
            }
        }
        // If we have a cache response too, then we're doing a conditional get.
        if (cacheResponse != null) {
            if (networkResponse.code() == HTTP_NOT_MODIFIED) {
                Response response = cacheResponse.newBuilder()
                        .headers(combine(cacheResponse.headers(), networkResponse.headers()))
                        .sentRequestAtMillis(networkResponse.sentRequestAtMillis())
                        .recievedResponseAtMillis(networkResponse.recievedResponseAtMillis())
                        .cacheResponse(stripBody(cacheResponse))
                        .networkResponse(stripBody(networkResponse))
                        .build();
                networkResponse.body().close();
                // Update the cache after combining headers but before stripping the
                // Content-Encoding header (as performed by initContentStream()).
                cache.trackConditionalCacheHit();
                cache.update(cacheResponse, cache);
                return response;
            } else {
                closeQuietly(cacheResponse.body());
            }
        }
        Response response = networkResponse.newBuilder()
                .cacheResponse(stripBody(cacheResponse))
                .networkResponse(stripBody(networkResponse))
                .build();
        if (cache != null) {
            if (HttpHeaders.hasBody(response) && CacheStrategy.isCacheable(response, networkRequest)) {
                // Offer this request to the cache.
                CacheRequest cacheRequest = cache.put(response);
                return cacheWritingResponse(cacheRequest, response);
            }
            if (HttpMethod.invalidatesCache(networkRequest.method())) {
                try {
                    cache.remove(networkRequest);
                } catch (IOException ignored) {
                    // The cache cannot be written.
                }
            }
        }
        return response;
    }

    private static Response stripBody(Response response) {
        return response != null && response.body() != null ?
                response.newBuilder().body(null).build() : response;
    }

    /**
     * Returns a new source that writes bytes to {@code cacheRequest} as they
     * are read by the source consumer.This is careful to discard bytes left over
     * when the steam is closed; otherwise we may never exhaust the source steam
     * and therefore not complete the cached response.
     */
    private Response cacheWritingResponse(final CacheRequest cacheRequest, Response response) throws
            IOException {
        // Some apps return a null body; for compatibility we treat that like a null cache request.
        if (cacheRequest == null) return response;
        Sink cacheBodyUnbuffered = cacheRequest.body();
        if (cacheBodyUnbuffered == null) return response;
        final BufferedSource source = response.body().source();
        final BufferedSink cacheBody = Okio.buffer(cacheBodyUnbuffered);
        Source cacheWritingSource = new Source() {
            boolean cacheRequestClosed;

            @Override
            public long read(Buffer sink, long byteCount) throws IOException {
                long bytesRead;
                try {
                    bytesRead = source.read(sink, byteCount);
                } catch (IOException e) {
                    if (!cacheRequestClosed) {
                        cacheRequestClosed = true;
                        cacheRequest.abort();// Failed to write a complete cache response.
                    }
                    throw e;
                }
                if (bytesRead == -1) {
                    if (!cacheRequestClosed) {
                        cacheRequestClosed = true;
                        cacheBody.close(); // The cache response is complete!
                    }
                    return -1;
                }
                sink.copyTo(cacheBody.buffer(), sink.size() - bytesRead, bytesRead);
                cacheBody.emitCompleteSegments();
                return bytesRead;
            }

            @Override
            public Timeout timeout() {
                return source.timeout();
            }

            @Override
            public void close() throws IOException {
                if (!cacheRequestClosed && !discard(this, HttpCodec.DISCARD_STREAM_TIMEOUT_MILLIS, MILLISECONDS)) {
                    cacheRequestClosed = true;
                    cacheRequest.abort();
                }
                source.close();
            }

        }
        String contentType = response.header("Content-Type");
        long contentLength = response.body().contentLength();
        return response.newBuilder().body(new RealResponseBody(contentType, contentLength, Okio.
                buffer(cacheWritingSource))).build();
    }

    /**
     * Combines cached headers with a network headers as defined by RFC 7234, 4.3.4.
     */
    private static Headers combine(Headers cachedHeaders, Headers networkHeaders) {
        Headers.Builder result = new Headers.Builder();
        for (int i = 0, size = cachedHeaders.size(); i < size; i++) {
            String fieldName = cachedHeaders.name(i);
            String value = cachedHeaders.value(i);
            if ("Warning".equalsIgnoreCase(fieldName) && value.startsWith("1")) {
                continue;// Drop 100-level freshness warnings.
            }
            if (isContentSpecificHeader(fieldName) || !isEndToEnd(fieldName) || networkHeaders.get(fieldName) == null) {
                Internal.instance.addLenient(result, fieldName, value);
            }
        }
        for (int i = 0, size = networkHeaders.size(); i < size; i++) {
            String fieldName = networkHeaders.name(i);
            if (!isContentSpecificHeader(fieldName) && isEndToEnd(fieldName)) {
                Internal.instance.addLenient(result, fieldName, networkHeaders.value(i));
            }
        }
        return result.build();
    }

    /**
     * Returns true if {@code fieldName} is an end-to-end HTTP header, as defined by RFC 2616,
     * 13.5.1.
     */
    static boolean isEndToEnd(String fieldName) {
        return !"Connection".equalsIgnoreCase(fieldName)
                && !"Keep-Alive".equalsIgnoreCase(fieldName)
                && !"Proxy-Authenticate".equalsIgnoreCase(fieldName)
                && !"Proxy-Authorization".equalsIgnoreCase(fieldName)
                && !"TE".equalsIgnoreCase(fieldName)
                && !"Trailers".equalsIgnoreCase(fieldName)
                && !"Transfer-Encoding".equalsIgnoreCase(fieldName)
                && !"Upgrade".equalsIgnoreCase(fieldName);
    }


    /**
     * Returns true if {@code fieldName} is content specific and therefore should always be used
     * from cached headers.
     */
    static boolean isContentSpecificHeader(String fieldName) {
        return "Content-Length".equalsIgnoreCase(fieldName)
                || "Content-Encoding".equalsIgnoreCase(fieldName)
                || "Content-Type".equalsIgnoreCase(fieldName);
    }

}
