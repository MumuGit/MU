package com.mu.example.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by mu on 2018/1/10.
 */

public class LoaderActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    LoaderPresenter loaderPresenter;
    MyAdapter myAdapter;
    boolean sIsScrolling = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loader);
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(getBaseContext(), 3));
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING || newState == RecyclerView.SCROLL_STATE_SETTLING) {
                    sIsScrolling = true;
                    Glide.with(LoaderActivity.this).pauseRequests();
                } else if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (sIsScrolling == true) {
                        Glide.with(LoaderActivity.this).resumeRequests();

                    }
                    sIsScrolling = false;
                }
            }

        });
        loaderPresenter = new LoaderPresenter(this, getSupportLoaderManager(), this);
        loaderPresenter.loadData();
    }

    public void updateData(List<MediaEntity> data) {
        if (myAdapter == null) {
            myAdapter = new MyAdapter(data, this);
            recyclerView.setAdapter(myAdapter);
        }
    }
}
