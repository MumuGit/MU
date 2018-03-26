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

    public static void display(Context context, String url, String title, VideoPlayer view) {
//        url="http://play.g3proxy.lecloud.com/vod/v2/MjUxLzE2LzgvbGV0di11dHMvMTQvdmVyXzAwXzIyLTExMDc2NDEzODctYXZjLTE5OTgxOS1hYWMtNDgwMDAtNTI2MTEwLTE3MDg3NjEzLWY1OGY2YzM1NjkwZTA2ZGFmYjg2MTVlYzc5MjEyZjU4LTE0OTg1NTc2ODY4MjMubXA0?b=259&mmsid=65565355&tm=1499247143&key=f0eadb4f30c404d49ff8ebad673d3742&platid=3&splatid=345&playid=0&tss=no&vtype=21&cvid=2026135183914&payff=0&pip=08cc52f8b09acd3eff8bf31688ddeced&format=0&sign=mb&dname=mobile&expect=1&tag=mobile&xformat=super";
        try {
            if (NetUtil.isWebUrl(url)) {

            }
            view.setUp(url, null);
            VideoController controller = new VideoController(context);
            controller.setTitle(title);
            view.setController(controller);
            view.start();

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void display(Context context, String url, VideoPlayer view) {
        display(context, url, "", view);
    }


    public static void savePlayPosition(String url, long position) {
        App.getApplication().getSharedPreferences(VIDEO_SP_NAME, Context.MODE_PRIVATE)
                .edit().putLong(url, position).apply();
    }

    public static long getSavedPlayPosition(String url) {
        return App.getApplication().getSharedPreferences(VIDEO_SP_NAME, Context.MODE_PRIVATE).getLong(url, 0);
    }
}
