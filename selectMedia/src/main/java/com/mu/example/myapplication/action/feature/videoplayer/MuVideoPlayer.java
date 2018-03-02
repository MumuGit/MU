package com.mu.example.myapplication.action.feature.videoplayer;

import android.content.Context;
import android.graphics.Color;
import android.graphics.SurfaceTexture;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.io.IOException;
import java.util.Map;

/**
 * Created by mu on 2018/1/15.
 */

public class MuVideoPlayer extends FrameLayout implements
        TextureView.SurfaceTextureListener {
    private Context mContext;
    private FrameLayout mContainer;
    private SurfaceTexture mSurfaceTexture;
    private TextureView mTextureView;
    private MediaPlayer mMediaPlayer;
    private String mUrl;
    private Map<String, String> mHeaders;
    private int mCurrentState = STATE_IDLE;
    /**
     * 播放错误
     **/
    public static final int STATE_ERROR = -1;
    /**
     * 播放未开始
     **/
    public static final int STATE_IDLE = 0;
    /**
     * 播放准备中
     **/
    public static final int STATE_PREPARING = 1;
    /**
     * 播放准备就绪
     **/
    public static final int STATE_PREPARED = 2;
    /**
     * 正在播放
     **/
    public static final int STATE_PLAYING = 3;
    /**
     * 暂停播放
     **/
    public static final int STATE_PAUSED = 4;
    /**
     * 正在缓冲(播放器正在播放时，缓冲区数据不足，进行缓冲，缓冲区数据足够后恢复播放)
     **/
    public static final int STATE_BUFFERING_PLAYING = 5;
    /**
     * 正在缓冲(播放器正在播放时，缓冲区数据不足，进行缓冲，此时暂停播放器，继续缓冲，缓冲区数据足够后恢复暂停
     **/
    public static final int STATE_BUFFERING_PAUSED = 6;
    /**
     * 播放完成
     **/
    public static final int STATE_COMPLETED = 7;

    /**
     * 普通模式
     **/
    public static final int MODE_NORMAL = 10;
    /**
     * 全屏模式
     **/
    public static final int MODE_FULL_SCREEN = 11;
    /**
     * 小窗口模式
     **/
    public static final int MODE_TINY_WINDOW = 12;
    private AudioManager mAudioManager;
    private SurfaceView mn_palyer_surfaceView;

    public MuVideoPlayer(@NonNull Context context) {
        this(context, null);
    }

    public MuVideoPlayer(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }


    private void init() {
        mContainer = new FrameLayout(mContext);
        mContainer.setBackgroundColor(Color.BLACK);
        LayoutParams params = new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        this.addView(mContainer, params);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        if (mSurfaceTexture == null) {
            mSurfaceTexture = surface;
            openMediaPlayer();
        } else {
            mTextureView.setSurfaceTexture(mSurfaceTexture);
        }
    }

    private Surface mSurface;

    private void openMediaPlayer() {
        // 屏幕常亮
        mContainer.setKeepScreenOn(true);
        try {
            mMediaPlayer.setDataSource(mContext.getApplicationContext(), Uri.parse(mUrl));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (mSurface == null) {
            mSurface = new Surface(mSurfaceTexture);
        }
        mMediaPlayer.setSurface(mSurface);
        mMediaPlayer.prepareAsync();
        mCurrentState = STATE_PAUSED;

    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        return false;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {

    }

    private void initAudioManager() {
        if (mAudioManager == null) {
            mAudioManager = (AudioManager) getContext().getSystemService(Context.AUDIO_SERVICE);
            mAudioManager.requestAudioFocus(null, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);//?
        }
    }

    private void initMediaPlayer() {
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
    }



}
