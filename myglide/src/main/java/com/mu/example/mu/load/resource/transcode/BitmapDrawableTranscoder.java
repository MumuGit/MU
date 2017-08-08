package com.mu.example.mu.load.resource.transcode;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

import com.mu.example.mu.Glide;
import com.mu.example.mu.load.engine.Resource;
import com.mu.example.mu.load.engine.bitmap_recycle.BitmapPool;
import com.mu.example.mu.load.resource.bitmap.LazyBitmapDrawableResource;
import com.mu.example.mu.util.Preconditions;
/**
 * tnormal
 */
/**
 * An {@link com.mu.example.mu.load.resource.transcode.ResourceTranscoder} that converts {@link
 * Bitmap}s into {@link BitmapDrawable}s.
 */
public class BitmapDrawableTranscoder implements ResourceTranscoder<Bitmap, BitmapDrawable> {
  private final Resources resources;
  private final BitmapPool bitmapPool;

  public BitmapDrawableTranscoder(Context context) {
    this(context.getResources(), Glide.get(context).getBitmapPool());
  }

  public BitmapDrawableTranscoder(Resources resources, BitmapPool bitmapPool) {
    this.resources = Preconditions.checkNotNull(resources);
    this.bitmapPool = Preconditions.checkNotNull(bitmapPool);
  }

  @Override
  public Resource<BitmapDrawable> transcode(Resource<Bitmap> toTranscode) {
    return LazyBitmapDrawableResource.obtain(resources, bitmapPool, toTranscode.get());
  }
}
