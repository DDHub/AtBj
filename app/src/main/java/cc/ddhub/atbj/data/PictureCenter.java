package cc.ddhub.atbj.data;

import java.util.List;

import cc.ddhub.atbj.PictureApplication;
import cc.ddhub.atbj.bean.Picture;
import cc.ddhub.atbj.bean.PictureMap;
import cc.ddhub.atbj.bean.Pictures;

/**
 * Created by DELL on 2015/6/19.
 */
public class PictureCenter {
    private PictureMap pictureMap;

    private static PictureCenter center;

    private PictureCenter(){

    }

    public static PictureCenter getInstance(){
        if (center == null){
            center = new PictureCenter();
        }
        return center;
    }

    public void load(OnPictureLoadListener listener){
        new PictureLoader(listener, PictureApplication.getInstance().getPictureCachePath()){
            @Override
            protected void onPostExecute(PictureMap pictureMap) {
                super.onPostExecute(pictureMap);
                PictureCenter.this.pictureMap = pictureMap;
            }
        }.execute();
    }

    public Picture getNext(Picture picture){
        if (pictureMap == null){
            return null;
        }
        int size = pictureMap.size();
        for (int i = 0; i < size; i++){
            Pictures pictures = pictureMap.get(i);
            List<Picture> list = pictures.getPictures();
            int count = list.size();
            for (int j = 0; j < count; j++){
                if (list.get(j).equals(picture)){
                    if (j == (count - 1)){
                        if (i == (size - 1)){
                            return null;
                        }else {
                            List<Picture> nextList = pictureMap.get(i + 1).getPictures();
                            if (nextList != null && !nextList.isEmpty()){
                                return nextList.get(0);
                            }
                        }
                    }else {
                        return list.get(j + 1);
                    }
                }
            }
        }
        return null;
    }

}
