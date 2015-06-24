package cc.ddhub.atbj;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import cc.ddhub.atbj.Util.SystemUtil;
import cc.ddhub.atbj.Util.ViewUtil;

/**
 * Created by DELL on 2015/6/23.
 */
public class NewTextActivity extends ActionBarActivity {
    private EditText editText;

    private String saveTo;

    private static final String SAVE_TO = "save_to";

    public static Intent getIntent(Context context, String saveTo){
        Intent intent = new Intent(context, NewTextActivity.class);
        intent.putExtra(SAVE_TO, saveTo);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_text);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        if (intent == null){
            finish();
        }else {
            saveTo = intent.getStringExtra(SAVE_TO);
        }

        editText = ViewUtil.findViewById(getWindow().getDecorView(), R.id.newText_edit);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_new_text, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_save){
            save(editText.getText());
            return true;
        }else if (item.getItemId() == android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void save(CharSequence text){
        if (TextUtils.isEmpty(text)){
            finish();
        }
        Point point = SystemUtil.getScreenSize(this);
        Bitmap bitmap = Bitmap.createBitmap(point.x, point.y, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(Color.argb(0xff, 0xf4, 0xf4, 0xf4));
        TextPaint mTextPaint=new TextPaint();
        mTextPaint.setTextSize(64);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setFakeBoldText(true);
        mTextPaint.setColor(Color.BLACK);
        StaticLayout mTextLayout = new StaticLayout(text, mTextPaint, canvas.getWidth(), Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
        canvas.save();
        mTextLayout.draw(canvas);
        canvas.restore();
        File file = new File(saveTo);
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            outputStream.flush();
            outputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
            if (outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }

    }
}
