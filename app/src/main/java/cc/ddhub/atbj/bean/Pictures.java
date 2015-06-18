package cc.ddhub.atbj.bean;

import android.support.annotation.NonNull;
import android.text.format.DateUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by denzelw on 15/6/14.
 */
public class Pictures implements Comparable<Pictures>{

    private long time;
    private List<Picture> pictures;

    public Pictures(){
        time = 0;
        pictures = new ArrayList<>();
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getTime() {
        return time;
    }

    public String getDate(){
        if (DateUtils.isToday(time)){
            return "今天";
        }
        String pattern = "MM-dd";
        SimpleDateFormat format = new SimpleDateFormat(pattern, Locale.getDefault());
        return format.format(new Date(time));
    }

    public void addPicture(Picture picture){
        pictures.add(picture);
    }

    public List<Picture> getPictures() {
        return pictures;
    }

    public boolean isEmpty(){
        return pictures.isEmpty();
    }

    public int size(){
        return pictures.size();
    }

    public void sort(){
        Collections.sort(pictures);
    }

    @Override
    public int compareTo(@NonNull Pictures another) {
        if (getTime() > another.getTime()){
            return 1;
        }
        if (getTime() < another.getTime()){
            return -1;
        }
        return 0;
    }
}
