package com.mu.example.myapplication.core.ui.poplist.menu;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mu.example.myapplication.util.ScreenUtil;

/**
 * Created by mu on 2018/1/29.
 */

public class MenuDecoration extends RecyclerView.ItemDecoration {

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        drawHorizontalLine(c, parent);
    }

    private Drawable mDivider;

    private void drawHorizontalLine(Canvas c, RecyclerView parent) {

        Paint paint = new Paint();
        paint.setARGB(255, 255, 255, 255);
        int left = parent.getPaddingLeft() + ScreenUtil.dip2px(10);
        int right = parent.getWidth() - parent.getPaddingRight() - ScreenUtil.dip2px(10);
        int count = parent.getChildCount();
        for (int i = 0; i < count; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            int top = child.getBottom() + layoutParams.bottomMargin;
            int bottom = top + ScreenUtil.dip2px(0.5f);
            c.drawRect(new Rect(left, top, right, bottom), paint);
        }
    }
}
