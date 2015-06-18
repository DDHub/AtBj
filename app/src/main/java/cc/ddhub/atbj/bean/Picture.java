package cc.ddhub.atbj.bean;

import android.support.annotation.NonNull;

/**
 * Created by denzelw on 15/6/13.
 */
public class Picture implements Comparable<Picture>{
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
}
