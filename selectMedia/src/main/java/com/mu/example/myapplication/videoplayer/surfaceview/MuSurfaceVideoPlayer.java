package com.mu.example.myapplication.videoplayer.surfaceview;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.mu.example.myapplication.R;

import java.io.IOException;

/**
 * Created by mu on 2018/1/15.
 */

public class MuSurfaceVideoPlayer extends FrameLayout implements View.OnClickListener {
    private Context context;
    private ImageView mn_iv_play_pause;
    private SurfaceView mn_palyer_surfaceView;
    private TextureView textureView;
    // SurfaceView的创建比较耗时，要注意
    private SurfaceHolder surfaceHolder;
    private MediaPlayer mediaPlayer;
    private String videoPath;

    public MuSurfaceVideoPlayer(@NonNull Context context) {
        this(context, null);
    }

    public MuSurfaceVideoPlayer(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    private void init() {
        View inflate = View.inflate(context, R.layout.surface_video_view, this);
        mn_iv_play_pause = (ImageView) inflate.findViewById(R.id.mn_iv_play_pause);
        mn_iv_play_pause.setOnClickListener(this);
//        mn_palyer_surfaceView = (SurfaceView) inflate.findViewById(R.id.mn_palyer_surfaceView);
        textureView = findViewById(R.id.video_view);
        textureView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
            @Override
            public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setSurface(new Surface(surface));
                try {
                    mediaPlayer.setDataSource(videoPath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

                mediaPlayer.prepareAsync();


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
        });
        //初始化
        initViews();
        //初始化SurfaceView
        //  initSurfaceView();
    }

    private void initViews() {

//        mn_palyer_surfaceView.setAlpha(0);

    }

    /**
     * 设置视频信息
     *
     * @param url   视频地址
     * @param title 视频标题
     */
    public void setDataSource(String url, String title) {
        //赋值
        videoPath = url;
//        setVideoThumbnail();
    }

    private void initSurfaceView() {
        // 得到SurfaceView容器，播放的内容就是显示在这个容器里面
        surfaceHolder = mn_palyer_surfaceView.getHolder();
        surfaceHolder.setKeepScreenOn(true);
        // SurfaceView的一个回调方法
        surfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setDisplay(holder); // 添加到容器中
                try {
                    mediaPlayer.setDataSource(videoPath);
                    // 准备开始,异步准备，自动在子线程中
                    mediaPlayer.prepareAsync();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.mn_iv_play_pause) {
            if (mediaPlayer != null) {
                if (mediaPlayer.isPlaying()) {
                    pauseVideo();
                } else {
                    startVideo();
                }
            }
        }
    }

    /**
     * 暂停视频
     */
    public void pauseVideo() {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
            mn_iv_play_pause.setImageResource(R.mipmap.mn_player_play);

        }
    }

    /**
     * 播放视频
     */
    public void startVideo() {
        if (mediaPlayer != null) {
            mediaPlayer.start();
            mn_iv_play_pause.setImageResource(R.mipmap.mn_player_pause);
        }
    }
}
