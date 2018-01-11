package com.mu.example.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import java.io.File;

/**
 * Created by mu on 2018/1/10.
 */

public class MyViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;

    public MyViewHolder(View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.image);
    }

    public void bind(MediaEntity data, Context context) {
//        if (data.getType().equals(MediaEntity.IMAGE)) {
//            ImageUtils.displayImageFromFile(context, new File(data.getLocalPath()), imageView);
//        }
        ImageUtils.displayImageFromFile(context, new File(data.getLocalPath()), imageView);

    }
}
