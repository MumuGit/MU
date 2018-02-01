package com.mu.example.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by mu on 2018/1/25.
 */

public class PersonHolder extends RecyclerView.ViewHolder {
    private TextView mText;

    public PersonHolder(View itemView) {
        super(itemView);
        mText = (TextView) itemView.findViewById(R.id.text);
    }

    public void bind(Person data) {
        mText.setText(data.getName() + "\n" + data.getPhone() + "\n" + data.getEmail());
    }
}
