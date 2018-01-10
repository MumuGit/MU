package com.mu.example.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;

/**
 * Created by mu on 2018/1/10.
 */

public class LoaderPresenter implements LoaderManager.LoaderCallbacks<Cursor> {
    private Context mContext;
    private LoaderManager loaderManager;
    private static final int LOAD_PHOTO = 0;
    private static final String[] PHOTO_PROJECTION = {
            MediaStore.Images.Media.DATA,
            MediaStore.Images.Media.SIZE,
            MediaStore.Images.Media.MIME_TYPE,
            MediaStore.Images.Media.DATE_ADDED
    };

    public LoaderPresenter(Context context, LoaderManager loaderManager) {
        this.mContext = context;
        this.loaderManager = loaderManager;
    }

    public void loadData() {
        loaderManager.initLoader(LOAD_PHOTO, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Uri baseUri;
        baseUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String select = PHOTO_PROJECTION[1] + ">0 and " + PHOTO_PROJECTION[2] + "=? or "
                + PHOTO_PROJECTION[2] + "=?";
        String[] selectArgs = {"image/jpeg", "image/png"};
        String sort = PHOTO_PROJECTION[3] + " DESC";
        return new CursorLoader(mContext, baseUri, PHOTO_PROJECTION, select, selectArgs, sort);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
