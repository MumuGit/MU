package com.mu.example.myapplication;

import android.app.Application;
import android.content.Context;

/**
 * Created by mu on 2018/1/11.
 */

public class MyApp extends Application {
    public static Context getApplictionContext() {
        return applictionContext;
    }

    static Context applictionContext;

    @Override

    public void onCreate() {
        super.onCreate();
        applictionContext = getApplicationContext();
    }
}
