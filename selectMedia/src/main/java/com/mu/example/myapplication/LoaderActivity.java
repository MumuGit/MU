package com.mu.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * Created by mu on 2018/1/10.
 */

public class LoaderActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    LoaderPresenter loaderPresenter;
    MyAdapter myAdapter;

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
        loaderPresenter.loadData();
    }

    public void loadMore() {
        loaderPresenter.getData();
    }

    public void updateData(List<MediaEntity> data) {
        if (myAdapter == null) {
            myAdapter = new MyAdapter(data, this);
            myAdapter.setItemClickListener(adapterItemClick);
            recyclerView.setAdapter(myAdapter);
        } else {
            myAdapter.updateData(data);
        }
    }


}
