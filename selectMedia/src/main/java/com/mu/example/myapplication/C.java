package com.mu.example.myapplication;

/**
 * Created by mu on 2018/3/6.
 */

public class C {
    public static class RequestCode {
        public static int REQUEST_CODE_MEDIA_PICK = 0;
    }

    public static class MediaPicker {
        public static String SELECTABLE_MAX_COUNT = "SELECTABLE_MAX_COUNT";
        public static String SHOW_CAMERA = "SHOW_CAMERA";
        public static String SELECT_TAG = "SELECT_TAG";
        public static String REPLACE = "REPLACE";
        public static String PREVIEW_MEDIA_DATA = "PREVIEW_MEDIA_DATA";
        public static String PREVIEW_MEDIA_DATA_INDEX = "PREVIEW_MEDIA_DATA_INDEX";
        public static String PREVIEW_MEDIA_TYPE = "PREVIEW_MEDIA_TYPE";
        public static boolean DEAULT_SHOW_CAMERA = true;
        public static boolean DEAULT_REPLACE = false;
        public static int DEAULT_SELECTABLE_MAX_COUNT = 9;
        public final static String DEFAULT_SELECT_MEDIA_TAG = "DEFAULT";
        public final static int EXCEED_MAX_COUNT = 1;
        public final static int SUCCESS_ADD = 0;
        public final static int MEDIA_PICKER_TYPE_CAMERA = 0;
        public final static int MEDIA_PICKER_TYPE_MEDIA = 1;
        public final static String VIDEO_URL = "VIDEO_URL";
    }

    public static class ImageOpreator {
        public static final float DEFAULT_MIN_SCALE = 1.0f;
        public static final float DEFAULT_MID_SCALE = 1.75f;
        public static final float DEFAULT_MAX_SCALE = 3.0f;
        public static final int DEFAULT_ZOOM_DURATION = 200;
    }
}
