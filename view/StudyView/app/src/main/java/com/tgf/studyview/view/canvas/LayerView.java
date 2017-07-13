package com.tgf.studyview.view.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 涂高峰 on 2017/6/28.
 */
public class LayerView extends View {
    private Paint mPaint;
    public LayerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);
        mPaint.setColor(Color.BLUE);
        canvas.drawCircle(150,150,100,mPaint);

        // save将红色图层入栈
        // 0透明 127半透明 255不透明
        canvas.saveLayerAlpha(0,0,400,400,127,LAYER_TYPE_NONE);
        mPaint.setColor(Color.RED);
        canvas.drawCircle(200,200,100,mPaint);
        canvas.restore();  //图层出栈

    }
}
