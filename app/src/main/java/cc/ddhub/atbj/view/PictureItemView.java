package cc.ddhub.atbj.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import cc.ddhub.atbj.R;
import cc.ddhub.atbj.Util.ViewUtil;

/**
 * Created by denzelw on 15/6/13.
 */
public class PictureItemView extends LinearLayout {
    private TextView dateText;
    private GridLayout pictureGrid;

    private int leftMargin;
    private int topMargin;
    private int width;
    private int height;

    private static final int COLUMN_COUNT = 5;

    public PictureItemView(Context context) {
        super(context);
        init(context);
    }

    public PictureItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PictureItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        inflate(context, R.layout.item_picture, this);
        dateText = ViewUtil.findViewById(this, R.id.picture_date);
        pictureGrid = ViewUtil.findViewById(this, R.id.picture_grid);
        pictureGrid.setColumnCount(COLUMN_COUNT);

        leftMargin = getResources().getDimensionPixelSize(R.dimen.picture_item_margin_left);
        topMargin = getResources().getDimensionPixelSize(R.dimen.picture_item_margin_top);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        width = (w - leftMargin * (COLUMN_COUNT - 1)) / COLUMN_COUNT;
        height = width;
    }

    public void setDate(String date){
        dateText.setText(date);
    }

    public void addPicture(String file){
        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        params.width = width;
        params.height = height;
        ImageView imageView = new ImageView(getContext());
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        if (getChildCount() % COLUMN_COUNT != 0){
            params.leftMargin = leftMargin;
        }
        if (getChildCount() >= COLUMN_COUNT){
            params.topMargin = topMargin;
        }
        ImageLoader.getInstance().displayImage("file://" + file, imageView);
        addView(imageView, params);
    }

}
