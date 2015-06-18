package cc.ddhub.atbj;

import android.os.AsyncTask;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cc.ddhub.atbj.bean.Picture;
import cc.ddhub.atbj.bean.PictureMap;

/**
 * Created by denzelw on 15/6/14.
 */
public class PictureLoader extends AsyncTask<Void, Integer, PictureMap>{
    private OnPictureLoadListener listener;
    private String folder;

    public interface OnPictureLoadListener{
        public void onPicturePreload();
        public void onPictureLoadSucceed(PictureMap pictureMap);
    }

    public PictureLoader(OnPictureLoadListener listener, String folder){
        this.listener = listener;
        this.folder = folder;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (listener != null) {
            listener.onPicturePreload();
        }
    }

    @Override
    protected PictureMap doInBackground(Void... params) {
        PictureMap pictureMap = new PictureMap();
        File folder = new File(this.folder);
        if (folder.isDirectory()){
            File[] files = folder.listFiles();
            if (files != null){
                int i = 0;
                for (File file : files){
                    Picture picture = new Picture(file.getAbsolutePath(), file.lastModified());
                    if (i > 5){
                        picture.setTime(file.lastModified() - 86400 * 1000);
                    }
                    pictureMap.addPicture(picture);
                    i++;
                }
            }
        }
        pictureMap.sort();
        return pictureMap;
    }

    @Override
    protected void onPostExecute(PictureMap pictureMap) {
        super.onPostExecute(pictureMap);
        if (listener != null){
            listener.onPictureLoadSucceed(pictureMap);
        }
    }
}
