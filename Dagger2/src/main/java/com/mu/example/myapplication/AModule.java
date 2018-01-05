package com.mu.example.myapplication;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mu on 2018/1/4.
 */
@Module
public class AModule {
    @PoetryQualifier("A")
    @AScope
    @Provides
    public Poetry getPoetry() {
        return new Poetry("万物美好");
    }
    @PoetryQualifier("B")
    @AScope
    @Provides
    public Poetry getOtherPoetry(){
        return new Poetry("我在中间");
    }
}
