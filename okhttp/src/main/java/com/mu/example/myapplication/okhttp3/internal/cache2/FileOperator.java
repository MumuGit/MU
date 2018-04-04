package com.mu.example.myapplication.okhttp3.internal.cache2;

/**
 * Created by mu on 2018/3/19.
 */

import java.io.IOException;
import java.nio.Buffer;
import java.nio.channels.FileChannel;

/**
 * Read and write a target file. Unlike Okio's built-in {@linkplain Okio#source(java.io.File) file
 * source} and {@linkplain Okio#sink(java.io.File) file sink} this class offers:
 * <p>
 * <ul>
 * <li><strong>Read/write:</strong> read and write using the same operator.
 * <li><strong>Random access:</strong> access any position within the file.
 * <li><strong>Shared channels:</strong> read and write a file channel that's shared between
 * multiple operators. Note that although the underlying {@code FileChannel} may be shared,
 * each {@code FileOperator} should not be.
 * </ul>
 */
final class FileOperator {
    private final FileChannel fileChannel;

    FileOperator(FileChannel fileChannel) {
        this.fileChannel = fileChannel;
    }

    /**
     * Write {@code byteCount} bytes from {@code source} to the file at {@code pos}.
     */
    public void write(long pos, Buffer source, long byteCount) throws IOException {
        if (byteCount < 0 || byteCount > source.size()) throw new IndexOutOfBoundsException();
        while (byteCount > 0L) {
            long bytesWritten = fileChannel.transferFrom(source, pos, byteCount);
            pos += bytesWritten;
            byteCount -= bytesWritten;
        }
    }

    /**
     * Copy {@code byteCount} bytes from the file at {@code pos} into to {@code source}. It is the
     * caller's responsibility to make sure there are sufficient bytes to read: if there aren't this
     * method throws an {@link EOFException}.
     */
    public void read(long pos, Buffer sink, long byteCount) throws IOException {
        if (byteCount < 0) throw new IndexOutOfBoundsException();

        while (byteCount > 0L) {
            long bytesRead = fileChannel.transferTo(pos, byteCount, sink);
            pos += bytesRead;
            byteCount -= bytesRead;
        }
    }
}
