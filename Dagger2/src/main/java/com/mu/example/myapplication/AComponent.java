package com.mu.example.myapplication;

import dagger.Subcomponent;

/**
 * Created by mu on 2018/1/4.
 */
@AScope
@Subcomponent(modules = AModule.class)
public interface AComponent {
    void inject(AActivity activity);
}
