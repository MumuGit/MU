package com.mu.example.myapplication.action.feature.select_media.ui.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.mu.example.myapplication.R;
import com.mu.example.myapplication.model.MediaEntity;
import com.mu.example.myapplication.util.ImageUtils;

import java.io.File;

/**
 * Created by mu on 2018/1/10.
 */

public class MyViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    Context context;

    public MyViewHolder(View itemView) {
        super(itemView);
        context = itemView.getContext();
        imageView = itemView.findViewById(R.id.image);
    }

    public void bind(MediaEntity data, Context context) {
//        if (data.getType().equals(MediaEntity.IMAGE)) {
//            ImageUtils.displayImageFromFile(context, new File(data.getLocalPath()), imageView);
//        }
        ImageUtils.displayImageFromFile(context, new File(data.getLocalPath()), imageView);

    }

    public void setItemClick(View.OnClickListener clickListener) {
        itemView.setOnClickListener(clickListener);
    }
}
