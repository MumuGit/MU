package com.mu.example.myapplication.action.feature.video_player.surfaceview;

import android.content.Context;
import android.view.TextureView;

/**
 * Created by mu on 2018/3/16.
 */

public class CustomedTextureView extends TextureView {
    private int videoHeight;
    private int videoWidth;

    public CustomedTextureView(Context context) {
        super(context);
    }

    public void adaptVideoSize(int videoWidth, int videoHeight) {
        if (this.videoHeight != videoHeight || this.videoWidth != videoWidth) {
            this.videoWidth = videoWidth;
            this.videoHeight = videoHeight;
            requestLayout();
        }
    }

    @Override
    public void setRotation(float rotation) {
        if (rotation != getRotation()) {
            super.setRotation(rotation);
            requestLayout();
        }
    }


}
