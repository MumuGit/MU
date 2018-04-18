package com.mu.example.applyfinegesturedector;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Toast;

public class GoogleGestureDectorActivity extends AppCompatActivity {
    GestureDetector gestureDetector;
    GestureDetector.OnGestureListener onGestureListener
            = new GestureDetector.OnGestureListener() {
        @Override
        public boolean onDown(MotionEvent e) {
            Toast.makeText(GoogleGestureDectorActivity.this,
                    "onDown", Toast.LENGTH_SHORT).show();
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {
            Toast.makeText(GoogleGestureDectorActivity.this,
                    "onShowPress", Toast.LENGTH_SHORT).show();
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            Toast.makeText(GoogleGestureDectorActivity.this,
                    "onSingleTapUp", Toast.LENGTH_SHORT).show();
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            Toast.makeText(GoogleGestureDectorActivity.this,
                    "onScroll", Toast.LENGTH_SHORT).show();
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            Toast.makeText(GoogleGestureDectorActivity.this,
                    "onLongPress", Toast.LENGTH_SHORT).show();
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Toast.makeText(GoogleGestureDectorActivity.this,
                    "onFling", Toast.LENGTH_SHORT).show();
            return false;
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture);
        gestureDetector = new GestureDetector(onGestureListener);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }
}
