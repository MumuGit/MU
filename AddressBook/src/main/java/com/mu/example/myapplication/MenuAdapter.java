package com.mu.example.myapplication;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by mu on 2018/1/25.
 */

public class MenuAdapter extends BaseRecyclerAdapter<Person, PersonHolder> {
    public MenuAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    public PersonHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_poplist_menu, parent, false);
        return new PersonHolder(view);
    }

    @Override
    public void onBindViewHolder(PersonHolder holder, int position) {
        holder.bind(mData.get(position));
    }
}
