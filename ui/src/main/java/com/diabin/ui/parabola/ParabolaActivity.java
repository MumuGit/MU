package com.diabin.ui.parabola;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.diabin.ui.R;

public class ParabolaActivity extends AppCompatActivity {
    ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parabola);
        imageView = findViewById(R.id.iv);
        ParabolaAnimator animator = new ParabolaAnimator();
        animator.start(imageView);
    }
}
