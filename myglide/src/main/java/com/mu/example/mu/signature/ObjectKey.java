package com.mu.example.mu.signature;

import com.mu.example.mu.load.Key;
import com.mu.example.mu.util.Preconditions;

import java.security.MessageDigest;
/**
 * tnormal
 */
/**
 * Wraps an {@link Object}, delegating {@link #equals(Object)} and {@link #hashCode()} to
 * the wrapped Object and providing the bytes of the result of the Object's {@link #toString()}
 * method to the {@link MessageDigest} in
 * {@link #updateDiskCacheKey(MessageDigest)}.
 *
 * <p>The Object's {@link #toString()} method must be unique and suitable for use as a disk cache
 * key.</p>
 */
public final class ObjectKey implements Key {
  private final Object object;

  public ObjectKey(Object object) {
    this.object = Preconditions.checkNotNull(object);
  }

  @Override
  public String toString() {
    return "ObjectKey{"
        + "object=" + object
        + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof ObjectKey) {
      ObjectKey other = (ObjectKey) o;
      return object.equals(other.object);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return object.hashCode();
  }

  @Override
  public void updateDiskCacheKey(MessageDigest messageDigest) {
    messageDigest.update(object.toString().getBytes(CHARSET));
  }
}

