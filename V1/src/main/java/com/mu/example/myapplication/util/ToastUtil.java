package com.mu.example.myapplication.util;

import android.widget.Toast;

import com.mu.example.myapplication.App;

/**
 * Created by mu on 2018/2/5.
 */

public class ToastUtil {
    public static void toastShort(String msg) {
        Toast.makeText(App.getApplication(), msg, Toast.LENGTH_SHORT).show();
    }
}
