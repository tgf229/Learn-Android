package com.tgf.studyview.view;

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
 * Created by 涂高峰 on 2017/5/12.
 * 计数器 自定义view
 * 根据 http://blog.csdn.net/guolin_blog/article/details/17357967
 */
public class CounterView extends View implements View.OnClickListener{
    private static final String TAG = "CounterView";
    private int countNum;
    private Paint mPaint;
    private Rect rect;
    public CounterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        rect = new Rect();
        setOnClickListener(this);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.i(TAG, "onSizeChanged: w="+ w + "h=" +h);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setColor(Color.GREEN);
        RectF rectF = new RectF(0,0,getWidth(),getHeight());
        canvas.drawRect(rectF,mPaint);  //画矩形背景
        mPaint.setColor(Color.RED);
        canvas.drawRoundRect(rectF,getWidth()/2,getHeight()/2,mPaint);

        mPaint.setColor(Color.BLUE);
        mPaint.setTextSize(24);

        String str = String.valueOf(countNum);

        mPaint.getTextBounds(str,0,str.length(),rect);

        Log.d(TAG, "onDraw: "+"layout宽= "+getWidth()+",  layout高= "+getHeight());
        Log.d(TAG, "onDraw: "+"text矩形宽= "+rect.width()+",  text矩形高= "+rect.height());

        canvas.drawText(str,getWidth()/2-rect.width()/2,getHeight()/2-rect.height()/2,mPaint);
    }

    @Override
    public void onClick(View v) {
        countNum++;
        invalidate();   //重绘
    }
}
