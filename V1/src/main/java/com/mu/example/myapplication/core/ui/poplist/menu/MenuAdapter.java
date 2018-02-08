package com.mu.example.myapplication.core.ui.poplist.menu;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.mu.example.myapplication.R;
import com.mu.example.myapplication.core.ui.BaseRecyclerAdapter;


/**
 * Created by mu on 2018/1/25.
 */

public class MenuAdapter extends BaseRecyclerAdapter<String, MenuHolder> {
    public MenuAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    public MenuHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_poplist_menu, parent, false);
        return new MenuHolder(view);
    }

    @Override
    public void onBindViewHolder(MenuHolder holder, int position) {
        holder.bind(mData.get(position));
    }
}
