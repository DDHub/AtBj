package cc.ddhub.atbj.data;

import android.media.ExifInterface;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.text.format.DateUtils;

import java.io.File;
import java.io.IOException;

import cc.ddhub.atbj.bean.Picture;
import cc.ddhub.atbj.bean.PictureMap;
import cc.ddhub.atbj.bean.Pictures;
import cc.ddhub.atbj.Util.DateUtil;

/**
 * Created by denzelw on 15/6/14.
 */
class PictureLoader extends AsyncTask<Void, Integer, PictureMap>{
    private OnPictureLoadListener listener;
    private String folder;

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
                for (File file : files){
                    long time = file.lastModified();
                    try {
                        ExifInterface exifInterface = new ExifInterface(file.getAbsolutePath());
                        String s = exifInterface.getAttribute(ExifInterface.TAG_DATETIME);
                        if (!TextUtils.isEmpty(s)) {
                            time = DateUtil.getTime(s, "yyyy:MM:dd hh:mm:ss");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Picture picture = new Picture(file.getAbsolutePath(), time);
                    pictureMap.addPicture(picture);
                }
            }
        }
        pictureMap.sort();
        if (pictureMap.size() == 0 || !DateUtils.isToday(pictureMap.get(0).getTime())){
            Picture picture = new Picture("", System.currentTimeMillis());
            Pictures pictures = new Pictures();
            pictures.setTime(picture.getTime());
            pictures.addPicture(picture);
            pictureMap.addPictures(0, pictures);
        }
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
