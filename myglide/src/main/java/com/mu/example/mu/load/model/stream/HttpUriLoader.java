package com.mu.example.mu.load.model.stream;

import android.net.Uri;

import com.mu.example.mu.load.Options;
import com.mu.example.mu.load.model.GlideUrl;
import com.mu.example.mu.load.model.ModelLoader;
import com.mu.example.mu.load.model.ModelLoaderFactory;
import com.mu.example.mu.load.model.MultiModelLoaderFactory;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
/**
 * tnormal
 */
/**
 * Loads {@link InputStream}s from http or https {@link Uri}s.
 */
public class HttpUriLoader implements ModelLoader<Uri, InputStream> {
  private static final Set<String> SCHEMES =
      Collections.unmodifiableSet(new HashSet<>(Arrays.asList("http", "https")));

  private final ModelLoader<GlideUrl, InputStream> urlLoader;

  public HttpUriLoader(ModelLoader<GlideUrl, InputStream> urlLoader) {
    this.urlLoader = urlLoader;
  }

  @Override
  public LoadData<InputStream> buildLoadData(Uri model, int width, int height, Options options) {
    return urlLoader.buildLoadData(new GlideUrl(model.toString()), width, height, options);
  }

  @Override
  public boolean handles(Uri model) {
    return SCHEMES.contains(model.getScheme());
  }

  /**
   * Factory for loading {@link InputStream}s from http/https {@link Uri}s.
   */
  public static class Factory implements ModelLoaderFactory<Uri, InputStream> {

    @Override
    public ModelLoader<Uri, InputStream> build(MultiModelLoaderFactory multiFactory) {
      return new HttpUriLoader(multiFactory.build(GlideUrl.class, InputStream.class));
    }

    @Override
    public void teardown() {
      // Do nothing.
    }
  }
}
