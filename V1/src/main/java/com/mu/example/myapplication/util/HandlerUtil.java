package com.mu.example.myapplication.util;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by mu on 2018/2/8.
 */

public class HandlerUtil {
    private Handler mHandler;

    private HandlerUtil() {
        mHandler = new Handler(Looper.getMainLooper());
    }

    public void postTask(Runnable runnable, long mills) {
        mHandler.postDelayed(runnable, mills);
    }

    public void postTask(Runnable runnable) {
        postTask(runnable, 0);
    }

    private static class Holder {
        private final static HandlerUtil INSTANCE = new HandlerUtil();
    }

    public static HandlerUtil getInstance() {
        return Holder.INSTANCE;
    }
}
