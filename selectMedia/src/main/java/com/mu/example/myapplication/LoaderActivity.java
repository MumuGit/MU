package com.mu.example.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by mu on 2018/1/10.
 */

public class LoaderActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    LoaderPresenter loaderPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loader);
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(getBaseContext(), 3));
        loaderPresenter = new LoaderPresenter(this, getSupportLoaderManager());
        loaderPresenter.loadData();
    }
}
