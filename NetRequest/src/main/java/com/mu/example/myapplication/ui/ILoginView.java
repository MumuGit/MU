package com.mu.example.myapplication.ui;

import com.mu.example.myapplication.bean.LoginBean;

/**
 * Created by mu on 2018/1/5.
 */

public interface ILoginView {
    //登录获取数据过程中显示progress
    void showProgress();

    //获取数据结束progress消失
    void hideProgress();

    void onSuccess(LoginBean bean);

    //登录失败提示
    void showDialogFailed(String msg, Exception e);
}
