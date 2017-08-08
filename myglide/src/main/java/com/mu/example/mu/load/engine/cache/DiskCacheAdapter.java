package com.mu.example.mu.load.engine.cache;

import com.mu.example.mu.load.Key;

import java.io.File;
/**
 * tnormal
 */

/**
 * A simple class that returns null for all gets and ignores all writes.
 */
public class DiskCacheAdapter implements DiskCache {
  @Override
  public File get(Key key) {
    // no op, default for overriders
    return null;
  }

  @Override
  public void put(Key key, Writer writer) {
    // no op, default for overriders
  }

  @Override
  public void delete(Key key) {
    // no op, default for overriders
  }

  @Override
  public void clear() {
      // no op, default for overriders
  }
}
