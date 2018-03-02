package com.mu.example.myapplication.action.feature.select_media.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mu.example.myapplication.R;
import com.mu.example.myapplication.action.feature.select_media.ui.holder.MyViewHolder;
import com.mu.example.myapplication.model.MediaEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mu on 2018/1/10.
 */

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    public interface OnItemClickListener {
        void onClick(int position, MediaEntity data);
    }

    List<MediaEntity> mData;
    Context context;

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    OnItemClickListener itemClickListener;

    public MyAdapter(Context context) {
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
        if(mData==null){
            mData=new ArrayList<>();
        }
        mData.addAll(newData);
        notifyDataSetChanged();
    }


}
