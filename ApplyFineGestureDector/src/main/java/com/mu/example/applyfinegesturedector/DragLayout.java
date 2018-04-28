package com.mu.example.applyfinegesturedector;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class DragLayout extends RelativeLayout {
    private final DragHelper mDragHelper;
    private View mDragView;
    /**
     * 宽度
     */
    private int mContentLayoutWidth;


    public DragLayout(Context context) {
        this(context, null);
    }

    public DragLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public DragLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mDragHelper = DragHelper.create(this, 1.0f, new DragHelperCallback());
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mDragView = findViewById(R.id.button);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        final int action = MotionEventCompat.getActionMasked(ev);
//        if (action == MotionEvent.ACTION_CANCEL
//                || action == MotionEvent.ACTION_UP) {
//            mDragHelper.cancel();
//            return false;
//        }
        boolean result
                = mDragHelper.shouldInterceptTouchEvent(ev);
//        boolean result1 = gestureDetector.onTouchEvent(ev);
        System.out.println("result:" + result);
        return false;
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
            return false;
        }

        @Override
        public int getViewHorizontalDragRange(View child) {
            return mContentLayoutWidth;
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
//            final int leftBound = getPaddingLeft();
//            final int rightBound = getWidth() - mDragView.getWidth();
//            final int newLeft = Math.min(Math.max(left, leftBound), rightBound);
            return left;
        }
    }
}
