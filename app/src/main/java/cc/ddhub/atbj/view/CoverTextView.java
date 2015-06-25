package cc.ddhub.atbj.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

import cc.ddhub.atbj.R;

/**
 * Created by DELL on 2015/6/25.
 */
public class CoverTextView extends TextView {
    private Paint paint;
    private Path path;

    private Bitmap bitmap;
    private int coverColor;
    private Canvas coverCanvas;

    public CoverTextView(Context context) {
        super(context);
        init(null);
    }

    public CoverTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CoverTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(null);
    }

    private void init(AttributeSet attrs) {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(80);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));

        path = new Path();

        if (attrs != null){
            TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.CoverText);
            coverColor = array.getColor(R.styleable.CoverText_coverColor, Color.BLACK);
            array.recycle();
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (bitmap != null){
            bitmap.recycle();
            bitmap = null;
        }
        bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        coverCanvas = new Canvas(bitmap);
        coverCanvas.drawColor(coverColor);
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                path.lineTo(event.getX(), event.getY());
                invalidate();
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        coverCanvas.drawPath(path, paint);
        canvas.drawBitmap(bitmap, 0, 0, null);
    }

}
