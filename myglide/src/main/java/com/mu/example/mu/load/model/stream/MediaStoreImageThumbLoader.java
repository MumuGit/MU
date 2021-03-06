package com.mu.example.mu.load.model.stream;

import android.content.Context;
import android.net.Uri;

import com.mu.example.mu.load.Options;
import com.mu.example.mu.load.data.mediastore.MediaStoreUtil;
import com.mu.example.mu.load.data.mediastore.ThumbFetcher;
import com.mu.example.mu.load.model.ModelLoader;
import com.mu.example.mu.load.model.ModelLoaderFactory;
import com.mu.example.mu.load.model.MultiModelLoaderFactory;
import com.mu.example.mu.signature.ObjectKey;

import java.io.InputStream;
/**
 * tnormal
 */
/**
 * Loads {@link InputStream}s from media store image {@link Uri}s that point to pre-generated
 * thumbnails for those {@link Uri}s in the media store.
 */
public class MediaStoreImageThumbLoader implements ModelLoader<Uri, InputStream> {
  public final Context context;

  public MediaStoreImageThumbLoader(Context context) {
    this.context = context.getApplicationContext();
  }

  @Override
  public LoadData<InputStream> buildLoadData(Uri model, int width, int height, Options options) {
    if (MediaStoreUtil.isThumbnailSize(width, height)) {
      return new LoadData<>(new ObjectKey(model), ThumbFetcher.buildImageFetcher(context, model));
    } else {
      return null;
    }
  }

  @Override
  public boolean handles(Uri model) {
    return MediaStoreUtil.isMediaStoreImageUri(model);
  }

  /**
   * Factory that loads {@link InputStream}s from media store image {@link Uri}s.
   */
  public static class Factory implements ModelLoaderFactory<Uri, InputStream> {

    private final Context context;

    public Factory(Context context) {
      this.context = context;
    }

    @Override
    public ModelLoader<Uri, InputStream> build(MultiModelLoaderFactory multiFactory) {
      return new MediaStoreImageThumbLoader(context);
    }

    @Override
    public void teardown() {
      // Do nothing.
    }
  }
}
