package com.mu.example.fine_recycler_adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.HashMap;

public abstract class BaseHolder<T> extends RecyclerView.ViewHolder {


    public BaseHolder(View itemView) {
        super(itemView);

    }

    public abstract void bind(T item, int position, HashMap listeners, HashMap params);

    public interface OnItemViewClickListener {
        void ItemViewClick(int position, Object data, HashMap params);
    }
}
