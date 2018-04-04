package com.mu.example.myapplication.okhttp3.internal.cache;

/**
 * Created by mu on 2018/3/19.
 */

import java.io.IOException;
import java.nio.Buffer;

/**
 * A sink that never throws IOExceptions, even if the underlying sink does.
 */
class FaultHidingSink extends ForwordingSink {
    private boolean hasErrors;

    FaultHidingSink(Sink delegate) {
        super(delegate);
    }

    @Override
    public void write(Buffer source, long byteCount) throws IOException {
        if (hasErrors) {
            source.skip(byteCount);
            return;
        }
        try {
            super.write(source, byteCount);
        } catch (IOException e) {
            hasErrors = true;
            onException(e);
        }
    }

    @Override
    public void flush() throws IOException {
        if (hasErrors) return;
        try {
            super.flush();
        } catch (IOException e) {
            hasErrors = true;
            onException(e);
        }
    }

    @Override
    public void close() throws IOException {
        if (hasErrors) return;
        try {
            super.close();
        } catch (IOException e) {
            hasErrors = true;
            onException(e);
        }
    }

    protected void onException(IOException e) {
    }
}
