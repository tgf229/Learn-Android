package com.tgf.paint.porterDuffXfermode;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.tgf.paint.R;

/**
 * Created by 涂高峰 on 2017/7/3.
 */
public class XfermodeView extends View {
    private Paint mPaint;
    private Canvas canvas;
    private Bitmap mBgBitmap, mFgBitmap;
    private Path mPath;
    public XfermodeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();

        //重要! 用于绘制透明path
        mPaint.setAlpha(0);
        //遮罩层类型
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));

        mPaint.setStrokeWidth(50);
        mPaint.setStrokeCap(Paint.Cap.ROUND); //笔触风格
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND); // 接合处形态

        mBgBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.def);
        mFgBitmap = Bitmap.createBitmap(mBgBitmap.getWidth(), mBgBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        canvas = new Canvas(mFgBitmap);
        canvas.drawColor(Color.GRAY);

        mPath = new Path();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(event.getX(),event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(event.getX(),event.getY());
                break;
        }

        //其实就是绘制透明路径, 因mPaint设置的Alpha为0
        canvas.drawPath(mPath,mPaint);
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(mBgBitmap,0,0,null);
        canvas.drawBitmap(mFgBitmap,0,0,null);
    }
}
