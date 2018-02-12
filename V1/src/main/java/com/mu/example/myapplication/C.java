package com.mu.example.myapplication;

import android.Manifest;

/**
 * Created by mu on 2018/2/7.
 */

public final class C {
    public static final class App {

        volatile public static boolean WAIT_ID = true;
    }

    public static final class Request {

        public static String[] PUBLIC_PARAM = {Manifest.permission.READ_PHONE_STATE};
        public static int REQUEST_CODE_PUBLIC_PARAM = 0;

    }

}
