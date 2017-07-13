package com.tgf.path.view.bezier;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by 涂高峰 on 2017/5/25.
 */
public class BazierCircleView extends View {
    private static final String TAG = "BazierCircleView";
    private static final float C = 0.551915024494f;
    private Paint mPaint = new Paint();
    private PointF p0,p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11;
    private int centerX,centerY;
    private int radius = 100;
    private float M = radius*C;
    private float mCurrent = 0;
    private float mCount = 10;
    public BazierCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint.setStrokeWidth(6);
        mPaint.setStyle(Paint.Style.STROKE);
        p0 = new PointF(0,radius);
        p1 = new PointF(M,radius);
        p2 = new PointF(radius,M);
        p3 = new PointF(radius,0);
        p4 = new PointF(radius,-M);
        p5 = new PointF(M,-radius);
        p6 = new PointF(0,-radius);
        p7 = new PointF(-M,-radius);
        p8 = new PointF(-radius,-M);
        p9 = new PointF(-radius,0);
        p10 = new PointF(-radius,M);
        p11 = new PointF(-M,radius);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX = w/2;
        centerY = h/2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(getWidth()/2,getHeight()/2);//设置中心点

        drawPoint(canvas);
        drawLine(canvas);
        drawCoordinateSystem(canvas);

        mPaint.setColor(Color.BLUE);
        Path path = new Path();
        path.moveTo(p0.x,p0.y);
        path.cubicTo(p1.x,p1.y,p2.x,p2.y,p3.x,p3.y);
        path.cubicTo(p4.x,p4.y,p5.x,p5.y,p6.x,p6.y);
        path.cubicTo(p7.x,p7.y,p8.x,p8.y,p9.x,p9.y);
        path.cubicTo(p10.x,p10.y,p11.x,p11.y,p0.x,p0.y);
        canvas.drawPath(path,mPaint);

        mCurrent++;
        if (mCurrent < mCount){
            Log.i(TAG, "onDraw: ");
            p6.y += 5;
            p11.y -=5;
            p1.y -=5;
            p10.x += 2;
            p2.x -= 2;
            postInvalidateDelayed(20);
        }
    }

    private void drawPoint(Canvas canvas){
        mPaint.setStrokeWidth(6);
        mPaint.setColor(Color.RED);
        canvas.drawPoint(p0.x,p0.y,mPaint);
        canvas.drawPoint(p1.x,p1.y,mPaint);
        canvas.drawPoint(p2.x,p2.y,mPaint);
        canvas.drawPoint(p3.x,p3.y,mPaint);
        canvas.drawPoint(p4.x,p4.y,mPaint);
        canvas.drawPoint(p5.x,p5.y,mPaint);
        canvas.drawPoint(p6.x,p6.y,mPaint);
        canvas.drawPoint(p7.x,p7.y,mPaint);
        canvas.drawPoint(p8.x,p8.y,mPaint);
        canvas.drawPoint(p9.x,p9.y,mPaint);
        canvas.drawPoint(p10.x,p10.y,mPaint);
        canvas.drawPoint(p11.x,p11.y,mPaint);
    }

    private void drawLine(Canvas canvas){
        mPaint.setStrokeWidth(2);
        mPaint.setColor(Color.CYAN);
        canvas.drawLine(p11.x,p11.y,p1.x,p1.y,mPaint);
        canvas.drawLine(p2.x,p2.y,p4.x,p4.y,mPaint);
        canvas.drawLine(p5.x,p5.y,p7.x,p7.y,mPaint);
        canvas.drawLine(p8.x,p8.y,p10.x,p10.y,mPaint);
    }

    private void drawCoordinateSystem(Canvas canvas){
        mPaint.setColor(Color.RED);
        canvas.drawLine(0,-getHeight(),0,getHeight(),mPaint);
        canvas.drawLine(-getWidth(),0,getWidth(),0,mPaint);
    }
}
