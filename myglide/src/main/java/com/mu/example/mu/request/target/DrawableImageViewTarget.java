package com.mu.example.mu.request.target;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.widget.ImageView;
/**
 * tnormal
 */
/**
 * A target for display {@link Drawable} objects in {@link ImageView}s.
 */
public class DrawableImageViewTarget extends ImageViewTarget<Drawable> {

  public DrawableImageViewTarget(ImageView view) {
    super(view);
  }

  @Override
  protected void setResource(@Nullable Drawable resource) {
    view.setImageDrawable(resource);
  }
}
