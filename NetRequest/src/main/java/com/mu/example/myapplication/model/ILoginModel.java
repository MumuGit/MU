package com.mu.example.myapplication.model;

/**
 * Created by mu on 2018/1/5.
 */

public interface ILoginModel {
    void mLogin(String storeName, String userName, String passWord, String IP, OnLoginListener listener);
}
