package com.mu.example.myapplication.action.feature.select_media.presenter;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;

import com.mu.example.myapplication.action.feature.select_media.LoaderActivity;
import com.mu.example.myapplication.model.ImageEntity;
import com.mu.example.myapplication.model.MediaEntity;
import com.mu.example.myapplication.model.VideoEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mu on 2018/1/10.
 */

public class LoaderPresenter implements LoaderManager.LoaderCallbacks<Cursor> {
    private LoaderActivity mActivity;
    private Context mContext;
    private LoaderManager loaderManager;

    public static final int LOAD_ALL = 0;
    public static final int LOAD_IMAGE = 1;
    public static final int LOAD_VIDEO = 2;
    //    public static String mBucketId = String.valueOf(Integer.MIN_VALUE);
    public boolean mHasMore = true;
    public static final int LIMIT = 23;
    private int mPage;
    private static final String[] ALL_PROJECTION = {
            MediaStore.Files.FileColumns.DATA,
            MediaStore.Files.FileColumns.SIZE,
            MediaStore.Files.FileColumns.MIME_TYPE,
            MediaStore.Files.FileColumns.DATE_ADDED,
            MediaStore.Video.Media.DURATION
    };

    private static final String[] IMAGE_PROJECTION = {
            MediaStore.Video.Media.DATA,
            MediaStore.Video.Media.SIZE,
            MediaStore.Video.Media.MIME_TYPE,
            MediaStore.Video.Media.DATE_ADDED

    };
    private static final String[] VIDEO_PROJECTION = {
            MediaStore.Video.Media.DATA,
            MediaStore.Video.Media.SIZE,
            MediaStore.Video.Media.MIME_TYPE,
            MediaStore.Video.Media.DATE_ADDED
    };


    public LoaderPresenter(Context context, LoaderManager loaderManager, LoaderActivity activity) {
        this.mContext = context;
        this.loaderManager = loaderManager;
        mActivity = activity;
    }

    public void loadData(int type) {
//        mPage = page;
        if (type == LOAD_ALL) {
            loaderManager.restartLoader(LOAD_ALL, null, this);
        } else if (type == LOAD_IMAGE) {
            loaderManager.restartLoader(LOAD_IMAGE, null, this);
        } else if (type == LOAD_VIDEO) {
            loaderManager.restartLoader(LOAD_VIDEO, null, this);
        }
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        Uri baseUri;
        String selectImage = IMAGE_PROJECTION[1] + ">0 and " + IMAGE_PROJECTION[2] + "=? or "
                + IMAGE_PROJECTION[2] + "=?";
        String selectVideo = VIDEO_PROJECTION[1] + ">0 and " + VIDEO_PROJECTION[2] + "=? or "
                + VIDEO_PROJECTION[2] + "=?";
        String selectAll = "(" + MediaStore.Files.FileColumns.MEDIA_TYPE + "="
                + MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE
                + " OR "
                + MediaStore.Files.FileColumns.MEDIA_TYPE + "="
                + MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO + ") and ((" + selectImage + ") or"
                + "(" + selectVideo + "))";
        if (id == LOAD_ALL) {
//            baseUri = MediaStore.Files.getContentUri("external");
//            String select = ALL_PROJECTION[1] + ">0 and " + ALL_PROJECTION[2] + "=? or "
//                    + ALL_PROJECTION[2] + "=? or "
//                    + ALL_PROJECTION[2] + "=?";
//            String[] selectArgs = {"image/jpeg", "image/png", "video/mp4"};
//            String sort = ALL_PROJECTION[3] + " DESC " ;
//            try {
//                CursorLoader loader = new CursorLoader(mContext, baseUri, ALL_PROJECTION, select, selectArgs, sort);
//                return loader;
//            } catch (Exception e) {
//                int a = 0;
//                a = 1;
//            }

            baseUri = MediaStore.Files.getContentUri("external");

            String[] selectArgs = {"image/jpeg", "image/png", "video/mp4"};
            String sort = MediaStore.Files.FileColumns.DATE_ADDED + " DESC ";
//            String sort = MediaStore.Files.FileColumns.DATE_ADDED + " DESC LIMIT " + LIMIT + " OFFSET " + LIMIT * mPage;

            CursorLoader cursorLoader = new CursorLoader(
                    mContext,
                    baseUri,
                    ALL_PROJECTION,
                    selectAll,
                    selectArgs, // Selection args (none).
                    sort // Sort order.
            );
            return cursorLoader;
        } else if (id == LOAD_IMAGE) {
            baseUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            String[] selectArgs = {"image/jpeg", "image/png"};
            String sort = IMAGE_PROJECTION[3] + " DESC";
//            String sort = IMAGE_PROJECTION[3] + " DESC LIMIT " + LIMIT + " OFFSET " + LIMIT * mPage;
            return new CursorLoader(mContext, baseUri, IMAGE_PROJECTION, selectImage, selectArgs, sort);
        } else if (id == LOAD_VIDEO) {
            baseUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
            String[] selectArgs = {"video/mp4"};
            String sort = VIDEO_PROJECTION[3] + " DESC";
//            String sort = VIDEO_PROJECTION[3] + " DESC LIMIT " + LIMIT + " OFFSET " + LIMIT * mPage;
            return new CursorLoader(mContext, baseUri, VIDEO_PROJECTION, selectVideo, selectArgs, sort);

        }
        return null;

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        int id = loader.getId();
        List files = null;
        if (id == LOAD_ALL) {
            files = getAllFile(data);
        } else if (id == LOAD_IMAGE) {
            files = getImageFile(data);
        } else if (id == LOAD_VIDEO) {
            files = getVideoFile(data);
        }

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

    public List getAllFile(Cursor cursor) {
        List<MediaEntity> result = null;

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                result = new ArrayList<>();
                do {
                    MediaEntity entity = new MediaEntity();
                    entity.setLocalPath(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATA)));
                    entity.setMimeType(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.MIME_TYPE)));
                    entity.setSize(cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.SIZE)));
                    entity.setDateAdded(cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATE_ADDED)));
                    result.add(entity);
                } while (cursor.moveToNext());
            }
        }
        return result;
    }


}
