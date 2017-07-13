package com.tgf.path.view.bezier;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by 涂高峰 on 2017/5/25.
 */
public class Bazier3View extends View {
    private static final String TAG = "Bazier3View";
    private Paint mPaint;
    private boolean mode;
    private PointF start,end,control1,control2;
    public Bazier3View(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        start = new PointF();
        end = new PointF();
        control1 = new PointF();
        control2 = new PointF();
    }

    public void setMode(boolean mode){
        this.mode = mode;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.i(TAG, "onSizeChanged: ");
        int centerX = w/2;
        int centerY = h/2;
        start.x = centerX-100;
        start.y = centerY;
        end.x = centerX+100;
        end.y = centerY;
        control1.x = centerX-110;
        control1.y = centerY-100;
        control2.x = centerX+110;
        control2.y = centerY-100;

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mode){
            control1.x = event.getX();
            control1.y = event.getY();
        }else{
            control2.x = event.getX();
            control2.y = event.getY();
        }
        invalidate();
        return true;
//        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        //因为实际布局中,此view被包含在ScrollView中,
        // 调用此方法告知父View,由我本身处理touch事件
        //可对比二阶贝塞尔View(Bazier2View)在ScrollView中的效果
        getParent().requestDisallowInterceptTouchEvent(true);
        return super.dispatchTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(12);
        canvas.drawPoint(start.x,start.y,mPaint);
        canvas.drawPoint(end.x,end.y,mPaint);
        canvas.drawPoint(control1.x,control1.y,mPaint);
        canvas.drawPoint(control2.x,control2.y,mPaint);

        mPaint.setColor(Color.WHITE);
        mPaint.setStrokeWidth(4);
        canvas.drawLine(start.x,start.y,control1.x,control1.y,mPaint);
        canvas.drawLine(control1.x,control1.y,control2.x,control2.y,mPaint);
        canvas.drawLine(control2.x,control2.y,end.x,end.y,mPaint);

        mPaint.setColor(Color.RED);
        Path path = new Path();
        path.moveTo(start.x,start.y);
        path.cubicTo(control1.x,control1.y,control2.x,control2.y,end.x,end.y);
        canvas.drawPath(path,mPaint);
    }
}
