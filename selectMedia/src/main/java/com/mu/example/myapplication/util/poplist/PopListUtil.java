package com.mu.example.myapplication.util.poplist;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import com.mu.example.myapplication.R;

import java.util.List;

/**
 * Created by mu on 2018/1/25.
 */

public class PopListUtil {
    private Context mContext;
    private AlertDialog mDialog;
    private RecyclerView mRecyclerView;
    private BaseRecyclerAdapter mAdapter;
    private AlertDialog.Builder mBuilder;
    private int mWidth;
    private int mHeight;
    private int mX;
    private int mY;
    private static PopListUtil popListUtil;
    private float mTargetX;
    private float mTargetY;
    private float mReSelfX;
    private float mReSelfY;

    private PopListUtil() {
    }

    public static void closePopList() {
        if (popListUtil != null && popListUtil.mDialog.isShowing()) {
            popListUtil.mDialog.dismiss();
        }
    }

    public static PopListUtil with(Activity context, int layout, int style) {
        View view = context.getLayoutInflater().inflate(layout, null);
        popListUtil = new PopListUtil();
        popListUtil.mContext = context;
        popListUtil.mRecyclerView = view.findViewById(R.id.recycler);
        popListUtil.mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        popListUtil.mBuilder = new AlertDialog.Builder(context, style);
        popListUtil.mBuilder.setView(view);
        popListUtil.mDialog = popListUtil.mBuilder.create();
        //加上此句xy坐标才起作用
        popListUtil.mDialog.getWindow().setGravity(Gravity.LEFT | Gravity.TOP);
        return popListUtil;
    }

    public PopListUtil width(int w) {
        mWidth = w;
        return this;
    }

    public PopListUtil location(View target, float reTargetX, float reTargetY, int reSelfX, int reSelfY) {
        mTargetX = target.getX() + reTargetX;
        mTargetY = target.getY() + reTargetY;
        mReSelfX = reSelfX;
        mReSelfY = reSelfY;
        mX = (int) (mTargetX - mReSelfX);
        mY = (int) (mTargetY - mReSelfY);
        return this;
    }

    public PopListUtil height(int h) {
        mHeight = h;
        return this;
    }
//    protected static void forceWrapContent(View v) {
//        // Start with the provided view
//        View current = v;
//
//        // Travel up the tree until fail, modifying the LayoutParams
//        do {
//            // Get the parent
//            ViewParent parent = current.getParent();
//
//            // Check if the parent exists
//            if (parent != null) {
//                // Get the view
//                try {
//                    current = (View) parent;
//                } catch (ClassCastException e) {
//                    // This will happen when at the top view, it cannot be cast to a View
//                    break;
//                }
//
//                // Modify the layout
//                current.getLayoutParams().width = ViewGroup.LayoutParams.WRAP_CONTENT;
//            }
//        } while (current.getParent() != null);
//


//        // Request a layout to be re-done
//        current.requestLayout();
//    }

    public PopListUtil setAdapter(BaseRecyclerAdapter adapter) {
        mRecyclerView.setAdapter(adapter);
        mAdapter = adapter;
        return this;
    }


    public PopListUtil updateData(List data) {
        mAdapter.updateData(data);
        return this;
    }

    public void show() {
        if (!mDialog.isShowing()) {
            mDialog.show();
            WindowManager.LayoutParams params = mDialog.getWindow().getAttributes();
            if (mWidth != 0) {
                params.width = mWidth;
            }
            if (mHeight != 0) {
                params.height = mHeight;
            }

            if (mX != 0) {
                params.x = mX;
            }
            if (mY != 0) {
                params.y = mY;
            }
            mDialog.getWindow().setAttributes(params);
        }
    }


}
