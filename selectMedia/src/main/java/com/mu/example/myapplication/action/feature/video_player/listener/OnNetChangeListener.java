package com.mu.example.myapplication.action.feature.video_player.listener;

import android.media.MediaPlayer;

/**
 * Created by mu on 2018/3/22.
 */

public interface OnNetChangeListener {
    //wifi
    void onWifi(MediaPlayer mediaPlayer);

    //手机
    void onMobile(MediaPlayer mediaPlayer);

    //不可用
    void onNoAvailable(MediaPlayer mediaPlayer);

}
