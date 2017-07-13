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
public class ScaleView extends View {
    private Paint mPaint = new Paint();
    public ScaleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        RectF rectF = new RectF(0,-200,200,0);
        canvas.translate(getWidth()/2,getHeight()/2); //移动画布原始点至view中心

        mPaint.setColor(Color.BLUE);
        canvas.drawRect(rectF,mPaint);    //在数学坐标系的第一半轴画矩形

        mPaint.setColor(Color.BLACK);
        canvas.scale(0.5f,0.5f);    //缩放一半 画矩形
        canvas.drawRect(rectF,mPaint);

        mPaint.setColor(Color.RED);
        canvas.scale(-0.5f,-0.5f);   //翻转
        canvas.drawRect(rectF,mPaint);

        mPaint.setColor(Color.CYAN);
        canvas.scale(-0.5f,-0.5f,200,0);  //本次对缩放中心点x轴坐标进行了偏移，故中心轴也向右偏移了
        canvas.drawRect(rectF,mPaint);  //顺序:  缩放->偏移->翻转

    }
}
