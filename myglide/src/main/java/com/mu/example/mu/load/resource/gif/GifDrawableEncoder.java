package com.mu.example.mu.load.resource.gif;

import android.util.Log;

import com.mu.example.mu.load.EncodeStrategy;
import com.mu.example.mu.load.Options;
import com.mu.example.mu.load.ResourceEncoder;
import com.mu.example.mu.load.engine.Resource;
import com.mu.example.mu.util.ByteBufferUtil;

import java.io.File;
import java.io.IOException;
/**
 * tnormal
 */
/**
 * Writes the original bytes of a {@link com.mu.example.mu.load.resource.gif.GifDrawable} to an
 * {@link java.io.OutputStream}.
 */
public class GifDrawableEncoder implements ResourceEncoder<GifDrawable> {
  private static final String TAG = "GifEncoder";

  @Override
  public EncodeStrategy getEncodeStrategy(Options options) {
    return EncodeStrategy.SOURCE;
  }

  @Override
  public boolean encode(Resource<GifDrawable> data, File file, Options options) {
    GifDrawable drawable = data.get();
    boolean success = false;
    try {
      ByteBufferUtil.toFile(drawable.getBuffer(), file);
      success = true;
    } catch (IOException e) {
      if (Log.isLoggable(TAG, Log.WARN)) {
        Log.w(TAG, "Failed to encode GIF drawable data", e);
      }
    }
    return success;
  }
}
