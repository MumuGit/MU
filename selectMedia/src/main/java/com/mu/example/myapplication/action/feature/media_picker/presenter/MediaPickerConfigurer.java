package com.mu.example.myapplication.action.feature.media_picker.presenter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.mu.example.myapplication.C;
import com.mu.example.myapplication.action.feature.media_picker.LoaderActivity;

/**
 * Created by mu on 2018/3/6.
 */

public class MediaPickerConfigurer {
    private static MediaPickerConfigurer sSelector;
    private boolean showCamera = C.MediaPicker.DEAULT_SHOW_CAMERA;
    private int maxSelectableCount = C.MediaPicker.DEAULT_SELECTABLE_MAX_COUNT;

    public static MediaPickerConfigurer create() {
        if (sSelector == null) {
            sSelector = new MediaPickerConfigurer();
        }
        return sSelector;
    }

    public MediaPickerConfigurer showCamera(boolean show) {
        showCamera = show;
        return sSelector;
    }

    public MediaPickerConfigurer count(int count) {
        maxSelectableCount = count;
        return sSelector;
    }


    public void start(Activity activity, int requestCode, String tag) {
        activity.startActivityForResult(createIntent(activity, tag), requestCode);
    }

    public void start(Fragment fragment, int requestCode, String tag) {
        fragment.startActivityForResult(createIntent(fragment, tag), requestCode);
    }

    private Intent createIntent(Activity context, String tag) {
        Intent intent = new Intent(context, LoaderActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(C.MediaPicker.SELECT_TAG, tag);
        bundle.putBoolean(C.MediaPicker.SHOW_CAMERA, showCamera);
        bundle.putInt(C.MediaPicker.SELECTABLE_MAX_COUNT, maxSelectableCount);
        intent.putExtras(bundle);
        return intent;
    }

    private Intent createIntent(Fragment context, String tag) {
        Intent intent = new Intent(context.getActivity(), LoaderActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(C.MediaPicker.SELECT_TAG, tag);
        bundle.putBoolean(C.MediaPicker.SHOW_CAMERA, showCamera);
        bundle.putInt(C.MediaPicker.SELECTABLE_MAX_COUNT, maxSelectableCount);
        intent.putExtras(bundle);
        return intent;
    }
}
