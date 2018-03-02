package com.mu.example.myapplication.action.feature.select_media;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mu.example.myapplication.R;
import com.mu.example.myapplication.action.feature.select_media.presenter.LoaderPresenter;
import com.mu.example.myapplication.action.feature.select_media.ui.adapter.MyAdapter;
import com.mu.example.myapplication.action.feature.videoplayer.PreviewVideoActivity;
import com.mu.example.myapplication.model.MediaEntity;

import java.util.List;

/**
 * Created by mu on 2018/1/10.
 */

public class LoaderActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    LoaderPresenter loaderPresenter;
    MyAdapter myAdapter;
//    private int mPage;
    MyAdapter.OnItemClickListener adapterItemClick = new MyAdapter.OnItemClickListener() {
        @Override
        public void onClick(int position, MediaEntity data) {

            Intent intent = new Intent(LoaderActivity.this, PreviewVideoActivity.class);
            Bundle bundle = new Bundle();
            bundle.putParcelable("data", (Parcelable) data);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loader);
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(getBaseContext(), 3));
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE &&
                        !recyclerView.canScrollVertically(1)) {
                    if (loaderPresenter.mHasMore) {
                        loadMore();
                    }
                }

            }
        });

        loaderPresenter = new LoaderPresenter(this, getSupportLoaderManager(), this);
        loaderPresenter.loadData(LoaderPresenter.LOAD_ALL);
    }

    public void loadMore() {
        loaderPresenter.loadData(LoaderPresenter.LOAD_ALL);
    }

    public void updateData(List<MediaEntity> data) {
        if (myAdapter == null) {
            myAdapter = new MyAdapter(this);
            myAdapter.setItemClickListener(adapterItemClick);
            recyclerView.setAdapter(myAdapter);
        }
        myAdapter.updateData(data);

    }


}
