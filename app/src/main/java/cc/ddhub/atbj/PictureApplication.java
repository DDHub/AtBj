package cc.ddhub.atbj;

import android.app.Application;
import android.os.Environment;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.File;

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

        File file = new File(getPictureCachePath());
        file.mkdirs();
    }

    public String getPictureCachePath(){
        return Environment.getExternalStorageDirectory().getAbsolutePath() + "/AtBj";
    }

}
