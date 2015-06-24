package cc.ddhub.atbj.Util;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.File;

import cc.ddhub.atbj.PictureApplication;

/**
 * Created by DELL on 2015/6/24.
 */
public class PictureUtil {
    private static final String PREF = "picture";
    private static final String PREF_FIRST_APP = "pref_first_app";

    public static String getTempPicturePath(){
        return PictureApplication.getInstance().getPictureCachePath() + "/" + System.currentTimeMillis() + ".jpg";
    }

    public static Intent addNewPicture(String path){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(
                new File(path)));
        return intent;
    }

    public static SharedPreferences getPref(Context context){
        return context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
    }

    public static boolean isFirstEnter(Context context){
        return getPref(context).getBoolean(PREF_FIRST_APP, true);
    }

    public static void enter(Context context){
        getPref(context).edit().putBoolean(PREF_FIRST_APP, false).apply();
    }

}
