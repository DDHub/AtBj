package cc.ddhub.atbj.Util;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by denzelw on 15/6/14.
 */
public class SystemUtil {

    public static Point getScreenSize(Context context){
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        return point;
    }

    public static Intent playImage(@NonNull String path){
        if(TextUtils.isEmpty(path)){
            return null;
        }
        Intent i = new Intent(Intent.ACTION_VIEW);
        Uri data = Uri.parse("file:// " + path);
        i.setDataAndType(data, "image/*");
        return i;
    }
}
