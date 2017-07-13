package com.tgf.matrix.view.basic;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.tgf.matrix.R;

/**
 * Created by 涂高峰 on 2017/5/27.
 */
public class PolyView extends View {
    private static final String TAG = "PolyView";
    private Bitmap bitmap;
    private Matrix matrix;
    private int pointCount = 4; //setPolyToPoly最后一个参数 作为配置项 查看效果 0 1 2 3 4
    private int triggerRadius = 100; //手指触摸半径
    private float[] src = new float[8];
    private float[] dst = new float[8];
    private Paint pointPaint = new Paint();
    public PolyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        pointPaint.setAntiAlias(true);
        pointPaint.setColor(Color.GRAY);
        pointPaint.setStrokeWidth(20);
        pointPaint.setStrokeCap(Paint.Cap.ROUND);
        initBitmapAndMatrix();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec),500);
    }

    private void initBitmapAndMatrix(){
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.def);
        matrix = new Matrix();
        float[] temp = new float[]{0,0, bitmap.getWidth()+0,0, bitmap.getWidth()+0,bitmap.getHeight()+0, 0,bitmap.getHeight()+0};
        src = temp.clone();
        dst = temp.clone();
        matrix.setPolyToPoly(src,0,dst,0,pointCount);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        return super.onTouchEvent(event);
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_MOVE:
                for (int i=0; i<pointCount*2; i+=2){
                    if (Math.abs(dst[i]-x) <= triggerRadius && Math.abs(dst[i+1] - y) <= triggerRadius){
                        dst[i] = event.getX();
                        dst[i+1] = event.getY();
                        resetMatrix();
                        invalidate();
                    }
                }
                break;
        }
        return true;
    }

    private void resetMatrix(){
        matrix.reset();
        matrix.setPolyToPoly(src,0,dst,0,pointCount);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(bitmap,matrix,null);

        for (int i=0 ;i<pointCount*2; i+=2){
            canvas.drawPoint(dst[i],dst[i+1],pointPaint);
        }
    }
}
