package com.mu.example.myapplication.core.ui.poplist;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import com.mu.example.myapplication.R;
import com.mu.example.myapplication.core.ui.BaseRecyclerAdapter;
import com.mu.example.myapplication.core.ui.poplist.menu.MenuDecoration;

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

    enum TYPE {

    }

    private PopListUtil() {

    }


    public static PopListUtil with(Activity context, int layout, int style) {
        View view = context.getLayoutInflater().inflate(R.layout.view_pop_list, null);
        PopListUtil popListUtil = new PopListUtil();
        popListUtil.mContext = context;
        popListUtil.mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        popListUtil.mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        popListUtil.mRecyclerView.addItemDecoration(new MenuDecoration());
        popListUtil.mBuilder = new AlertDialog.Builder(context, R.style.pop_list);
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
        float targetX = target.getX() + reTargetX;
        float targetY = target.getY() + reTargetY;
        float selfX = mDialog.getWindow().getAttributes().x + reSelfX;
        float selfY = mDialog.getWindow().getAttributes().y + reSelfY;

        mX = (int) (reTargetX + targetX);
        mY = (int) (reTargetY + targetY);
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
                mX = mX - params.width + mContext.getResources().
                        getDimensionPixelSize(R.dimen.menu_tri_right_margin) + mContext.getResources().
                        getDimensionPixelSize(R.dimen.menu_tri_width) / 2;
                params.x = mX;
            }
            if (mY != 0) {
                params.y = mY;
            }
            mDialog.getWindow().setAttributes(params);
        }
    }


}
