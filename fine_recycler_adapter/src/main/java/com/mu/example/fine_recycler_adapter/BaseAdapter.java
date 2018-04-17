package com.mu.example.fine_recycler_adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class BaseAdapter<T, VH extends BaseHolder> extends RecyclerView.Adapter<VH> {
    protected List<T> mData;
    protected LayoutInflater mInflater;

    public BaseAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_expandable, parent);
        VH baseHolder = (VH) new BaseHolder<T>(view);
        return baseHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.bind(mData.get(position));
    }


    @Override
    public int getItemCount() {
        if (mData == null) {
            return 0;
        }
        return mData.size();
    }
}
