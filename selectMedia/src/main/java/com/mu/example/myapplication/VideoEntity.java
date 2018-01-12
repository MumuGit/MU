package com.mu.example.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mu on 2018/1/11.
 */

public class VideoEntity extends MediaEntity implements Parcelable {

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public VideoEntity() {
    }

    protected VideoEntity(Parcel in) {
    }

    public static final Parcelable.Creator<VideoEntity> CREATOR = new Parcelable.Creator<VideoEntity>() {
        @Override
        public VideoEntity createFromParcel(Parcel source) {
            return new VideoEntity(source);
        }

        @Override
        public VideoEntity[] newArray(int size) {
            return new VideoEntity[size];
        }
    };
}
