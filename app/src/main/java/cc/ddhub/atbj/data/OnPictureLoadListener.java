package cc.ddhub.atbj.data;

import cc.ddhub.atbj.bean.PictureMap;

/**
 * Created by DELL on 2015/6/19.
 */
public interface OnPictureLoadListener{
    public void onPicturePreload();
    public void onPictureLoadSucceed(PictureMap pictureMap);
}
