package com.tgf.studyview.view.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 涂高峰 on 2017/5/19.
 */
public class SkewView extends View {
    private Paint mPaint = new Paint();
    public SkewView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(2f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(getWidth()/2,getHeight()/2);
        RectF rectF = new RectF(0,0,100,100);

        mPaint.setColor(Color.BLUE);
        canvas.drawRect(rectF,mPaint);

        canvas.skew(1,0);

        mPaint.setColor(Color.BLACK);
        canvas.drawRect(rectF,mPaint);

        canvas.skew(0,1);
        mPaint.setColor(Color.RED);
        canvas.drawRect(rectF,mPaint);

    }
}
