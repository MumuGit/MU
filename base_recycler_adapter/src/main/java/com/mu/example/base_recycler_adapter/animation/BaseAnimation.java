package com.mu.example.base_recycler_adapter.animation;

import android.animation.Animator;
import android.view.View;

public interface BaseAnimation {
    Animator[] getAnimators(View view);
}
