package com.mu.example.mu.module;

import android.content.Context;

import com.mu.example.mu.Registry;


/**
 * Registers a set of components to use when initializing Glide within an app when
 * Glide's annotation processor is used.
 *
 * <p>Any number of LibraryGlideModules can be contained within any library or application.
 *
 * <p>LibraryGlideModules are called in no defined order. If LibraryGlideModules within an
 * application conflict, {@link AppGlideModule}s can use the
 * {@link com.mu.example.mu.annotation.Excludes} annotation to selectively remove one or more of
 * the conflicting modules.
 */
public abstract class LibraryGlideModule implements RegistersComponents {
  @Override
  public void registerComponents(Context context, Registry registry) {
    // Default empty impl.
  }
}
