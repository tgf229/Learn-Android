package com.tgf.path.view.advance;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 涂高峰 on 2017/5/26.
 */
public class AdvanceView extends View{
    private Paint mPaint = new Paint();
    public AdvanceView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    @SuppressLint("NewApi")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(getWidth() / 2, getHeight() / 2);

        Path path1 = new Path();
        Path path2 = new Path();
        Path path3 = new Path();
        Path path4 = new Path();

        path1.addArc(-200,-200,200,200,90,180);
        path2.addCircle(0,-100,100, Path.Direction.CW);
        path3.addCircle(0,100,100,Path.Direction.CW);
        path4.addCircle(-100,-100,30, Path.Direction.CW);

        path1.op(path2,Path.Op.UNION);
        path1.op(path3, Path.Op.DIFFERENCE);
        path1.op(path4,Path.Op.DIFFERENCE);

        canvas.drawPath(path1, mPaint);

    }
}
