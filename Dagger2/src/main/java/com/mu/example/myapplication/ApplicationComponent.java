package com.mu.example.myapplication;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by mu on 2018/1/4.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    Gson getGson();// 暴露Gson对象接口

    //AComponent plus();
    AComponent plus(AModule module);//添加声明
}
