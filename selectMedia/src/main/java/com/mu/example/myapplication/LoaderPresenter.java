package com.mu.example.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mu on 2018/1/10.
 */

public class LoaderPresenter implements LoaderManager.LoaderCallbacks<Cursor> {
    private LoaderActivity mActivity;
    private Context mContext;
    private LoaderManager loaderManager;
    private static final int LOAD_PHOTO = 0;
    public static String mBucketId = String.valueOf(Integer.MIN_VALUE);
    public boolean mHasMore = true;
    public static final int LIMIT = 23;
    private int mPage = 0;

    private static final String[] PHOTO_PROJECTION = {
            MediaStore.Images.Media.DATA,
            MediaStore.Images.Media.SIZE,
            MediaStore.Images.Media.MIME_TYPE,
            MediaStore.Images.Media.DATE_ADDED
    };
    private static final String[] VIDEO_PROJECTION = {
            MediaStore.Images.Media.DATA,
            MediaStore.Images.Media.SIZE,
            MediaStore.Images.Media.MIME_TYPE,
            MediaStore.Images.Media.DATE_ADDED
    };

    public LoaderPresenter(Context context, LoaderManager loaderManager, LoaderActivity activity) {
        this.mContext = context;
        this.loaderManager = loaderManager;
        mActivity = activity;
    }

    public void loadData() {
        loaderManager.initLoader(LOAD_PHOTO, null, this);
    }

    public void getData() {
        mPage++;
        loaderManager.restartLoader(LOAD_PHOTO, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Uri baseUri;
        baseUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        String select = VIDEO_PROJECTION[1] + ">0 and " + VIDEO_PROJECTION[2] + "=? or "
                + VIDEO_PROJECTION[2] + "=?";
        String[] selectArgs = {"video/mp4"};
        String sort = VIDEO_PROJECTION[3] + " DESC LIMIT " + LIMIT + " OFFSET " + LIMIT*mPage;
        return new CursorLoader(mContext, baseUri, VIDEO_PROJECTION, select, selectArgs, sort);
//        baseUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
//        String select = PHOTO_PROJECTION[1] + ">0 and " + PHOTO_PROJECTION[2] + "=? or "
//                + PHOTO_PROJECTION[2] + "=?";
//        String[] selectArgs = {"image/jpeg", "image/png"};
//        String sort = PHOTO_PROJECTION[3] + " DESC";
//        return new CursorLoader(mContext, baseUri, PHOTO_PROJECTION, select, selectArgs, sort);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
//        mActivity.updateData(getImageFile(data));
        List files = getVideoFile(data);
        if (files != null && files.size() >= LIMIT) {
            mHasMore = true;
        } else {
            mHasMore = false;
        }
        mActivity.updateData(files);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    public List getImageFile(Cursor cursor) {
        List<ImageEntity> result = null;

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                result = new ArrayList<>();
                do {
                    ImageEntity entity = new ImageEntity();
                    entity.setLocalPath(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)));
                    entity.setMimeType(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.MIME_TYPE)));
                    entity.setSize(cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE)));
                    entity.setDateAdded(cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_ADDED)));
                    result.add(entity);
                } while (cursor.moveToNext());
            }
        }
        return result;
    }

    public List getVideoFile(Cursor cursor) {
        List<VideoEntity> result = null;

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                result = new ArrayList<>();
                do {
                    VideoEntity entity = new VideoEntity();
                    entity.setLocalPath(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)));
                    entity.setMimeType(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.MIME_TYPE)));
                    entity.setSize(cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE)));
                    entity.setDateAdded(cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_ADDED)));
                    result.add(entity);
                } while (cursor.moveToNext());
            }
        }
        return result;
    }


}
