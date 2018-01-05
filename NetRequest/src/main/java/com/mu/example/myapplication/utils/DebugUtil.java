package com.mu.example.myapplication.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by mu on 2018/1/5.
 */

public class DebugUtil {
    public static final boolean DEBUG = true;

    //toast弹窗提示
    public static void ToastShort(Context context, String content) {
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
    }

    public static void d(String tag, String msg) {
        if (DEBUG) {
            Log.d(tag, msg);
        }
    }
}
