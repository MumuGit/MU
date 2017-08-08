package com.mu.example.mu;

import android.app.Application;



/**
 * Created by mu on 2017/5/24.
 */

public class App extends Application {
    private static App myApplication = null;

    public static App getApplication() {
        return myApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());

    }



}
