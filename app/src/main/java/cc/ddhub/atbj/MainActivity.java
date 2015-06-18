package cc.ddhub.atbj;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import cc.ddhub.atbj.adapter.PictureAdapter;
import cc.ddhub.atbj.bean.PictureMap;

public class MainActivity extends AppCompatActivity {
    private PictureAdapter pictureAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pictureAdapter = new PictureAdapter();

        new PictureLoader(pictureLoadListener, PictureApplication.getInstance().getPictureCachePath());
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
