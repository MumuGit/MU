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
import android.widget.Toast;

public class MyButton extends Button {
    private boolean handled = false;
    private static final String TAG = MyButton.class.getSimpleName();
    private GestureDetectorCompat gestureDetector;
    private float mScaleFactor = 1.0f;
    private ScaleGestureDetector scaleGestureDetector;
    private ScaleGestureDetector.OnScaleGestureListener onScaleGestureListener = new SimpleOnScaleGestureListener() {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            if (handle()) {
                handled = true;
            }
//            Toast.makeText(getContext(), "onScale", Toast.LENGTH_SHORT).show();
            System.out.println("scale:" + scaleGestureDetector.getScaleFactor());
            mScaleFactor *= scaleGestureDetector.getScaleFactor();
            MyButton.this.setScaleX(mScaleFactor);
            MyButton.this.setScaleY(mScaleFactor);
            return handled;
        }
    };
    private GestureDetector.SimpleOnGestureListener onGestureListener = new GestureDetector.SimpleOnGestureListener() {
        @Override
        public boolean onDown(MotionEvent e) {
            if (handle()) {
                handled = true;
            }
            return handled;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            if (handle()) {
                handled = true;
            }
            Toast.makeText(getContext(), "onDoubleTap", Toast.LENGTH_SHORT).show();
            return handled;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            if (handle()) {
                handled = true;
            }
            Toast.makeText(getContext(), "onLongPress", Toast.LENGTH_SHORT).show();

//            super.onLongPress(e);
        }

//        @Override
//        public boolean onContextClick(MotionEvent e) {
//            return true;
//        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (handle()) {
                handled = true;
            }
            Toast.makeText(getContext(), "onFling", Toast.LENGTH_SHORT).show();

            return handled;
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            if (handle()) {
                handled = true;
            }
            return handled;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            if (handle()) {
                handled = true;
            }
            Toast.makeText(getContext(), "onSingleTapConfirmed", Toast.LENGTH_SHORT).show();

            return handled;
        }
    };


    public MyButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        gestureDetector = new GestureDetectorCompat(context, onGestureListener);
        scaleGestureDetector = new ScaleGestureDetector(context, onScaleGestureListener);
    }

    int a = 0;

    private boolean handle() {
        if (handled) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        handled = false;
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
                System.out.println(TAG + "----onTouchEvent ACTION_MOVE--" + a);
                break;
            case MotionEvent.ACTION_UP:
                System.out.println(TAG + "----onTouchEvent ACTION_UP");
                break;
            default:
                break;
        }
//        boolean result1 = gestureDetector.onTouchEvent(event);
//        System.out.println("result1:" + result1);
//        boolean result2 = super.onTouchEvent(event);
//        System.out.println("result2:" + result2);
//
//        return result1 && result2;
        gestureDetector.onTouchEvent(event);
        scaleGestureDetector.onTouchEvent(event);
        return true;
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
