package com.mu.example.mu.module;

import android.content.Context;

import com.mu.example.mu.GlideBuilder;


/**
 * An internal interface, to be removed when {@link GlideModule}s are removed.
 */
interface AppliesOptions {
  /**
   * Lazily apply options to a {@link com.mu.example.mu.GlideBuilder} immediately before the Glide
   * singleton is created.
   *
   * <p> This method will be called once and only once per implementation. </p>
   *
   * @param context An Application {@link Context}.
   * @param builder The {@link com.mu.example.mu.GlideBuilder} that will be used to create Glide.
   */
  void applyOptions(Context context, GlideBuilder builder);
}
