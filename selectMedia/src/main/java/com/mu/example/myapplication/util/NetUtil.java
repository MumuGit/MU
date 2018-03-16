package com.mu.example.myapplication.util;

import android.text.TextUtils;

/**
 * Created by mu on 2018/3/16.
 */

public class NetUtil {
    public static boolean isWebUrl(String uri) throws IllegalAccessException {
        if (TextUtils.isEmpty(uri)) {
            throw new IllegalAccessException("地址为空");
        }
        if (uri.contains("http://") || uri.contains("https://")) {
            return true;
        } else {
            return false;
        }
    }
}
