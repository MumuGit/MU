package com.mu.example.myapplication.model;

import com.mu.example.myapplication.bean.LoginBean;

/**
 * Created by mu on 2018/1/5.
 */

public interface OnLoginListener {
    void onLoginSuccess(LoginBean bean);

    void onLoginFailed(String msg, Exception e);
}
