package com.mu.example.myapplication;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ybslux.client.weight.recyclerbase.BaseViewHolder;

/**
 * Created by mu on 2018/1/9.
 */

public class AscDescItemHolder extends BaseViewHolder {
    private TextView text;
    private ImageView up;
    private ImageView down;

    public AscDescItemHolder(View itemView) {
        super(itemView);
        text = (TextView) itemView.findViewById(R.id.text);
        up = (ImageView) itemView.findViewById(R.id.up);
        down = (ImageView) itemView.findViewById(R.id.down);
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    public void bind(String item) {
        text.setText(item);
    }
}
