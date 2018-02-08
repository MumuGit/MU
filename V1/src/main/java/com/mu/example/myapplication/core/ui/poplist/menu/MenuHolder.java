package com.mu.example.myapplication.core.ui.poplist.menu;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mu.example.myapplication.R;


/**
 * Created by mu on 2018/1/25.
 */

public class MenuHolder extends RecyclerView.ViewHolder {
    private TextView mText;

    public MenuHolder(View itemView) {
        super(itemView);
        mText = (TextView) itemView.findViewById(R.id.text);
    }

    public void bind(String data) {
        mText.setText(data);
    }
}
