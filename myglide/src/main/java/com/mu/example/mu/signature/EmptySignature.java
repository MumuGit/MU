package com.mu.example.mu.signature;

import com.mu.example.mu.load.Key;

import java.security.MessageDigest;
/**
 * tnormal
 */
/**
 * An empty key that is always equal to all other empty keys.
 */
public final class EmptySignature implements Key {
  private static final EmptySignature EMPTY_KEY = new EmptySignature();

  public static EmptySignature obtain() {
    return EMPTY_KEY;
  }

  private EmptySignature() {
    // Empty.
  }

  @Override
  public String toString() {
    return "EmptySignature";
  }

  @Override
  public void updateDiskCacheKey(MessageDigest messageDigest) {
    // Do nothing.
  }
}
