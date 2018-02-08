package com.mu.example.myapplication.core.ui.poplist.permission;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mu.example.myapplication.R;

/**
 * Created by mu on 2018/2/8.
 */

public class PermissionHolder extends RecyclerView.ViewHolder {
    public PermissionHolder(View itemView) {
        super(itemView);
        mText = (TextView) itemView.findViewById(R.id.explain);
    }

    private TextView mText;

    public void bind(String data) {
        mText.setText(data);
    }
}
