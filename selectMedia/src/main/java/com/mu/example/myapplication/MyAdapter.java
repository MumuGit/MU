package com.mu.example.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by mu on 2018/1/10.
 */

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    interface OnItemClickListener {
        void onClick(int position, MediaEntity data);
    }

    List<MediaEntity> mData;
    Context context;

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    OnItemClickListener itemClickListener;

    public MyAdapter(List<MediaEntity> mData, Context context) {
        this.mData = mData;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_loader, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.bind(mData.get(position), context);
        holder.setItemClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickListener != null) {
                    itemClickListener.onClick(position, mData.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void updateData(List<MediaEntity> newData) {
        mData.addAll(newData);
        notifyDataSetChanged();
    }
}
