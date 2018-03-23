package com.mu.example.myapplication.action.feature.video_player;

/**
 * Created by mu on 2018/3/22.
 */

public class VideoManager {
    private VideoPlayer mVideoView;

    private VideoManager() {
    }

    private static VideoManager sInstance;

    public static synchronized VideoManager instance() {
        if (sInstance == null) {
            sInstance = new VideoManager();
        }
        return sInstance;
    }

    public VideoPlayer getCurrentVideoView() {
        return mVideoView;
    }

    public void setCurrentVideoView(VideoPlayer videoView) {
        if (mVideoView != videoView) {
            releaseVideoView();
            mVideoView = videoView;
        }
    }

    public void suspendVideoView() {
        if (mVideoView != null && (mVideoView.isPlaying() || mVideoView.isBufferingPlaying())) {
            mVideoView.pause();
        }
    }

    public void resumeVideoView() {
        if (mVideoView != null && (mVideoView.isPaused() || mVideoView.isBufferingPaused())) {
            mVideoView.restart();
        }
    }

    public void releaseVideoView() {
        if (mVideoView != null) {
            mVideoView.release();
            mVideoView = null;
        }
    }

    public boolean onBackPressd() {
        if (mVideoView != null) {
            if (mVideoView.isFullScreen()) {
                return mVideoView.exitFullScreen();
            } else if (mVideoView.isTinyWindow()) {
                return mVideoView.exitTinyWindow();
            }
        }
        return false;
    }
}
