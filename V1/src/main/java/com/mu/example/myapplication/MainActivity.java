package com.mu.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.mu.example.myapplication.core.ui.slidetab.SlideTab;
import com.mu.example.myapplication.transaction.login.SignInActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
//        startActivity(new Intent(this, SignInActivity.class));
    }


}
