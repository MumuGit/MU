package com.mu.example.myapplication;

import android.app.Application;
import android.content.Context;

/**
 * Created by mu on 2018/1/11.
 */

public class App extends Application {
    public static Context getApplication() {
        return applictionContext;
    }

    static Context applictionContext;

    @Override

    public void onCreate() {
        super.onCreate();
        applictionContext = getApplicationContext();
    }
}
