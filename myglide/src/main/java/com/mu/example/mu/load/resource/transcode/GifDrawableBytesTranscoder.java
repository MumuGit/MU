package com.mu.example.mu.load.resource.transcode;

import com.mu.example.mu.load.engine.Resource;
import com.mu.example.mu.load.resource.bytes.BytesResource;
import com.mu.example.mu.load.resource.gif.GifDrawable;
import com.mu.example.mu.util.ByteBufferUtil;

import java.nio.ByteBuffer;
/**
 * tnormal
 */
/**
 * An {@link com.mu.example.mu.load.resource.transcode.ResourceTranscoder} that converts {@link
 * com.mu.example.mu.load.resource.gif.GifDrawable} into bytes by obtaining the original bytes of
 * the GIF from the {@link com.mu.example.mu.load.resource.gif.GifDrawable}.
 */
public class GifDrawableBytesTranscoder implements ResourceTranscoder<GifDrawable, byte[]> {
  @Override
  public Resource<byte[]> transcode(Resource<GifDrawable> toTranscode) {
    GifDrawable gifData = toTranscode.get();
    ByteBuffer byteBuffer = gifData.getBuffer();
    return new BytesResource(ByteBufferUtil.toBytes(byteBuffer));
  }
}
