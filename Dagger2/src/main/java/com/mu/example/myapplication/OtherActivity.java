package com.mu.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

import javax.inject.Inject;

/**
 * Created by mu on 2018/1/4.
 */

public class OtherActivity extends AppCompatActivity {
    //添加@Inject注解，表示这个mPoetry是需要注入的
    @Inject
    Poetry mPoetry;

    @Inject
    Gson mGson;

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);

        MainComponent.getInstance()
                .inject(this);

        initView();
    }

    private void initView() {
        mTextView = (TextView) findViewById(R.id.tv_poetry);
        String json = mGson.toJson(mPoetry);
        String text = json + ",mPoetry:" + mPoetry;
        mTextView.setText(text);
        findViewById(R.id.open).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OtherActivity.this, AActivity.class));
            }
        });
    }


}
