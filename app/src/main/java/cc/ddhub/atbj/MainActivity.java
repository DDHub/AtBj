package cc.ddhub.atbj;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ListView;

import java.io.File;

import cc.ddhub.atbj.Util.ViewUtil;
import cc.ddhub.atbj.adapter.PictureAdapter;
import cc.ddhub.atbj.bean.Picture;
import cc.ddhub.atbj.bean.PictureMap;
import cc.ddhub.atbj.data.OnPictureLoadListener;
import cc.ddhub.atbj.data.PictureCenter;
import cc.ddhub.atbj.view.PictureItemView;

public class MainActivity extends Activity {
    private PictureAdapter pictureAdapter;

    private String tempPicturePath;

    private static final int REQUEST_CAMERA = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = ViewUtil.findViewById(getWindow().getDecorView(), R.id.listView);

        pictureAdapter = new PictureAdapter();
        pictureAdapter.setOnPictureItemClickListener(pictureItemClickListener);
        listView.setAdapter(pictureAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (pictureAdapter.getCount() == 0) {
            PictureCenter.getInstance().load(pictureLoadListener);
        }
    }

    private OnPictureLoadListener pictureLoadListener = new OnPictureLoadListener() {
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
            tempPicturePath = PictureApplication.getInstance().getPictureCachePath() + "/" + System.currentTimeMillis() + ".jpg";
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(
                    new File(tempPicturePath)));
            startActivityForResult(intent, REQUEST_CAMERA);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK){
            return;
        }
        if (REQUEST_CAMERA == requestCode){
            Picture picture = new Picture(tempPicturePath, System.currentTimeMillis());
            pictureAdapter.addPicture(picture);
        }
    }
}
