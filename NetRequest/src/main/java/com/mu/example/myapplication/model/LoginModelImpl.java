package com.mu.example.myapplication.model;

import com.mu.example.myapplication.bean.CommonBean;
import com.mu.example.myapplication.bean.LoginBean;
import com.mu.example.myapplication.http.MyHttpService;
import com.mu.example.myapplication.utils.DebugUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by mu on 2018/1/5.
 */

public class LoginModelImpl implements ILoginModel {
    static final String TAG = "login";

    @Override
    public void mLogin(String storeName, String userName, String passWord, String IP, final OnLoginListener listener) {
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
                , "")
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CommonBean<LoginBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(CommonBean<LoginBean> bean) {
                        if (bean.getCode().equals("1")) {
                            //code = 1
                            listener.onLoginSuccess(bean.getResult());
                        } else if (bean.getCode().equals("0")) {
                            //code = 0处理
                            listener.onLoginFailed(bean.getMessage(), new Exception("登陆失败"));
                        } else {

                        }
                    }


                    @Override
                    public void onError(Throwable e) {
                        listener.onLoginFailed("获取数据异常", (Exception) e);
                    }

                    @Override
                    public void onComplete() {
                        DebugUtil.d(TAG, "LoginModelImpl--onCompleted");
                    }
                });
    }
}
