package cc.ddhub.atbj;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

import cc.ddhub.atbj.bean.Picture;
import cc.ddhub.atbj.Util.ViewUtil;
import cc.ddhub.atbj.data.PictureCenter;

/**
 * Created by DELL on 2015/6/18.
 */
public class PictureBrowserActivity extends Activity {
    private Picture curPicture;

    private static final String PICTURE = "picture";

    public static Intent getIntent(Context context, Picture picture) {
        Intent intent = new Intent(context, PictureBrowserActivity.class);
        intent.putExtra(PICTURE, picture);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_browser);

        Intent intent = getIntent();
        if (intent == null) {
            finish();
        } else {
            Picture picture = intent.getParcelableExtra(PICTURE);
            init(picture);
        }
    }

    private void init(Picture picture) {
        if (picture == null) {
            finish();
        } else {
            curPicture = picture;
            ImageView imageView = ViewUtil.findViewById(getWindow().getDecorView(), R.id.imageView);
            imageView.setOnClickListener(clickListener);
            ImageLoader.getInstance().displayImage("file://" + picture.getPath(), imageView);
        }
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Picture picture = PictureCenter.getInstance().getNext(curPicture);
            init(picture);
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
    }
}
