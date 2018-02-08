package com.mu.example.myapplication.core.net;

import com.mu.example.myapplication.model.SMSResponse;
import com.mu.example.myapplication.model.User;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by mu on 2018/1/16.
 */

public interface IApi {
    public static String appType = "client";
    public final String BASE_URL = "https://api.517ybang.com/";
    /**
     * 短信登录
     */
    public final static String ACCOUNT_LOGIN_SMS = "account/login_sms";
    /**
     * 获取短信验证码
     */
    public final static String SMS_CREATE = "sms/create";


    /**
     * 短信登录
     */
    @POST(ACCOUNT_LOGIN_SMS)
    Observable<User> login_sms(@QueryMap Map param);

    /**
     * 获取短信验证码
     */
    @POST(SMS_CREATE)
    @FormUrlEncoded
    Observable<SMSResponse> sms_create(@FieldMap Map<String, String> param);
}
