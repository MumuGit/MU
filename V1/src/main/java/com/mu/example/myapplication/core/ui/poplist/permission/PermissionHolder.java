package com.mu.example.myapplication.core.ui.poplist.permission;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mu.example.myapplication.App;
import com.mu.example.myapplication.R;
import com.mu.example.myapplication.core.permission.PermissionData;
import com.mu.example.myapplication.core.ui.poplist.PopListUtil;

/**
 * Created by mu on 2018/2/8.
 */

public class PermissionHolder extends RecyclerView.ViewHolder {
    public PermissionHolder(View itemView) {
        super(itemView);
        mText = (TextView) itemView.findViewById(R.id.explain);
        mConfirm = itemView.findViewById(R.id.confirm);
        mCancel = itemView.findViewById(R.id.cancel);
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopListUtil.closePopList();
            }
        });
    }

    private TextView mText;
    private TextView mConfirm;
    private TextView mCancel;

    @SuppressLint("NewApi")
    public void bind(final PermissionData data) {
        mText.setText(data.getExplain());
        mConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopListUtil.closePopList();
                App.mCurrentActivity.requestPermissions(data.getUnauthorizedPermission(), data.getRequestCode());
            }
        });

    }
}
