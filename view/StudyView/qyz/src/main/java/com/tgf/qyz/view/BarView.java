package com.tgf.qyz.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

/**
 * Created by 涂高峰 on 2017/6/9.
 */
public class BarView extends View {
    private Paint mPaint = new Paint();
    private static final int M = 30;//柱状图宽度
    private int translate;
    private Matrix matrix;
    private LinearGradient linearGradient;
    public BarView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //线性渐变着色器
        linearGradient = new LinearGradient(0,0,getWidth(),0,new int[]{Color.GREEN,Color.RED,Color.CYAN},null, Shader.TileMode.CLAMP);
        mPaint.setShader(linearGradient);
        matrix = new Matrix();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL);

        //调整canvas原点
        canvas.translate(0,getHeight());

        for (int i=0; i<20; i++){
            Random random = new Random();
            int j = random.nextInt(300)+10;
            canvas.drawRect(M*i+1,0,M*(i+1),-j,mPaint);
        }

        // 线性渐变
        if (matrix!=null){
            translate += getWidth()/20;
            if (translate > getWidth()/2){
                translate = -getWidth();
            }
            matrix.setTranslate(translate,0);
            linearGradient.setLocalMatrix(matrix);
        }

        //动态渲染
        postInvalidateDelayed(1000);
    }
}
