package com.mu.example.mu.load.data.mediastore;

import android.database.Cursor;
import android.net.Uri;
/**
 * tnormal
 */
interface ThumbnailQuery {
  Cursor query(Uri uri);
}
