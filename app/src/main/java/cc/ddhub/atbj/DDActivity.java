package cc.ddhub.atbj;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import cc.ddhub.atbj.Util.PictureUtil;

/**
 * Created by DELL on 2015/6/24.
 */
public class DDActivity extends Activity {

    private static final int REQUEST_CAMERA = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (PictureUtil.isFirstEnter(this)){
            PictureUtil.enter(this);
            startActivity(new Intent(this, LauncherActivity.class));
            finish();
        }else {
            startActivityForResult(PictureUtil.addNewPicture(PictureUtil.getTempPicturePath()), REQUEST_CAMERA);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CAMERA) {
            startActivity(MainActivity.newIntent(DDActivity.this));
            finish();
        }
    }
}
