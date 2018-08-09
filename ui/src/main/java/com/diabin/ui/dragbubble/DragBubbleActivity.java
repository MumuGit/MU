package com.diabin.ui.dragbubble;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.diabin.ui.R;
import com.diabin.ui.parabola.ParabolaAnimator;

public class DragBubbleActivity extends AppCompatActivity {
    ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dragbubbleview);

    }
}
