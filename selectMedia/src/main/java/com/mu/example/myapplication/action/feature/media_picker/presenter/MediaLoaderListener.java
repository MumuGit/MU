package com.mu.example.myapplication.action.feature.media_picker.presenter;

import com.mu.example.myapplication.model.Folder;

import java.util.List;

/**
 * Created by mu on 2018/3/5.
 */

public interface MediaLoaderListener {
    void onLoadComplete(List<Folder> folders);

    void onFolderClick();
}
