package com.mu.example.myapplication;

import android.content.Context;

import com.ybslux.client.weight.recyclerbase.BaseAdapter;

import java.util.List;

/**
 * Created by mu on 2018/1/9.
 */

public class AscDescAdapter extends BaseAdapter<String, AscDescItemHolder> {


    public AscDescAdapter(Context context, List<String> data, int res) {
        super(context, data, res);
    }

    @Override
    public void onBindViewHolder(AscDescItemHolder holder, int position) {
        holder.bind(mData.get(position));
    }
}
