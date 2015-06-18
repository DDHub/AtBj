package cc.ddhub.atbj.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cc.ddhub.atbj.util.DateUtil;

/**
 * Created by denzelw on 15/6/13.
 */
public class PictureMap {
    private List<Pictures> picturesList;

    public PictureMap(){
        picturesList = new ArrayList<>();
    }

    public boolean addPicture(Picture picture){
        if (picture == null){
            return false;
        }
        for (Pictures pictures : picturesList){
            if (DateUtil.isSameDate(pictures.getTime(), picture.getTime())){
                pictures.addPicture(picture);
                return true;
            }
        }
        Pictures ps = new Pictures();
        ps.setTime(picture.getTime());
        ps.addPicture(picture);
        picturesList.add(ps);
        return true;
    }

    public Pictures get(int index){
        return picturesList.get(index);
    }

    public int size(){
        return picturesList.size();
    }

    public List<Pictures> sort(){
        for (Pictures pictures : picturesList){
            pictures.sort();
        }
        Collections.sort(picturesList);
        return picturesList;
    }

}
