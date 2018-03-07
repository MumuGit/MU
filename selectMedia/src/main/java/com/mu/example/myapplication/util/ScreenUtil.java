package com.mu.example.myapplication.util;

import android.content.Context;
import android.util.TypedValue;
import android.view.Display;
import android.view.WindowManager;

import com.mu.example.myapplication.App;

public class ScreenUtil {

    /**
     * 用于获取状态栏的高度
     *
     * @return 返回状态栏高度的像素值。
     */

    public static int getStatusBarHeight() {
        int result = 0;
        int resourceId = App.getApplication().getResources().getIdentifier(
                "status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = App.getApplication().getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static float px2sp(Context context, float pxVal)

    {

        return (pxVal / context.getResources().getDisplayMetrics().scaledDensity);

    }

    public static int sp2px(Context context, float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,

                spVal, context.getResources().getDisplayMetrics());

    }


    /**
     * 获取屏幕宽度
     */
    public static int getScreenWidth() {
        WindowManager wm = (WindowManager) App.getApplication()
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        return display.getWidth();
    }

    /**
     * 获取屏幕高度
     */
    public static int getScreenHeight() {
        WindowManager wm = (WindowManager) App.getApplication()
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        return display.getHeight();
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 根据手机分辨率从dp转成px
     *
     * @param
     * @param dpValue
     * @return
     */

    public static int dip2px(float dpValue) {
        final float scale = App.getApplication().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
