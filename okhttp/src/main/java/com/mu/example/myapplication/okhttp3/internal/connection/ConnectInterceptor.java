package com.mu.example.myapplication.okhttp3.internal.connection;

/**
 * Created by mu on 2018/3/19.
 */

import com.mu.example.myapplication.okhttp3.Interceptor;
import com.mu.example.myapplication.okhttp3.OkHttpClient;
import com.mu.example.myapplication.okhttp3.Request;
import com.mu.example.myapplication.okhttp3.Response;
import com.mu.example.myapplication.okhttp3.internal.http.HttpCodec;
import com.mu.example.myapplication.okhttp3.internal.http.RealInterceptorChain;

import java.io.IOException;

/**
 * Opens a connection to the target server and proceeds to the next interceptor.
 */
public final class ConnectInterceptor implements Interceptor {
    public final OkHttpClient client;

    public ConnectInterceptor(OkHttpClient client) {
        this.client = client;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        RealInterceptorChain realChain = (RealInterceptorChain) chain;
        Request request = realChain.request();
        StreamAllocation streamAllocation = realChain.streamAllocation();
        // We need the network to satisfy this request. Possibly for validating a conditional GET.
        boolean doExtensiveHealthChecks = !request.method().equals("GET");
        HttpCodec httpCodec = streamAllocation.newStream(client, chain, doExtensiveHealthChecks);
        RealConnection connection = streamAllocation.connection();
        return realChain.proceed(request, streamAllocation, httpCodec, connection);
    }
}
