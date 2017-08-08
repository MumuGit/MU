package com.mu.example.mu.load.resource.file;

import com.mu.example.mu.load.resource.SimpleResource;

import java.io.File;
/**
 * tnormal
 */
/**
 * A simple {@link com.mu.example.mu.load.engine.Resource} that wraps a {@link File}.
 */
public class FileResource extends SimpleResource<File> {
  public FileResource(File file) {
    super(file);
  }
}
