package com.mu.example.mu;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.ParcelFormatException;
import android.os.Parcelable;

import com.mu.example.mu.base.BaseEntry;
import com.mu.example.mu.base.Utils;

/**
 * Created by mu on 2017/5/31.
 */

public class Entry extends BaseEntry implements Parcelable {
    /** the x value */
    private float x = 0f;

    public Entry() {

    }
    /**
     * A Entry represents one single entry in the chart.
     *
     * @param x the x value
     * @param y the y value (the actual value of the entry)
     */
    public Entry(float x, float y) {
        super(y);
        this.x = x;
    }
    /**
     * A Entry represents one single entry in the chart.
     *
     * @param x the x value
     * @param y the y value (the actual value of the entry)
     * @param data Spot for additional data this Entry represents.
     */
    public Entry(float x, float y, Object data) {
        super(y, data);
        this.x = x;
    }

    /**
     * A Entry represents one single entry in the chart.
     *
     * @param x the x value
     * @param y the y value (the actual value of the entry)
     * @param icon icon image
     */
    public Entry(float x, float y, Drawable icon) {
        super(y, icon);
        this.x = x;
    }

    /**
     * A Entry represents one single entry in the chart.
     *
     * @param x the x value
     * @param y the y value (the actual value of the entry)
     * @param icon icon image
     * @param data Spot for additional data this Entry represents.
     */
    public Entry(float x, float y, Drawable icon, Object data) {
        super(y, icon, data);
        this.x = x;
    }
    /**
     * Returns the x-value of this Entry object.
     *
     * @return
     */
    public float getX() {
        return x;
    }

    /**
     * Sets the x-value of this Entry object.
     *
     * @param x
     */
    public void setX(float x) {
        this.x = x;
    }
    @Override
    public String toString() {
        return "Entry, x: " + x + " y: " + getY();
    }
    public boolean equalTo(Entry e) {

        if (e == null)
            return false;

        if (e.getData() != this.getData())
            return false;

        if (Math.abs(e.x - this.x) > Utils.FLOAT_EPSILON)
            return false;

        if (Math.abs(e.getY() - this.getY()) > Utils.FLOAT_EPSILON)
            return false;

        return true;
    }
    protected Entry(Parcel in) {
        this.x = in.readFloat();
        this.setY(in.readFloat());
        if (in.readInt() == 1) {
            this.setData(in.readParcelable(Object.class.getClassLoader()));
        }
    }


    public static  final Parcelable.Creator<Entry> CREATOR= new Creator<Entry>() {
        @Override
        public Entry createFromParcel(Parcel source) {
            return new Entry(source);
        }

        @Override
        public Entry[] newArray(int size) {
            return new Entry[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeFloat(this.x);
        dest.writeFloat(this.getY());
        if (getData() != null) {
            if (getData() instanceof Parcelable) {
                dest.writeInt(1);
                dest.writeParcelable((Parcelable) this.getData(), flags);
            } else {
                throw new ParcelFormatException("Cannot parcel an Entry with non-parcelable data");
            }
        } else {
            dest.writeInt(0);
        }

    }
}
