package com.mu.example.myapplication.http;

import android.support.compat.BuildConfig;

import com.mu.example.myapplication.utils.DebugUtil;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mu on 2018/1/5.
 */

public class HttpUtils {
    private static HttpUtils instance;
    private Object https;

    public static HttpUtils getInstance() {
        if (instance == null) {
            synchronized (HttpUtils.class) {
                if (instance == null) {
                    instance = new HttpUtils();
                }
            }
        }
        return instance;
    }

    /**
     * 01
     *
     * @param clz
     * @param <T>
     * @return
     */
    public <T> T getServer(Class<T> clz) {
        if (https == null) {
            synchronized (HttpUtils.class) {
                https = getRetrofitBuilder(URLUtils.API_BASE_URL).build().create(clz);
            }
        }
        return (T) https;
    }

    /**
     * retrofit配置 方式2
     *
     * @param apiUrl
     * @return
     */
    private Retrofit.Builder getRetrofitBuilder(String apiUrl) {

        //retrofit配置 可用链式结构
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.client(getOkhttp3Client());//设置okhttp3（重点），不设置走默认的
        builder.baseUrl(apiUrl);//设置远程地址
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create()); //Rx
        return builder;
    }

    //okhttp配置
    public OkHttpClient getOkhttp3Client() {
        OkHttpClient client1;
        //        client1 = getUnsafeOkHttpClient();//（https）
        client1 = getUnsafeOkHttpClient2();//（http）
        return client1;
    }

    //okhttp配置 修正（http）
    public OkHttpClient getUnsafeOkHttpClient2() {
        OkHttpClient.Builder okBuilder = new OkHttpClient.Builder();
        okBuilder.readTimeout(20, TimeUnit.SECONDS);
        okBuilder.writeTimeout(20, TimeUnit.SECONDS);
        okBuilder.connectTimeout(10, TimeUnit.SECONDS);
        okBuilder.addInterceptor(getInterceptor());
        okBuilder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession sslSession) {
                DebugUtil.d("HttpUtils", "hostname: " + hostname);
                return true;
            }
        });
        return okBuilder.build();
    }

    private boolean debug;//判断 app版本，由application设置

    private HttpLoggingInterceptor getInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        //可以通过 setLevel 改变日志级别,共包含四个级别：NONE、BASIC、HEADER、BODY
        /**
         * NONE 不记录
         * BASIC 请求/响应行
         * HEADERS 请求/响应行 + 头
         * BODY 请求/响应行 + 头 + 体
         */
        if (BuildConfig.DEBUG) {
            // 打印okhttp
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY); // 测试
        } else {
            interceptor.setLevel(HttpLoggingInterceptor.Level.NONE); // 打包
        }
        return interceptor;
    }
}
