package com.mu.example.myapplication.action.feature.preview_media;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.mu.example.myapplication.C;
import com.mu.example.myapplication.R;
import com.mu.example.myapplication.action.feature.media_picker.presenter.MediaDataCache;
import com.mu.example.myapplication.util.AppManagerUtil;

/**
 * Created by mu on 2018/3/12.
 */

public class MediaPreviewActivity extends AppCompatActivity {
    private ViewPager mDisplay;
    private TextView mBarTitle;
    private int mType = -1;
    private int mDataIndex;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_preview);
        initView();

    }

    @Override
    protected void onResume() {
        super.onResume();
        config(getIntent());
    }

    private void initView() {
        mDisplay = findViewById(R.id.display);
        mBarTitle = findViewById(R.id.bar_title);
    }

    private void config(Intent intent) {
        AppManagerUtil.scanForActivity(this)
                .setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        if (intent != null && intent.getExtras() != null) {
            Bundle bundle = intent.getExtras();
//            mType = bundle.getInt(C.MediaPicker.PREVIEW_MEDIA_TYPE, -1);
            mDataIndex = bundle.getInt(C.MediaPicker.PREVIEW_MEDIA_DATA_INDEX);
        }
        mDisplay.setAdapter(new PreviewAdapter(MediaDataCache.getInstance()
                .getCurrentFolder().getMedias()));
        mDisplay.setCurrentItem(mDataIndex);
    }

}
