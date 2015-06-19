package cc.ddhub.atbj.bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.text.TextUtils;

/**
 * Created by denzelw on 15/6/13.
 */
public class Picture implements Comparable<Picture>,Parcelable {
    private String path;
    private long time;

    public Picture(String path, long time) {
        this.path = path;
        this.time = time;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public int compareTo(@NonNull Picture another) {
        if (getTime() > another.getTime()){
            return 1;
        }
        if (getTime() < another.getTime()){
            return -1;
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Picture){
            return !TextUtils.isEmpty(((Picture) o).getPath()) && ((Picture) o).getPath().equals(getPath()) && ((Picture) o).getTime() == getTime();
        }
        return super.equals(o);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.path);
        dest.writeLong(this.time);
    }

    protected Picture(Parcel in) {
        this.path = in.readString();
        this.time = in.readLong();
    }

    public static final Parcelable.Creator<Picture> CREATOR = new Parcelable.Creator<Picture>() {
        public Picture createFromParcel(Parcel source) {
            return new Picture(source);
        }

        public Picture[] newArray(int size) {
            return new Picture[size];
        }
    };
}
