package com.tgf.path.view.advance;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 涂高峰 on 2017/5/26.
 */
public class BoundsView extends View {
    private Paint mPaint = new Paint();
    public BoundsView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(getWidth()/2,getHeight()/2);

        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL);
        Path path = new Path();
        path.lineTo(100,50);
        path.lineTo(100,-50);
        path.close();
        path.addCircle(-100,0,100, Path.Direction.CW);

        RectF rectF = new RectF();
        path.computeBounds(rectF,true);
        canvas.drawPath(path,mPaint);

        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(rectF,mPaint);

    }
}
