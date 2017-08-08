package com.mu.example.mu;

import com.mu.example.mu.base.ObjectPool;

/**
 * Created by mu on 2017/5/31.
 */

public class MPPointF extends ObjectPool.Poolable{
    public float x;
    public float y;

    public MPPointF() {

    }

    public MPPointF(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX(){
        return this.x;
    }

    public float getY(){
        return this.y;
    }

    @Override
    protected ObjectPool.Poolable instantiate() {
        return new MPPointF(0,0);
    }
}
