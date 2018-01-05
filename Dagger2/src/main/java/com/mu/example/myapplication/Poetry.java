package com.mu.example.myapplication;

import javax.inject.Inject;

/**
 * Created by mu on 2018/1/3.
 */

public class Poetry {
    private String mPemo;

    // 用Inject标记构造函数,表示用它来注入到目标对象中去
    @Inject
    public Poetry() {
        mPemo = "生活就像海洋";
    }

    public Poetry(String s) {
        mPemo = s;
    }

    public String getPemo() {
        return mPemo;
    }
}
