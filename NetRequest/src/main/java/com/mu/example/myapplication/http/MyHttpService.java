package com.mu.example.myapplication.http;

import com.mu.example.myapplication.bean.CommonBean;
import com.mu.example.myapplication.bean.LoginBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by mu on 2018/1/5.
 */

public interface MyHttpService {
    class Builder {
        /**
         * @return
         */
        public static MyHttpService getHttpServer() {
            return HttpUtils.getInstance().getServer(MyHttpService.class);
        }
    }

    @FormUrlEncoded
    @POST(URLUtils.LOGIN)
    Observable<CommonBean<LoginBean>> login
            (@Field("adminUserName") String storeName,
             @Field("userName") String username
            , @Field("password") String password

            , @Field("deviceType") String deviceType
            , @Field("deviceName") String deviceName
            , @Field("MAC") String MAC

            , @Field("DeviceSN") String DeviceSN
            , @Field("Remark") String Remark
            , @Field("DeviceInfo") String DeviceInfo
            , @Field("IP") String IP);
}
