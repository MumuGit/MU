package com.mu.example.myapplication;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by mu on 2018/1/11.
 */

public class PreviewVideoActivity extends AppCompatActivity {
    MediaEntity mediaEntity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_preview_video);
        mediaEntity = (MediaEntity) getIntent().getExtras().get("data");
    }
}
