package com.mu.example.myapplication.okhttp3.internal.cache;

import java.io.IOException;

/**
 * Created by mu on 2018/3/19.
 */

public interface CacheRequest {
    Sink body() throws IOException;

    void abort();
}
