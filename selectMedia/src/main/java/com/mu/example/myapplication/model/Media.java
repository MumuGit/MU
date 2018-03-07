package com.mu.example.myapplication.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

/**
 * Created by mu on 2018/1/11.
 */

public class Media implements Parcelable {

    protected long id;
    protected String displayName;
    protected String localPath;
    protected String parentPath;
    protected String cropPath;
    protected String compressPath;
    protected String loadPath;
    protected int size;
    protected int mediaType;
    protected String mimeType;
    protected long dateAdded;
    protected long duration;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    public String getParentPath() {
        return parentPath;
    }

    public void setParentPath(String parentPath) {
        this.parentPath = parentPath;
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

    public int getMediaType() {
        return mediaType;
    }

    public void setMediaType(int mediaType) {
        this.mediaType = mediaType;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public long getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(long dateAdded) {
        this.dateAdded = dateAdded;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.displayName);
        dest.writeString(this.localPath);
        dest.writeString(this.parentPath);
        dest.writeString(this.cropPath);
        dest.writeString(this.compressPath);
        dest.writeString(this.loadPath);
        dest.writeInt(this.size);
        dest.writeInt(this.mediaType);
        dest.writeString(this.mimeType);
        dest.writeLong(this.dateAdded);
        dest.writeLong(this.duration);
    }

    public Media() {
    }

    protected Media(Parcel in) {
        this.id = in.readLong();
        this.displayName = in.readString();
        this.localPath = in.readString();
        this.parentPath = in.readString();
        this.cropPath = in.readString();
        this.compressPath = in.readString();
        this.loadPath = in.readString();
        this.size = in.readInt();
        this.mediaType = in.readInt();
        this.mimeType = in.readString();
        this.dateAdded = in.readLong();
        this.duration = in.readLong();
    }

    public static final Parcelable.Creator<Media> CREATOR = new Parcelable.Creator<Media>() {
        @Override
        public Media createFromParcel(Parcel source) {
            return new Media(source);
        }

        @Override
        public Media[] newArray(int size) {
            return new Media[size];
        }
    };

    @Override
    public boolean equals(Object o) {
        try {
            Media other = (Media) o;
            return TextUtils.equals(this.localPath, other.localPath);
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return super.equals(o);
    }
}
