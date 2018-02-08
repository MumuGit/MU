package com.mu.example.myapplication.core.ui.poplist.permission;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.mu.example.myapplication.R;
import com.mu.example.myapplication.core.ui.BaseRecyclerAdapter;

/**
 * Created by mu on 2018/2/8.
 */

public class PermissionAdapter extends BaseRecyclerAdapter<String, PermissionHolder> {
    public PermissionAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    public PermissionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.view_permission, parent, false);
        return new PermissionHolder(view);
    }

    @Override
    public void onBindViewHolder(PermissionHolder holder, int position) {
        holder.bind(mData.get(position));
    }
}
