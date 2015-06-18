package cc.ddhub.atbj;

import android.app.Application;
import android.os.Environment;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by denzelw on 15/6/14.
 */
public class PictureApplication extends Application {
    private static PictureApplication application;

    public static PictureApplication getInstance(){
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;

        ImageLoader.getInstance().init(new ImageLoaderConfiguration.Builder(this).build());
    }

    public String getPictureCachePath(){
//        return getCacheDir().getAbsolutePath() + "/pictures/";
        return Environment.getExternalStorageDirectory().getAbsolutePath() + "/test";
    }

}
