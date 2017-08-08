package com.mu.example.mu.base;

/**
 * Created by mu on 2017/5/31.
 */

public class ObjectPool {

    public static abstract class Poolable{

        public static int NO_OWNER = -1;
        int currentOwnerId = NO_OWNER;

        protected abstract Poolable instantiate();

    }
}
