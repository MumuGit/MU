package com.mu.example.myapplication.okhttp3.internal;

/**
 * Created by mu on 2018/3/14.
 */

import android.telecom.Call;

import com.mu.example.myapplication.okhttp3.Address;

import java.net.MalformedURLException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.net.ssl.SSLSocket;

/**
 * Escalate internal APIs in {@code okhttp3} so they can be used from OkHttp's implementation
 * packages. The only implematation of this interface is in {@link OkHttpClient}.
 */
public abstract class Internal {
    public static void initializeInstanceForTests() {
        // Needed in tests to ensure that the instance is actually pointing to something.
        new OkHttpClient();
    }

    public static Internal instance;

    public abstract void addLenient(Headers.Builder builder, String line);

    public abstract void addLenient(Headers.Builder builder, String name, String value);

    public abstract void setCache(OkHttpClient.Builder builder, InternalCache internalCache);

    public abstract RealConnection get(ConnectioPool pool, Address address,
                                       StreamAllocation streamAllocation, Route route);

    public abstract boolean equalsNonHost(Address a, Address b);

    public abstract Socket deduplicate(ConnectionPool pool, Address address, StreamAllocation streamAllocation);

    public abstract void put(ConnectionPool pool, RealConnection connection);

    public abstract boolean connectionBecameIdle(ConnectionPool pool, RealConnection connection);

    public abstract RouteDatabase routeDatabase(ConnectionPool connectionPool);

    public abstract int code(Response.Builder reponseBuilder);

    public abstract void apply(ConnectionSpec tlsConfiguration, SSLSocket sslSocket, boolean isFallback);

    public abstract HttpUrl getHttpUrlChecked(String url) throws
            MalformedURLException, UnknownHostException;

    public abstract StreamAllocation streamAllocation(Call call);

    public abstract Call newWebSocketCall(OkHttpClient client, Request request);
}
