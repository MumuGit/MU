package com.mu.example.mu.load.resource.gif;

import android.graphics.Bitmap;

import com.mu.example.mu.gifdecoder.GifDecoder;
import com.mu.example.mu.load.Options;
import com.mu.example.mu.load.ResourceDecoder;
import com.mu.example.mu.load.engine.Resource;
import com.mu.example.mu.load.engine.bitmap_recycle.BitmapPool;
import com.mu.example.mu.load.resource.bitmap.BitmapResource;
/**
 * tnormal
 */
/**
 * Decodes {@link Bitmap}s from {@link GifDecoder}s representing a particular frame of a particular
 * GIF image.
 */
public final class GifFrameResourceDecoder implements ResourceDecoder<GifDecoder, Bitmap> {
  private final BitmapPool bitmapPool;

  public GifFrameResourceDecoder(BitmapPool bitmapPool) {
    this.bitmapPool = bitmapPool;
  }

  @Override
  public boolean handles(GifDecoder source, Options options) {
    return true;
  }

  @Override
  public Resource<Bitmap> decode(GifDecoder source, int width, int height, Options options) {
    Bitmap bitmap = source.getNextFrame();
    return BitmapResource.obtain(bitmap, bitmapPool);
  }
}
