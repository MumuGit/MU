package com.mu.example.applyfinegesturedector;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MyButton extends ImageView {
    private volatile boolean handled = false;
    private static final String TAG = MyButton.class.getSimpleName();




    public MyButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    int a = 0;

    private boolean handle() {
        if (handled) {
            return false;
        } else {
            return true;
        }
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        int action = event.getAction();
//        switch (action) {
//            case MotionEvent.ACTION_DOWN:
//                System.out.println(TAG + "----onTouchEvent ACTION_DOWN");
//                break;
//            case MotionEvent.ACTION_MOVE:
////                a++;
////                if (a == 2) {
////                    return false;
////                }
//                System.out.println(TAG + "----onTouchEvent ACTION_MOVE--" + a);
//                break;
//            case MotionEvent.ACTION_UP:
//                System.out.println(TAG + "----onTouchEvent ACTION_UP");
//                break;
//            default:
//                break;
//        }
////        boolean result1 = gestureDetector.onTouchEvent(event);
////        System.out.println("result1:" + result1);
////        boolean result2 = super.onTouchEvent(event);
////        System.out.println("result2:" + result2);
////
////        return result1 && result2;
////        gestureDetector.onTouchEvent(event);
////        scaleGestureDetector.onTouchEvent(event);
//        boolean retVal = scaleGestureDetector.onTouchEvent(event);
//        System.out.println("retVal:" + retVal);
////
////        retVal = gestureDetector.onTouchEvent(event) || retVal;
////        return retVal || super.onTouchEvent(event);\
//        return retVal;
//    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        int action = event.getAction();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                System.out.println(TAG + "----dispatchTouchEvent ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                System.out.println(TAG + "----dispatchTouchEvent ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                System.out.println(TAG + "----dispatchTouchEvent ACTION_UP");
                break;

            default:
                break;
        }
        return super.dispatchTouchEvent(event);
    }

}
