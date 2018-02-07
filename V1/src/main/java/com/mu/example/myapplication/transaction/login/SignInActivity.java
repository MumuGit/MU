package com.mu.example.myapplication.transaction.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.mu.example.myapplication.R;
import com.mu.example.myapplication.core.ui.BaseActivity;
import com.mu.example.myapplication.util.FragmentUtil;

/**
 * Created by mu on 2018/1/16.
 */

public class SignInActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        Fragment fragment = new SignInFragment();
        FragmentUtil.addFragment(getSupportFragmentManager(), fragment, R.id.container_fragment);
    }

}
