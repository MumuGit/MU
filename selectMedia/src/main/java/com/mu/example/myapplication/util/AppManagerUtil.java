package com.mu.example.myapplication.util;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;

/**
 * Created by mu on 2018/3/19.
 */

public class AppManagerUtil {
    /**
     * Get activity from context object
     *
     * @param context something
     * @return object of Activity or null if it is not Activity
     */
    public static Activity scanForActivity(Context context) {
        if (context == null) return null;
        if (context instanceof Activity) {
            return (Activity) context;
        } else if (context instanceof ContextWrapper) {
            return scanForActivity(((ContextWrapper) context).getBaseContext());
        }
        return null;
    }
}
