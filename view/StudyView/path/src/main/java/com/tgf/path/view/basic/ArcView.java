package com.tgf.path.view.basic;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 涂高峰 on 2017/5/23.
 */
public class ArcView extends View {
    Paint mPaint = new Paint();
    public ArcView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(4);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(getWidth()/2,getHeight()/2);
        canvas.scale(1,-1); //翻转y轴,变为熟悉的数学坐标而非屏幕坐标
        Path path = new Path();
        path.lineTo(50,50);

        RectF rectF = new RectF(0,0,100,100);
//        path.addArc(rectF,0,270);
        path.arcTo(rectF,0,270);

        path.lineTo(150,0);

        canvas.drawPath(path,mPaint);
    }
}
