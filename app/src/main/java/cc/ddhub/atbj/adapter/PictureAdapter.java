package cc.ddhub.atbj.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import cc.ddhub.atbj.bean.Picture;
import cc.ddhub.atbj.bean.PictureMap;
import cc.ddhub.atbj.bean.Pictures;
import cc.ddhub.atbj.view.PictureItemView;

/**
 * Created by denzelw on 15/6/13.
 */
public class PictureAdapter extends BaseAdapter {
    private PictureMap pictureMap;

    public void setPictureMap(PictureMap pictureMap){
        this.pictureMap = pictureMap;
    }

    public void addPicture(Picture picture){
        if (pictureMap != null){
            if (pictureMap.addPicture(picture)){
                notifyDataSetChanged();
            }
        }
    }

    @Override
    public int getCount() {
        return pictureMap == null ? 0 : pictureMap.size();
    }

    @Override
    public Pictures getItem(int i) {
        return pictureMap.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Holder holder;
        if (view == null){
            holder = new Holder();
            view = new PictureItemView(viewGroup.getContext());
            holder.itemView = (PictureItemView) view;
            view.setTag(holder);
        }else {
            holder = (Holder) view.getTag();
        }
        return holder.itemView;
    }

    class Holder {
        PictureItemView itemView;
    }
}
