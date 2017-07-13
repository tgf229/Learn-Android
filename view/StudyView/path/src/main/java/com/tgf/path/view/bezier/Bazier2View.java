package com.tgf.path.view.bezier;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by 涂高峰 on 2017/5/24.
 * 二阶贝塞尔曲线
 */
public class Bazier2View extends View{

    private PointF start,end,control;
    private int centerX,centerY;
    private Paint mPaint = new Paint();
    public Bazier2View(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint.setStyle(Paint.Style.STROKE);
        start = new PointF(0,0);
        end = new PointF(0,0);
        control = new PointF();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX = w/2;
        centerY = h/2;

        start.x = centerX - 100;
        start.y = centerY;
        end.x = centerX + 100;
        end.y = centerY;
        control.x = centerX;
        control.y = centerY - 100;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        return super.onTouchEvent(event);
        control.x = event.getX();
        control.y = event.getY();
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setStrokeWidth(12);
        mPaint.setColor(Color.BLACK);
        canvas.drawPoint(start.x,start.y,mPaint);
        canvas.drawPoint(end.x,end.y,mPaint);
        canvas.drawPoint(control.x,control.y,mPaint);

        mPaint.setStrokeWidth(2);
        mPaint.setColor(Color.GRAY);
        //绘制辅助线
        canvas.drawLine(start.x,start.y,control.x,control.y,mPaint);
        canvas.drawLine(end.x,end.y,control.x,control.y,mPaint);

        mPaint.setStrokeWidth(4);
        mPaint.setColor(Color.RED);
        Path path = new Path();
        path.moveTo(start.x,start.y);
        path.quadTo(control.x,control.y,end.x,end.y);
        canvas.drawPath(path,mPaint);

    }
}
