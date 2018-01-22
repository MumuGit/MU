package com.mu.example.myapplication;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by mu on 2018/1/18.
 */

public class UnderLineTextView extends TextView {
    int mLineGap;
    int mLineHeight;

    public UnderLineTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.UnderLineTextView);
        mLineHeight = (int) typedArray.getDimension(R.styleable.UnderLineTextView_line_height, 0);
        mLineGap = (int) typedArray.getDimension(R.styleable.UnderLineTextView_line_gap, 0);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth();
        int hight = getMeasuredHeight();
        setMeasuredDimension(width, hight + mLineGap + mLineHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint linePaint = getPaint();
        linePaint.setColor(getResources().getColor(R.color.main_text_color_fouce_red));
        Rect lineRect = new Rect(getLeft(), getBottom() - mLineHeight, getRight(), getBottom());
        canvas.drawRect(lineRect, linePaint);
    }
}
