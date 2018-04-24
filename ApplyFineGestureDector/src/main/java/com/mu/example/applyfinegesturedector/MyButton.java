package com.mu.example.applyfinegesturedector;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

public class MyButton extends Button {
    private static final String TAG = MyButton.class.getSimpleName();

    public MyButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    int a = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                System.out.println(TAG + "----onTouchEvent ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
//                a++;
//                if (a == 2) {
//                    return false;
//                }
                System.out.println(TAG + "----onTouchEvent ACTION_MOVE--"+a);
                break;
            case MotionEvent.ACTION_UP:
                System.out.println(TAG + "----onTouchEvent ACTION_UP");
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

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
