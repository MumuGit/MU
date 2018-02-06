package com.mu.example.myapplication.core.ui;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.mu.example.myapplication.core.permission.PermissionUtil;

/**
 * Created by mu on 2018/2/6.
 */

public class BaseActivity extends AppCompatActivity{
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionUtil.onRequestPermissionsResult(requestCode,permissions,grantResults);
    }
}
