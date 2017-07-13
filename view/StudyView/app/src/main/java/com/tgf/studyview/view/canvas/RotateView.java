package com.tgf.studyview.view.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 涂高峰 on 2017/5/16.
 */
public class RotateView extends View {
    private Paint mPaint = new Paint();
    public RotateView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(getWidth()/2,getHeight()/2);
        canvas.drawLine(0,0,100,100,mPaint);

        RectF rectF = new RectF(0,-100,100,0);

        canvas.save();

        mPaint.setColor(Color.BLUE);
        canvas.drawRect(rectF,mPaint);
        canvas.rotate(180);  //旋转中心向x右偏移50

        mPaint.setColor(Color.CYAN);
        canvas.drawRect(rectF,mPaint);

        mPaint.setColor(Color.BLACK);
        canvas.drawCircle(0,0,200,mPaint);
        canvas.drawCircle(0,0,180,mPaint);

        canvas.restore();   //回滚取出旋转前的画布状态

        for (int i=0; i<360; i+=60){
            canvas.drawLine(0,-180,0,-200,mPaint);
            canvas.rotate(60);
        }
    }
}
