package com.mu.example.myapplication.util;

import android.content.Context;

import com.mu.example.myapplication.App;
import com.mu.example.myapplication.action.feature.video_player.VideoController;
import com.mu.example.myapplication.action.feature.video_player.VideoPlayer;

/**
 * Created by mu on 2018/3/12.
 */

public class VideoUtil {
    private static String VIDEO_SP_NAME = "VIDEO_SP";

    public static void display(Context context, String url, VideoPlayer view) {
        try {
            if (NetUtil.isWebUrl(url)) {

            }
            view.setUp(url, null);
            VideoController controller = new VideoController(context);
            view.setController(controller);
            view.start();

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void savePlayPosition(String url, long position) {
        App.getApplication().getSharedPreferences(VIDEO_SP_NAME, Context.MODE_PRIVATE)
                .edit().putLong(url, position).apply();
    }

    public static long getSavedPlayPosition(String url) {
        return App.getApplication().getSharedPreferences(VIDEO_SP_NAME, Context.MODE_PRIVATE).getLong(url, 0);
    }
}
