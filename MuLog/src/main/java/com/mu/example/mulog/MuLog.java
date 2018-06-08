package com.mu.example.mulog;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

public class MuLog {
    public static void init() {
        Logger.addLogAdapter(new AndroidLogAdapter());
    }
}
