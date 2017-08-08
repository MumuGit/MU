package com.mu.example.mu;
/**
 * tnormal
 */

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.mu.example.mu.manager.RequestManagerRetriever;
import com.mu.example.mu.module.AppGlideModule;

import java.util.Set;

/**
 * A temporary interface to allow {@link AppGlideModule}s to exclude
 * {@link com.mu.example.mu.annotation.GlideModule}s to ease the migration from
 * {@link com.mu.example.mu.annotation.GlideModule}s to Glide's annotation processing system.
 */
@Deprecated
abstract class GeneratedAppGlideModule extends AppGlideModule {
  /**
   * This method can be removed when manifest parsing is no longer supported.
   */
  @Deprecated
  @NonNull
  abstract Set<Class<?>> getExcludedModuleClasses();

  @Nullable
  RequestManagerRetriever.RequestManagerFactory getRequestManagerFactory() {
    return null;
  }
}
