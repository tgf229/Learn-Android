package com.tgf.qyz.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by 涂高峰 on 2017/6/9.
 */
public class MyView1 extends View {
    private static final String TAG = "MyView1";
    private int centerXY;
    private Paint mPaint = new Paint();
    public MyView1(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(20);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerXY = h/2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.translate(centerXY,centerXY);
        int f = (int) (centerXY*0.5f);
        RectF rectF = new RectF(-f,-f,f,f);
        canvas.drawArc(rectF,270,200,false,mPaint);
        canvas.restore();

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.CYAN);
        canvas.drawCircle(centerXY,centerXY,80,mPaint);

        mPaint.setColor(Color.RED);
        String showText = "tgftgf1312321321312";
        Rect bounds = new Rect();
        //取到字符串宽高
        mPaint.getTextBounds(showText,0,showText.length(),bounds);
        Log.i(TAG, "onDraw: "+bounds.height());
        //Text的 X 为中心点 减去 字符串宽的一半
        canvas.drawText(showText,centerXY-bounds.width()/2,centerXY+bounds.height()/2,mPaint);
    }
}
