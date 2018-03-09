package com.mu.example.myapplication;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.mu.example.myapplication.action.feature.media_picker.presenter.MediaDataCache;
import com.mu.example.myapplication.action.feature.media_picker.presenter.MediaPickerConfigurer;

public class MainActivity extends AppCompatActivity {
    Button photo;
    Button vedio;
    String[] permission = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permission, C.RequestCode.REQUEST_CODE_MEDIA_PICK);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        photo = findViewById(R.id.photo);
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaDataCache.getInstance().setCurrentSelectedTag("1");
                MediaDataCache.getInstance().setCurrentMaxSelectableCount(9);
                MediaDataCache.getInstance().setShowCamera(true);
                requestPermission();
            }
        });
        vedio = findViewById(R.id.vedio);
        vedio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaDataCache.getInstance().setCurrentSelectedTag("2");
                MediaDataCache.getInstance().setCurrentMaxSelectableCount(8);
                MediaDataCache.getInstance().setShowCamera(false);
                requestPermission();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == C.RequestCode.REQUEST_CODE_MEDIA_PICK) {
            MediaPickerConfigurer configurer = MediaPickerConfigurer.create();
            configurer.count(MediaDataCache.getInstance().getCurrentMaxSelectableCount());
            configurer.showCamera(MediaDataCache.getInstance().isShowCamera());
            configurer.start(this, C.RequestCode.REQUEST_CODE_MEDIA_PICK,
                    MediaDataCache.getInstance().getCurrentSelectedTag());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == C.RequestCode.REQUEST_CODE_MEDIA_PICK) {
            if (resultCode == RESULT_OK) {

            }
        }

    }
}
