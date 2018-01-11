package com.mu.example.myapplication;

/**
 * Created by mu on 2018/1/11.
 */

public abstract class MediaEntity {
    public static final String IMAGE = "image";
    protected String mimeType;

    public String getType() {
        return type;
    }

    protected String type;
    protected int size;
    protected long dateAdded;
    protected String localPath;

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
        type = mimeType.substring(0, mimeType.indexOf("/"));
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public long getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(long dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    public String getCropPath() {
        return cropPath;
    }

    public void setCropPath(String cropPath) {
        this.cropPath = cropPath;
    }

    public String getCompressPath() {
        return compressPath;
    }

    public void setCompressPath(String compressPath) {
        this.compressPath = compressPath;
    }

    public String getLoadPath() {
        return loadPath;
    }

    public void setLoadPath(String loadPath) {
        this.loadPath = loadPath;
    }

    protected String cropPath;
    protected String compressPath;
    protected String loadPath;
}
