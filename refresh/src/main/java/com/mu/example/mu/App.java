package com.mu.example.mu;

import android.app.Application;
import android.os.Looper;



/**
 * Created by mu on 2017/5/24.
 */

public class App extends Application {
    private static App myApplication = null;
    public static boolean isDebug= true;
    public static App getApplication() {
        return myApplication;
    }

    /**
     * 判断当前线程是否为UI线程
     *
     * @return
     */
    public static boolean isOnUiThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    /**
     * 获取资源文件的String
     * @param resId
     * @return
     */
    public static String getResString(int resId){
        return getApplication().getString(resId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());


    }


}
