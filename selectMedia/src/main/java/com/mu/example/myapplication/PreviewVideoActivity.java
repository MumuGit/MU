package com.mu.example.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.mu.example.myapplication.videoplayer.MuVideoPlayer;
import com.mu.example.myapplication.videoplayer.surfaceview.MuSurfaceVideoPlayer;

/**
 * Created by mu on 2018/1/11.
 */

public class PreviewVideoActivity extends AppCompatActivity {
    MediaEntity mediaEntity;
    MuSurfaceVideoPlayer videoPlayer;
    private final String url1 = "http://svideo.spriteapp.com/video/2016/0703/7b5bc740-4134-11e6-ac2b-d4ae5296039d_wpd.mp4";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_video);

//        mediaEntity = (MediaEntity) getIntent().getExtras().get("data");
        videoPlayer = findViewById(R.id.video_player);
        videoPlayer.setDataSource(url1, null);

    }

}
