package com.mu.example.myapplication.util;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.telephony.TelephonyManager;

import com.google.gson.Gson;
import com.mu.example.myapplication.App;
import com.mu.example.myapplication.core.net.IApi;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mu on 2018/1/16.
 */

public class HttpUtil {
    private IApi mApiImp;
    private static final String POST = "POST";
    private static final String GET = "GET";
    public static String appType = "biz";
    private Gson mGson;
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private HttpUtil() {
        mApiImp = getRetrofitBuilder().build().create(IApi.class);
        mGson = new Gson();
    }

    private static class Holder {
        private static final HttpUtil INSTANCE = new HttpUtil();
    }

    private Retrofit.Builder getRetrofitBuilder() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(IApi.BASE_URL).client(getOkHttpClient()).
                addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        return builder;
    }

    private OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);
        builder.connectTimeout(10, TimeUnit.SECONDS);
        builder.addInterceptor(new ParamInterceptor());
        return builder.build();
    }


    private static Map<String, String> getPublicParams() {
        HashMap<String, String> map = new LinkedHashMap<String, String>();
        map.put("app_type", appType);
        map.put("app_version", getVersionName());
        map.put("device_platform", "android");
        map.put("device_number", getDeviceIDAndDeviceName()[0]);
        map.put("random", getRandom(4));
        map.put("timestamp", getTimestamp());
        return map;
    }

    private static String getTimestamp() {
        long time = System.currentTimeMillis();
        return String.valueOf(time).substring(0, 10);
    }

    private static String getRandom(int n) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int tem = (int) Math.random() * 10;
            stringBuilder.append(tem);
        }
        return stringBuilder.toString();
    }

    private static String[] getDeviceIDAndDeviceName() {
        String[] result = new String[2];
        TelephonyManager telephonyManager = (TelephonyManager) App.getApplication().
                getSystemService(Context.TELEPHONY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            if (ActivityCompat.checkSelfPermission(App.getApplication(),
//                    Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
//            }
            result[0] = telephonyManager.getImei();
        } else {
            result[0] = telephonyManager.getDeviceId();
        }
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        result[1] = bluetoothAdapter.getName();
        return result;
    }

    private static String getVersionName() {
        String packageName = App.getApplication().getPackageName();
        String versionName = null;
        try {
            versionName = App.getApplication().getPackageManager().getPackageInfo(packageName, 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }

    class ParamInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            String method = request.method();
            HashMap newParams = null;
            if (POST.equalsIgnoreCase(method)) {
                RequestBody body = request.body();
                if (body instanceof FormBody) {
                    FormBody.Builder formBuilder = new FormBody.Builder();

                } else {
                    Buffer buffer = new Buffer();
                    body.writeTo(buffer);
                    String oldParamsJson = buffer.readUtf8();
                    newParams = mGson.fromJson(oldParamsJson, HashMap.class);
                    if (newParams == null) {
                        newParams = new HashMap();
                    }
                    newParams.putAll(getPublicParams());
                    String newJsonParams = mGson.toJson(newParams);
                    request = request.newBuilder().post(RequestBody.create(JSON,
                            newJsonParams)).build();
                }
            }
            return chain.proceed(request);
        }
    }

    public static IApi getApi() {
        return Holder.INSTANCE.mApiImp;
    }
}
