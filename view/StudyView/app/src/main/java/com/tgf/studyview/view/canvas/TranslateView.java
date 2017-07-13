package com.tgf.studyview.view.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 涂高峰 on 2017/5/16.
 */
public class TranslateView extends View{
    private Paint mPaint = new Paint();
    public TranslateView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(100,100);
        mPaint.setColor(Color.BLUE);
        canvas.drawRect(0,0,100,100,mPaint);

        //translate是基于当前位置移动,所以相同代码 同样是translate(100,100),的确移动了
        canvas.translate(100,100);
        mPaint.setColor(Color.RED);
        canvas.drawRect(0,0,100,100,mPaint);

    }
}
