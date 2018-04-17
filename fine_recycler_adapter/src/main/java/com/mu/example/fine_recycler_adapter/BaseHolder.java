package com.mu.example.fine_recycler_adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public class BaseHolder<T> extends RecyclerView.ViewHolder {

    public BaseHolder(View itemView) {
        super(itemView);
    }

    public void bind(T item) {

    }
}
