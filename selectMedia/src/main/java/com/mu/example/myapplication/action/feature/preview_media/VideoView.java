package com.mu.example.myapplication.action.feature.preview_media;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.mu.example.myapplication.C;
import com.mu.example.myapplication.R;
import com.mu.example.myapplication.action.feature.video_player.VideoPlayerActivity;

/**
 * Created by mu on 2018/3/23.
 */

public class VideoView extends FrameLayout {
    private ImageView content;
    private ImageView cover;
    private String url;

    public VideoView(@NonNull final Context context) {
        super(context);
        content = new ImageView(context);
        cover = new ImageView(context);
        cover.setImageResource(R.mipmap.video_center_start);
        cover.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, VideoPlayerActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString(C.MediaPicker.VIDEO_URL, url);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        FrameLayout.LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        FrameLayout.LayoutParams params2 = new LayoutParams(150, 150);
        params2.gravity = Gravity.CENTER;
        addView(content, params);
        addView(cover, params2);
    }

    public ImageView getContent() {
        return content;
    }


    public void setUrl(String url) {
        this.url = url;
    }


}
