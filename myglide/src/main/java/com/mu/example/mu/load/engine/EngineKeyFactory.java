package com.mu.example.mu.load.engine;

import com.mu.example.mu.load.Key;
import com.mu.example.mu.load.Options;
import com.mu.example.mu.load.Transformation;

import java.util.Map;

/**
 * tnormal
 */
class EngineKeyFactory {

  @SuppressWarnings("rawtypes")
  public EngineKey buildKey(Object model, Key signature, int width, int height,
      Map<Class<?>, Transformation<?>> transformations, Class<?> resourceClass,
      Class<?> transcodeClass, Options options) {
    return new EngineKey(model, signature, width, height, transformations, resourceClass,
        transcodeClass, options);
  }
}
