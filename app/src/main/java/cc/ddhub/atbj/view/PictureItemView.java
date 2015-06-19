package cc.ddhub.atbj.view;

import android.content.Context;
import android.graphics.Point;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import cc.ddhub.atbj.R;
import cc.ddhub.atbj.bean.Picture;
import cc.ddhub.atbj.Util.SystemUtil;
import cc.ddhub.atbj.Util.ViewUtil;

/**
 * Created by denzelw on 15/6/13.
 */
public class PictureItemView extends LinearLayout {
    private TextView dateText;
    private GridLayout pictureGrid;
    private DisplayImageOptions options;

    private List<Picture> pictureList;

    private int leftMargin;
    private int topMargin;
    private int width;
    private int height;

    private static final int COLUMN_COUNT = 5;

    private OnPictureItemClickListener pictureItemClickListener;

    public interface OnPictureItemClickListener {
        public void onPictureItemClick(View view, int position, Picture picture);
        public void onPictureAddClick();
    }

    public void setOnPictureItemClickListener(OnPictureItemClickListener itemClickListener) {
        this.pictureItemClickListener = itemClickListener;
    }

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

    private void init(Context context) {
        inflate(context, R.layout.item_picture, this);
        setOrientation(VERTICAL);

        pictureList = new ArrayList<>();

        dateText = ViewUtil.findViewById(this, R.id.picture_date);
        pictureGrid = ViewUtil.findViewById(this, R.id.picture_grid);
        pictureGrid.setColumnCount(COLUMN_COUNT);

        leftMargin = getResources().getDimensionPixelSize(R.dimen.picture_item_margin_left);
        topMargin = getResources().getDimensionPixelSize(R.dimen.picture_item_margin_top);

        Point point = SystemUtil.getScreenSize(context);
        width = (point.x - leftMargin * (COLUMN_COUNT - 1) - ((LayoutParams) pictureGrid.getLayoutParams()).leftMargin - ((LayoutParams) pictureGrid.getLayoutParams()).rightMargin) / COLUMN_COUNT;
        height = width;

        options = new DisplayImageOptions.Builder().cacheOnDisk(false).cacheInMemory(false).build();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    public void clear() {
        dateText.setText("");
        pictureGrid.removeAllViews();
        pictureList.clear();
    }

    public void setDate(long dateInMillis) {
        dateText.setText(getDate(dateInMillis));
    }

    public String getDate(long time) {
        if (DateUtils.isToday(time)) {
            return "今天";
        }
        String pattern = "MM-dd";
        SimpleDateFormat format = new SimpleDateFormat(pattern, Locale.getDefault());
        return format.format(new Date(time));
    }

    public void addPictures(List<Picture> pictures) {
        if (pictures == null){
            return;
        }
        for (Picture picture : pictures) {
            boolean isSuc = addPicture(picture.getPath());
            if (isSuc) {
                pictureList.add(picture);
            }
        }
        if (!pictures.isEmpty() && DateUtils.isToday(pictures.get(0).getTime())){
            newPicture();
        }
    }

    public void newPicture(){
        ImageView imageView = new ImageView(getContext());
        imageView.setOnClickListener(clickListener);
        imageView.setImageResource(R.drawable.ic_add_white_48dp);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        pictureGrid.addView(imageView, initViewParams());
    }

    private boolean addPicture(String file) {
        if (TextUtils.isEmpty(file)){
            return false;
        }
        ImageView imageView = new ImageView(getContext());
        imageView.setTag(pictureGrid.getChildCount());
        imageView.setOnClickListener(clickListener);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        ImageLoader.getInstance().displayImage("file://" + file, imageView, options);
        pictureGrid.addView(imageView, initViewParams());
        return true;
    }

    private GridLayout.LayoutParams initViewParams(){
        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        params.width = width;
        params.height = height;
        if (pictureGrid.getChildCount() % COLUMN_COUNT != 0) {
            params.leftMargin = leftMargin;
        }
        if (pictureGrid.getChildCount() >= COLUMN_COUNT) {
            params.topMargin = topMargin;
        }
        return params;
    }

    public ImageView getImageView(int index){
        if (index < pictureGrid.getChildCount()){
            return (ImageView) pictureGrid.getChildAt(index);
        }
        return null;
    }

    private OnClickListener clickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (pictureItemClickListener != null) {
                if (v.getTag() == null){
                    pictureItemClickListener.onPictureAddClick();
                }else {
                    int position = (int) v.getTag();
                    pictureItemClickListener.onPictureItemClick(PictureItemView.this, position, pictureList.get(position));
                }
            }
        }
    };

}
