package com.mu.example.mu.module;

import android.content.Context;

import com.mu.example.mu.Registry;


/**
 * An internal interface, to be removed when {@link GlideModule}s are removed.
 */
interface RegistersComponents {

  /**
   * Lazily register components immediately after the Glide singleton is created but before any
   * requests can be started.
   *
   * <p> This method will be called once and only once per implementation. </p>
   *
   * @param context  An Application {@link Context}.
   * @param registry An {@link com.mu.example.mu.Registry} to use to register components.
   */
  void registerComponents(Context context, Registry registry);
}
