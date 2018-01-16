package com.mu.example.myapplication.util;

import com.mu.example.myapplication.core.net.IApi;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mu on 2018/1/16.
 */

public class HttpUtil {
    private Object mApiImp;

    private HttpUtil() {
        mApiImp = getRetrofitBuilder().build().create(IApi.class);
    }

    private static class Holder {
        private static final HttpUtil INSTANCE = new HttpUtil();
    }

    private Retrofit.Builder getRetrofitBuilder() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(IApi.BASE_URL).client(getOkHttpClient()).
                addConverterFactory(GsonConverterFactory.create());
        return builder;
    }

    private OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);
        builder.connectTimeout(10, TimeUnit.SECONDS);
        return builder.build();
    }

    public static Object getApi() {
        return Holder.INSTANCE.mApiImp;
    }
}
