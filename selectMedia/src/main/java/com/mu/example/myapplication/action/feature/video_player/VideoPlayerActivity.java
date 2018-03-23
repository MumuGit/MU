package com.mu.example.myapplication.action.feature.video_player;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.mu.example.myapplication.C;
import com.mu.example.myapplication.R;
import com.mu.example.myapplication.util.VideoUtil;

/**
 * Created by mu on 2018/3/23.
 */

public class VideoPlayerActivity extends AppCompatActivity {
    private VideoPlayer videoPlayer;
    private String url;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        url = getIntent().getExtras().getString(C.MediaPicker.VIDEO_URL);
        videoPlayer = findViewById(R.id.video_player);
        VideoUtil.display(this, url, videoPlayer);
    }
}
