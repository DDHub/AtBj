package cc.ddhub.atbj.Util;

import android.view.View;

/**
 * Created by denzelw on 15/6/14.
 */
public class ViewUtil {


    public static <T extends View> T findViewById(View view, int id){
        return (T)view.findViewById(id);
    }

}
