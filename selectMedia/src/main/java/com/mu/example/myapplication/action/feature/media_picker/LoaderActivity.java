package com.mu.example.myapplication.action.feature.media_picker;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.mu.example.myapplication.C;
import com.mu.example.myapplication.R;
import com.mu.example.myapplication.action.feature.media_picker.presenter.LoaderPresenter;
import com.mu.example.myapplication.action.feature.media_picker.presenter.MediaDataCache;
import com.mu.example.myapplication.action.feature.media_picker.presenter.MediaLoaderListener;
import com.mu.example.myapplication.action.feature.media_picker.ui.adapter.MediaAdapter;
import com.mu.example.myapplication.action.feature.videoplayer.PreviewVideoActivity;
import com.mu.example.myapplication.model.Folder;
import com.mu.example.myapplication.model.Media;
import com.mu.example.myapplication.util.ScreenUtil;
import com.mu.example.myapplication.util.poplist.PopListUtil;
import com.mu.example.myapplication.util.poplist.media_folder.MediaFolderAdapter;

import java.util.List;

/**
 * Created by mu on 2018/1/10.
 */

public class LoaderActivity extends AppCompatActivity implements MediaLoaderListener, MediaAdapter.OnSelectCountChangeListener {
    RecyclerView recyclerView;
    LoaderPresenter loaderPresenter;
    MediaAdapter mediaAdapter;
    MediaFolderAdapter folderAdapter;
    Button category_btn;
    Button done;
    Button preview;
    RelativeLayout footer;
    private boolean showCamera = C.MediaPicker.DEAULT_SHOW_CAMERA;
    private boolean isReplace = C.MediaPicker.DEAULT_REPLACE;
    private int maxSelectableCount = C.MediaPicker.DEAULT_SELECTABLE_MAX_COUNT;
    private String selectTag;
    //    private int mPage;
    MediaAdapter.OnItemClickListener adapterItemClick = new MediaAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(Media media) {
            Intent intent = new Intent(LoaderActivity.this, PreviewVideoActivity.class);
            Bundle bundle = new Bundle();
            bundle.putParcelable(C.MediaPicker.PREVIEW_MEDIA_DATA, media);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loader);
        initView();
        config(getIntent().getExtras());
//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                if (newState == RecyclerView.SCROLL_STATE_IDLE &&
//                        !recyclerView.canScrollVertically(1)) {
//                    if (loaderPresenter.mHasMore) {
//                        loadMore();
//                    }
//                }
//
//            }
//        });
        loaderPresenter = new LoaderPresenter(this, getSupportLoaderManager(), this);
        loaderPresenter.loadData(LoaderPresenter.LOAD_ALL);
    }

    private void config(Bundle bundle) {
        if (bundle != null) {
            showCamera = bundle.getBoolean(C.MediaPicker.SHOW_CAMERA);
            maxSelectableCount = bundle.getInt(C.MediaPicker.SELECTABLE_MAX_COUNT);
            selectTag = bundle.getString(C.MediaPicker.SELECT_TAG);
            isReplace = bundle.getBoolean(C.MediaPicker.REPLACE);
        }
    }

    private void initView() {
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(getBaseContext(), 3));
        category_btn = findViewById(R.id.category_btn);
        done = findViewById(R.id.done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK, null);
                MediaDataCache.getInstance().complete();
                LoaderActivity.this.finish();
            }
        });
        preview = findViewById(R.id.preview);
        category_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int height = ScreenUtil.getScreenHeight() / 5 * 4;
                PopListUtil.with(LoaderActivity.this, R.layout.poplist_bc_media_folder,
                        R.style.MediaFolder).setAdapter(folderAdapter)
                        .location(footer, 0, 0, 0,
                                height)
                        .width(ScreenUtil.getScreenWidth()).height(height).show();
            }
        });
        footer = findViewById(R.id.footer);
    }

    private void updateCategoryBtn() {
        int index = MediaDataCache.getInstance().getSelectedFolderIndex();
        String text = MediaDataCache.getInstance().get().get(index).getName();
        category_btn.setText(text);
    }

    private void updateSelectedCount() {
        int seleted = MediaDataCache.getInstance().getTempSelectedMediaCount();
        int max = MediaDataCache.getInstance().getCurrentMaxSelectableCount();
        if (seleted > 0) {
            done.setText(getResources().getString(R.string.done) + "(" + seleted + "/" + max + ")");
            preview.setText(getResources().getString(R.string.preview) + "(" + seleted + ")");
        } else {
            done.setText(getResources().getString(R.string.done));
            preview.setText(getResources().getString(R.string.preview));
        }
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED, null);
        MediaDataCache.getInstance().cancel();
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        MediaDataCache.getInstance().remove();
        super.onDestroy();
    }

    @Override
    public void onLoadComplete(List<Folder> folders) {
        MediaDataCache.getInstance().add(folders);
        initFolderAdapter();
        initMediaAdapter();
        recyclerView.setAdapter(mediaAdapter);
        mediaAdapter.updateData();
        updateCategoryBtn();
        updateSelectedCount();
    }

    @Override
    public void onFolderClick() {
        updateCategoryBtn();
        PopListUtil.closePopList();
        mediaAdapter.updateData();
    }

    public void initMediaAdapter() {
        mediaAdapter = new MediaAdapter(this);
        mediaAdapter.setSelectTag(selectTag);
        mediaAdapter.setMaxSelectableCount(maxSelectableCount);
        mediaAdapter.setShowCamera(showCamera);
        mediaAdapter.setOnItemClickListener(adapterItemClick);
        mediaAdapter.setOnSelectCountChangeListener(this);
        mediaAdapter.setReplace(isReplace);
    }

    public void initFolderAdapter() {
        folderAdapter = new MediaFolderAdapter(this);
        folderAdapter.setLoaderListener(this);
        folderAdapter.updateData();
    }

    @Override
    public void OnSelectCountChange() {
        updateSelectedCount();
    }
}
