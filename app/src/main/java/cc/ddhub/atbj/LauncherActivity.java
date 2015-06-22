package cc.ddhub.atbj;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by DDHub on 2015/6/22.
 */
public class LauncherActivity extends Activity{

    public static Intent getIntent(Context context){
        return new Intent(context, LauncherActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

    }
}
