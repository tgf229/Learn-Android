package com.tgf.path.view.basic;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 涂高峰 on 2017/5/23.
 */
public class BasicView extends View {
    private static final String TAG = "BasicView";
    Paint mPaint = new Paint();
    public BasicView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(getWidth()/2,getHeight()/2);

        Path path = new Path();
        path.lineTo(100,100);
//        path.moveTo(100,50);
        path.setLastPoint(100,50);
        path.lineTo(100,0);
        path.close(); //闭环 形成三角

        path.moveTo(0,0);
        path.addRect(-150,-150,150,150, Path.Direction.CW);
        path.setLastPoint(-200,200); //不规则矩形

        Path src = new Path(); //第二个Path
        src.addCircle(0,0,100, Path.Direction.CW);
        path.addPath(src,0,100); //偏移将圆合并

        canvas.drawPath(path,mPaint);

    }
}
