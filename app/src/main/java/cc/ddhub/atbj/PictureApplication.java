package cc.ddhub.atbj;

import android.app.Application;

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
    }

    public String getPictureCachePath(){
        return getCacheDir().getAbsolutePath() + "/pictures/";
    }

}
