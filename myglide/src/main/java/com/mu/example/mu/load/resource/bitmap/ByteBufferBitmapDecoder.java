package com.mu.example.mu.load.resource.bitmap;
/**
 * tnormal
 */
import android.graphics.Bitmap;

import com.mu.example.mu.load.Options;
import com.mu.example.mu.load.ResourceDecoder;
import com.mu.example.mu.load.engine.Resource;
import com.mu.example.mu.util.ByteBufferUtil;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/**
 * Decodes {@link Bitmap Bitmaps} from {@link ByteBuffer ByteBuffers}.
 */
public class ByteBufferBitmapDecoder implements ResourceDecoder<ByteBuffer, Bitmap> {
  private final Downsampler downsampler;

  public ByteBufferBitmapDecoder(Downsampler downsampler) {
    this.downsampler = downsampler;
  }

  @Override
  public boolean handles(ByteBuffer source, Options options) throws IOException {
    return downsampler.handles(source);
  }

  @Override
  public Resource<Bitmap> decode(ByteBuffer source, int width, int height, Options options)
      throws IOException {
    InputStream is = ByteBufferUtil.toStream(source);
    return downsampler.decode(is, width, height, options);
  }
}
