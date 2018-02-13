package com.mu.example.myapplication.util;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.mu.example.myapplication.App;
import com.mu.example.myapplication.C;
import com.mu.example.myapplication.core.net.IApi;
import com.mu.example.myapplication.core.permission.PermissionUtil;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
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
            int tem = (int) (Math.random() * 10);
            stringBuilder.append(tem);
        }
        return stringBuilder.toString();
    }


    private static String[] getDeviceIDAndDeviceName() {
        final TelephonyManager telephonyManager = (TelephonyManager) App.getApplication().
                getSystemService(Context.TELEPHONY_SERVICE);
        String[] result = new String[2];
        final String[] id = {null};
        PermissionUtil.permission(C.Request.PUBLIC_PARAM).
                success(new PermissionUtil.IPermissionSuccess() {
                    @SuppressLint({"MissingPermission"})
                    @Override
                    public void onSuccess() {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            id[0] = telephonyManager.getImei();
                        } else {
                            id[0] = telephonyManager.getDeviceId();
                        }
                        if (TextUtils.isEmpty(id[0])) {
                            id[0] = "000000000000000";
                        }
                        C.App.WAIT_ID = false;
                    }
                }).fail(new PermissionUtil.IPermissionFail() {
            @SuppressLint("MissingPermission")
            @Override
            public void onFail(String refusePermission) {
                id[0] = "000000000000000";
                C.App.WAIT_ID = false;
            }
        }).explain("请交出权限").code(C.Request.REQUEST_CODE_PUBLIC_PARAM).request();
        while (C.App.WAIT_ID) {

        }
        C.App.WAIT_ID = true;
        result[0] = id[0];
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null) {
            result[1] = "";
        } else {
            result[1] = bluetoothAdapter.getName();
        }
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

    public static Map<String, String> getAllParam(Map<String, String> params) {
        Map<String, String> map = getPublicParams();
        String random = map.get("random");
        map.put("random", encryptToSHA1(random));
        for (Map.Entry<String, String> entry : params.entrySet()) {
            map.put(entry.getKey(), entry.getValue());
        }
        String sign = signGenerate(map);
        map.put("sign", sign);
        map.put("random", random);
        return map;
    }

    public static String signGenerate(Map<String, String> map) {
        Map<String, String> tempMap = zDSort(map);
        StringBuilder sign = new StringBuilder();

        for (Map.Entry<String, String> entry : tempMap.entrySet()) {

            sign.append("&" + entry.getKey() + "=" + entry.getValue());

        }
        sign.append("&key=7C4l7JLaM3Fq5biQurtmk9nFS");
        String s = toUpperCase(encryptToSHA1(sign.toString()));

        return s;
    }

    public static String toUpperCase(String s) {
        return s.toUpperCase();
    }

    /**
     * 字典升序排序
     *
     * @param map
     * @return
     */
    public static Map<String, String> zDSort(Map<String, String> map) {
        Map<String, String> tempMap = new LinkedHashMap<String, String>();
        Collection<String> keyset = map.keySet();

        List list = new ArrayList<String>(keyset);

        Collections.sort(list);
        //这种打印出的字符串顺序和微信官网提供的字典序顺序是一致的
        for (int i = 0; i < list.size(); i++) {
            tempMap.put((String) list.get(i), map.get(list.get(i)));
        }

        return tempMap;
    }

    /**
     * sha1加密
     *
     * @param info
     * @return
     */
    public static String encryptToSHA1(String info) {
        byte[] digesta = null;
        try {
            MessageDigest alga = MessageDigest.getInstance("SHA-1");
            alga.update(info.getBytes());
            digesta = alga.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        String rs = byte2hex(digesta);
        return rs;
    }

    public static String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
        }
        return hs;
    }


    class ParamInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            String method = request.method();
            HashMap<String, String> newParams = new HashMap();
            if (GET.equalsIgnoreCase(method)) {
                //添加公共参数
                HttpUrl httpUrl = request.url()
                        .newBuilder()
                        .addQueryParameter("clienttype", "1")
                        .addQueryParameter("imei", "imei")
                        .addQueryParameter("version", "VersionName")
                        .addQueryParameter("timestamp", String.valueOf(System.currentTimeMillis()))
                        .build();
                request = request.newBuilder().url(httpUrl).build();
            } else if (POST.equalsIgnoreCase(method)) {
                RequestBody body = request.body();
                if (body instanceof FormBody) {
                    FormBody formBody = (FormBody) body;
                    for (int i = 0; i < formBody.size(); i++) {
                        newParams.put(formBody.encodedName(i),
                                formBody.encodedValue(i));
                    }
                    newParams = (HashMap) getAllParam(newParams);
                    FormBody.Builder formBodyBuilder = new FormBody.Builder();

                    for (Map.Entry<String, String> entry : newParams.entrySet()) {
                        formBodyBuilder.addEncoded(entry.getKey(), entry.getValue());
                    }
                    request = request.newBuilder().post(formBodyBuilder.build()).build();
                } else {
                    Buffer buffer = new Buffer();
                    body.writeTo(buffer);
                    String oldParamsJson = buffer.readUtf8();
                    newParams = mGson.fromJson(oldParamsJson, HashMap.class);
                    if (newParams == null) {
                        newParams = new HashMap();
                    }
                    newParams = (HashMap) getAllParam(newParams);
                    String newJsonParams = mGson.toJson(newParams);
                    /**
                     * 这个为何请求不成功？
                     */
                    request = request.newBuilder().post(FormBody.create(JSON,
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
