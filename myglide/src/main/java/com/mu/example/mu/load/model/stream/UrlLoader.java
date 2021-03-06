package com.mu.example.mu.load.model.stream;

import com.mu.example.mu.load.Options;
import com.mu.example.mu.load.model.GlideUrl;
import com.mu.example.mu.load.model.ModelLoader;
import com.mu.example.mu.load.model.ModelLoaderFactory;
import com.mu.example.mu.load.model.MultiModelLoaderFactory;

import java.io.InputStream;
import java.net.URL;
/**
 * tnormal
 */
/**
 * A wrapper class that translates {@link URL} objects into {@link
 * com.mu.example.mu.load.model.GlideUrl} objects and then uses the wrapped {@link
 * com.mu.example.mu.load.model.ModelLoader} for {@link com.mu.example.mu.load.model.GlideUrl}s to
 * load the data.
 */
public class UrlLoader implements ModelLoader<URL, InputStream> {
  private final ModelLoader<GlideUrl, InputStream> glideUrlLoader;

  public UrlLoader(ModelLoader<GlideUrl, InputStream> glideUrlLoader) {
    this.glideUrlLoader = glideUrlLoader;
  }

  @Override
  public LoadData<InputStream> buildLoadData(URL model, int width, int height, Options options) {
    return glideUrlLoader.buildLoadData(new GlideUrl(model), width, height, options);
  }

  @Override
  public boolean handles(URL model) {
    return true;
  }

  /**
   * Factory for loading {@link InputStream}s from {@link URL}s.
   */
  public static class StreamFactory implements ModelLoaderFactory<URL, InputStream> {

    @Override
    public ModelLoader<URL, InputStream> build(MultiModelLoaderFactory multiFactory) {
      return new UrlLoader(multiFactory.build(GlideUrl.class, InputStream.class));
    }

    @Override
    public void teardown() {
      // Do nothing.
    }
  }
}
