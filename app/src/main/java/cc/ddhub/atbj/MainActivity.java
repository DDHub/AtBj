package cc.ddhub.atbj;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import cc.ddhub.atbj.adapter.PictureAdapter;
import cc.ddhub.atbj.bean.Picture;
import cc.ddhub.atbj.bean.PictureMap;
import cc.ddhub.atbj.util.ViewUtil;
import cc.ddhub.atbj.view.PictureItemView;

public class MainActivity extends Activity {
    private PictureAdapter pictureAdapter;
    private ImageView zoomImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = ViewUtil.findViewById(getWindow().getDecorView(), R.id.listView);
        zoomImageView = ViewUtil.findViewById(getWindow().getDecorView(), R.id.imageView);

        pictureAdapter = new PictureAdapter();
        pictureAdapter.setOnPictureItemClickListener(pictureItemClickListener);
        listView.setAdapter(pictureAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (pictureAdapter.getCount() == 0) {
            new PictureLoader(pictureLoadListener, PictureApplication.getInstance().getPictureCachePath()).execute();
        }
    }

    @Override
    public void onBackPressed() {
        if (zoomImageView.getVisibility() != View.GONE){
            zoomImageView.performClick();
        }else {
            super.onBackPressed();
        }
    }

    private PictureLoader.OnPictureLoadListener pictureLoadListener = new PictureLoader.OnPictureLoadListener() {
        @Override
        public void onPicturePreload() {

        }

        @Override
        public void onPictureLoadSucceed(PictureMap pictureMap) {
            pictureAdapter.setPictureMap(pictureMap);
        }
    };

    private PictureItemView.OnPictureItemClickListener pictureItemClickListener = new PictureItemView.OnPictureItemClickListener() {
        @Override
        public void onPictureItemClick(View view, int position, Picture picture) {
            startActivity(PictureBrowserActivity.getIntent(MainActivity.this, picture));
        }

        @Override
        public void onPictureAddClick() {

        }
    };

}
