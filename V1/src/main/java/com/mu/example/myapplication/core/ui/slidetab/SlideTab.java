package com.mu.example.myapplication.core.ui.slidetab;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mu.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mu on 2018/2/9.
 */

public class SlideTab extends HorizontalScrollView implements ViewPager.OnPageChangeListener {
    //标签布局容器
    private LinearLayout linearLayout;
    //指示器画笔
    private Paint paint;
    //tab容器
    private List<String> list;
    //text容器
    private List<TextView> textViews;
    //当前位置
    private int currIndex = 0;
    //上一次位置
    private int lastIndex = 0;
    //满屏显示数量
    private float maxCount = 5f;
    //偏移百分比
    private float offSet;
    //非选中字体颜色
    private int noCurrColor = android.R.color.black;
    //选中字体颜色
    private int currColor = android.R.color.holo_red_light;
    //指示器颜色
    private int offLineColor = android.R.color.holo_red_light;
    //背景色
    private int background = R.color.colorPrimary;
    //字体大小
    private int txtSize = 18;
    //viewPager
    private ViewPager viewPager;
    //上下文
    private Context context;

    public SlideTab(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    //初始化View
    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        textViews = new ArrayList<>();
        linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams params = new LinearLayout.
                LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout.setLayoutParams(params);
        setBackgroundResource(background);
        addView(linearLayout);
    }

    //初始化tab数据
    public void initTab(List<String> list, ViewPager viewPager) {
        this.list = list;
        this.viewPager = viewPager;
        viewPager.setOnPageChangeListener(this);
        //setListener();
        addTab();
    }

    //设置监听
//    private void setListener() {
//        viewPager.setOnPageChangeListener(this);
//
//        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//
//            @SuppressWarnings("deprecation")
//            @SuppressLint("NewApi")
//            @Override
//            public void onGlobalLayout() {
//                getViewTreeObserver().removeOnGlobalLayoutListener(this);
//                currIndex = viewPager.getCurrentItem();
//                scrollToChild(currIndex, 0);
//            }
//        });
//    }

    private void scrollToChild(int position, int offset) {
        int newScrollX = 0;
        if (lastIndex > position) {// 左滑
            newScrollX = linearLayout.getChildAt(position).getLeft() - linearLayout.getChildAt(position).getWidth();
        } else { //右滑
            newScrollX = linearLayout.getChildAt(lastIndex).getLeft() - linearLayout.getChildAt(position).getWidth();
        }
        Log.e("TAG", "newScrollX:" + newScrollX);
        smoothScrollTo(newScrollX, 0);
    }

    //添加tab 默认选中第一个
    private void addTab() {
        for (int i = 0; i < list.size(); i++) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.
                    LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            final TextView textView = new TextView(context);
            if (i == 0) {
                textView.setTextColor(getResources().getColor(currColor));
            } else {
                textView.setTextColor(getResources().getColor(noCurrColor));
            }
            layoutParams.setMargins(0, 20, 0, 20);
            layoutParams.width = (int) (((float) getWth()) / maxCount);
            textView.setLayoutParams(layoutParams);
            textView.setGravity(Gravity.CENTER);
            textView.setTextSize(txtSize);
            textView.setText(list.get(i));

            final int finalI = i;
            textView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (currIndex != finalI) { //点击的不是当前tab
                        refresh(finalI);
                        viewPager.setCurrentItem(finalI);
                    }

                }
            });
            textViews.add(textView);
            linearLayout.addView(textView);
        }
        invalidate();
    }

    private void refresh(int index) {
        for (int i = 0; i < textViews.size(); i++) {
            if (i != index) {
                textViews.get(i).setTextColor(getResources().getColor(noCurrColor));
            } else {
                textViews.get(i).setTextColor(getResources().getColor(currColor));
            }
        }
    }

    private int getWth() {
        WindowManager manager = ((Activity) context).getWindowManager();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float h = getHeight();
        paint.setColor(getResources().getColor(offLineColor));
        View view = linearLayout.getChildAt(currIndex);

        float lineLeft = view.getLeft();
        float lineRight = view.getRight();

        if (offSet > 0f) {
            View nextTab = linearLayout.getChildAt(currIndex + 1);
            final float nextTabLeft = nextTab.getLeft();
            final float nextTabRight = nextTab.getRight();

            lineLeft = (offSet * nextTabLeft + (1f - offSet) * lineLeft);
            lineRight = (offSet * nextTabRight + (1f - offSet) * lineRight);
        }
        canvas.drawRect(lineLeft, h - 5, lineRight, h, paint);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        currIndex = position;
        offSet = positionOffset;
        invalidate();
    }

    @Override
    public void onPageSelected(int position) {
        lastIndex = position;
        refresh(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
//        if (state == 0) {
//            Log.e("TAG", "currIndex:" + currIndex);
//            if (currIndex != textViews.size() - 1 && currIndex != 0) {
//                scrollToChild(currIndex, 0);
//            }
//        }
    }
}
