package com.tgf.surfaceview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by 涂高峰 on 2017/7/5.
 */
public class SurfaceViewDemo extends SurfaceView implements SurfaceHolder.Callback,Runnable {
    private static final String TAG = "SurfaceViewDemo";
    private int x;
    private int y;
    private SurfaceHolder mHolder;
    private Canvas mCanvas;
    private boolean mIsDrawing; //控制子线程
    private Path mPath;
    private Paint mPaint;

    public SurfaceViewDemo(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    //初始化
    private void init(){
        mHolder = getHolder(); //初始化SurfaceHolder
        mHolder.addCallback(this); //注册SurfaceHolder的回调方法
        mPath = new Path();
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    //开启子线程进行绘制
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mIsDrawing = true;
        new Thread(this).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mIsDrawing = false;
    }

    @Override
    public void run() {
        //子线程中通过while不断绘制
        while (mIsDrawing){
            draw();
            x += 1;
            y = (int) (Math.sin(x*2*Math.PI/180)*100)+400;
            mPath.lineTo(x,y);
        }
    }

    private void draw(){
        try {
            mCanvas = mHolder.lockCanvas(); //获得canvas对象
            mCanvas.drawColor(Color.WHITE);
            mCanvas.drawPath(mPath,mPaint);
        }catch (Exception e){

        }finally {
            if (mCanvas != null){
                mHolder.unlockCanvasAndPost(mCanvas); //提交画布内容
            }
        }
    }
}
