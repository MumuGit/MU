package com.mu.example.mu.load.resource.file;

import com.mu.example.mu.load.Options;
import com.mu.example.mu.load.ResourceDecoder;
import com.mu.example.mu.load.engine.Resource;

import java.io.File;
/**
 * tnormal
 */

/**
 * A simple {@link com.mu.example.mu.load.ResourceDecoder} that creates resource for a given {@link
 * File}.
 */
public class FileDecoder implements ResourceDecoder<File, File> {

  @Override
  public boolean handles(File source, Options options) {
    return true;
  }

  @Override
  public Resource<File> decode(File source, int width, int height, Options options) {
    return new FileResource(source);
  }
}
