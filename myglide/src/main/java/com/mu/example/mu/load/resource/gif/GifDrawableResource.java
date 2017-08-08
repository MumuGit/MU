package com.mu.example.mu.load.resource.gif;

import com.mu.example.mu.load.engine.Initializable;
import com.mu.example.mu.load.resource.drawable.DrawableResource;
/**
 * tnormal
 */
/**
 * A resource wrapping an {@link com.mu.example.mu.load.resource.gif.GifDrawable}.
 */
public class GifDrawableResource extends DrawableResource<GifDrawable>
    implements Initializable {
  public GifDrawableResource(GifDrawable drawable) {
    super(drawable);
  }

  @Override
  public Class<GifDrawable> getResourceClass() {
    return GifDrawable.class;
  }

  @Override
  public int getSize() {
   return drawable.getSize();
  }

  @Override
  public void recycle() {
    drawable.stop();
    drawable.recycle();
  }

  @Override
  public void initialize() {
    drawable.getFirstFrame().prepareToDraw();
  }
}
