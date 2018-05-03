package com.mu.example.test;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;

public class MyImageView extends ImageView {

    ScaleGestureDetector scaleGestureDetector;
    float scale = 1;
    ScaleGestureDetector.SimpleOnScaleGestureListener listener = new ScaleGestureDetector.SimpleOnScaleGestureListener() {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            scale = scale * detector.getScaleFactor();
            MyImageView.this.setScaleX(scale);
            MyImageView.this.setScaleY(scale);
            return false;//!!!
        }
    };

    public MyImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        scaleGestureDetector = new ScaleGestureDetector(context, listener);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return scaleGestureDetector.onTouchEvent(event);

    }
}
