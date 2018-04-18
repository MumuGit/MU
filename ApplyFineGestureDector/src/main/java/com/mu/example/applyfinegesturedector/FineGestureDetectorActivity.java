package com.mu.example.applyfinegesturedector;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.Toast;

import com.mu.example.fine_gesture_dector.FineGestureDetector;


public class FineGestureDetectorActivity extends AppCompatActivity {
    FineGestureDetector gestureDetector;
    FineGestureDetector.OnGestureListener onGestureListener
            = new FineGestureDetector.OnGestureListener() {
        @Override
        public boolean onDown(MotionEvent e) {
            Toast.makeText(FineGestureDetectorActivity.this,
                    "onDown", Toast.LENGTH_SHORT).show();
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }


        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            Toast.makeText(FineGestureDetectorActivity.this,
                    "onSingleTapUp", Toast.LENGTH_SHORT).show();
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {

        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return false;
        }


    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture);
        gestureDetector = new FineGestureDetector(this,onGestureListener);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }
}
