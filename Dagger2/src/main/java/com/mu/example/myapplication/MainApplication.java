package com.mu.example.myapplication;

import android.app.Application;

/**
 * Created by mu on 2018/1/4.
 */

public class MainApplication extends Application {
    private ApplicationComponent mApplicationComponent;
    private static MainApplication sApplication;
    private AComponent mAComponent;

    public static MainApplication getInstance() {
        return sApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;

        mApplicationComponent = DaggerApplicationComponent.builder().build();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    public AComponent getAComponent() {
        if (mAComponent == null) {
            mAComponent = mApplicationComponent.plus(new AModule());
        }
        return mAComponent;
    }
}
