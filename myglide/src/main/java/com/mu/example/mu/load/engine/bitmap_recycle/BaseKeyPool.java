package com.mu.example.mu.load.engine.bitmap_recycle;

import com.mu.example.mu.util.Util;

import java.util.Queue;
/**
 * tnormal
 */
abstract class BaseKeyPool<T extends Poolable> {
  private static final int MAX_SIZE = 20;
  private final Queue<T> keyPool = Util.createQueue(MAX_SIZE);

  protected T get() {
    T result = keyPool.poll();
    if (result == null) {
      result = create();
    }
    return result;
  }

  public void offer(T key) {
    if (keyPool.size() < MAX_SIZE) {
      keyPool.offer(key);
    }
  }

  protected abstract T create();
}
