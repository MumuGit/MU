package com.mu.example.myapplication.model;

import com.mu.example.myapplication.http.MyHttpService;

import rx.Scheduler;

/**
 * Created by mu on 2018/1/5.
 */

public class LoginModelImpl implements ILoginModel {
    @Override
    public void mLogin(String storeName, String userName, String passWord, String IP, OnLoginListener listener) {
        //除了输入的三个参数，还有几个默认的参数
        String deviceName = userName;//设备名称（使用用户名当设备名称）(必须)
        String DeviceSN = "";//系列号
        String Remark = "android";
        String DeviceInfo = "1@1001";//是否接收推送(0:不接收;1:接收) @手机类型(1001:Android;1002:IOS) (必须)
        MyHttpService.Builder.getHttpServer().login(storeName, userName
                , passWord
                , ""
                , deviceName
                , ""
                , DeviceSN
                , Remark
                , DeviceInfo
                , "").subscribeOn(Scheduler.);
    }
}
