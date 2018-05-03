package com.mu.example.applyfinegesturedector;

import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import static android.view.MotionEvent.ACTION_DOWN;

public class DragLayout1 extends RelativeLayout {
    private final DragHelper mDragHelper;
    private View mDragView;
    /**
     * 宽度
     */
    private int mContentLayoutWidth;
    private GestureDetectorCompat gestureDetector;
    private GestureDetector.SimpleOnGestureListener onGestureListener = new GestureDetector.SimpleOnGestureListener() {
        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
//            if (handle()) {
//                handled = true;
//            }
            Toast.makeText(getContext(), "onDoubleTap", Toast.LENGTH_SHORT).show();
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {
//            if (handle()) {
//                handled = true;
//            }
            Toast.makeText(getContext(), "onLongPress", Toast.LENGTH_SHORT).show();
//            super.onLongPress(e);
        }

//        @Override
//        public boolean onContextClick(MotionEvent e) {
//            return true;
//        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
//            if (handle()) {
//                handled = true;
//            } else {
//                return false;
//            }
            Toast.makeText(getContext(), "onFling", Toast.LENGTH_SHORT).show();
            return false;
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
//            if (handle()) {
//                handled = true;
//            }
            return false;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
//            if (handle()) {
//                handled = true;
//            }
            Toast.makeText(getContext(), "onSingleTapConfirmed", Toast.LENGTH_SHORT).show();

            return false;
        }
    };

    public DragLayout1(Context context) {
        this(context, null);
    }

    public DragLayout1(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public DragLayout1(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mDragHelper = DragHelper.create(this, 1.0f, new DragHelperCallback());

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mDragView = findViewById(R.id.button);
        mDragView.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.setScaleX(mScaleFactor);
                v.setScaleY(mScaleFactor);
                gestureDetector.onTouchEvent(event);
                scaleGestureDetector.onTouchEvent(event);
                return true;

            }
        });
        scaleGestureDetector = new ScaleGestureDetector(getContext(), onScaleGestureListener);

        gestureDetector = new GestureDetectorCompat(getContext(), onGestureListener);

    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        final int action = MotionEventCompat.getActionMasked(ev);
//        if (action == MotionEvent.ACTION_CANCEL
//                || action == MotionEvent.ACTION_UP) {
//            mDragHelper.cancel();
//            return false;
//        }
//        boolean result
//                = mDragHelper.shouldInterceptTouchEvent(ev);
////        boolean result1 = gestureDetector.onTouchEvent(ev);
//        System.out.println("result:" + result);
//        return false;
        switch (action) {
            case ACTION_DOWN:
                break;
            default:
        }

        return mDragHelper.shouldInterceptTouchEvent(ev);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mContentLayoutWidth = mDragView.getMeasuredWidth();
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        mDragHelper.processTouchEvent(ev);
        System.out.println("onTouchEvent:" + ev.getAction());

        return true;
    }

    private class DragHelperCallback extends DragHelper.Callback {

        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return child == mDragView;
        }

        @Override
        public int getViewHorizontalDragRange(View child) {
            return mContentLayoutWidth;
        }

        @Override
        public int getViewVerticalDragRange(View child) {
            return 1000;
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
//            final int leftBound = getPaddingLeft();
//            final int rightBound = getWidth() - mDragView.getWidth();
//            final int newLeft = Math.min(Math.max(left, leftBound), rightBound);
            return left;
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            return top;
        }
    }

    private float mScaleFactor = 1.0f;

    private ScaleGestureDetector scaleGestureDetector;
    private ScaleGestureDetector.OnScaleGestureListener onScaleGestureListener = new ScaleGestureDetector.SimpleOnScaleGestureListener() {

        @Override
        public boolean onScale(ScaleGestureDetector detector) {

//            // Don't let the object get too small or too large.
//            mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 5.0f));
//
//            invalidate();
//            return true;
//            if (handle()) {
//                handled = true;
//            } else {
//                return false;
//            }
//            Toast.makeText(getContext(), "onScale", Toast.LENGTH_SHORT).show();
            System.out.println("scale:" + scaleGestureDetector.getScaleFactor());
            mScaleFactor *= scaleGestureDetector.getScaleFactor();


            return false;
        }
    };
}
