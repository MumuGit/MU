package com.mu.example.myapplication;

import android.app.Application;

/**
 * Created by mu on 2018/1/17.
 */

public class App extends Application {
    private static Application mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
    }

    public static Application getApplication() {
        return mApplication;
    }
}
