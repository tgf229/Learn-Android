package com.tgf.studyview.view.pie;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tgf on 2017/5/16.
 * 示例参考 http://www.gcssloop.com/customview/Canvas_BasicGraphics
 */

public class PieView extends View{
    private static final String TAG = "PieView";
    private Paint mPaint = new Paint(); //初始化画笔
    private List<PieBean> mList = new ArrayList<>(); //数据集
    private float mCurrentAngle = 0;
    //颜色数组
    private int[] mColors = {Color.YELLOW,Color.BLUE, Color.CYAN,Color.GREEN,Color.RED};
    public PieView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        setData(new ArrayList<PieBean>());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(getWidth()/2,getHeight()/2);
        float r = (float) (Math.min(getWidth(),getHeight())/2 * 0.8);
        Log.i(TAG, "onDraw: r="+r);

        RectF rectF = new RectF(-r,-r,r,r);

        for (PieBean b:mList) {
            mPaint.setColor(b.getColor());
            canvas.drawArc(rectF,mCurrentAngle,b.getAngle(),true,mPaint);
            mCurrentAngle += b.getAngle() ;
        }
    }

    public void setData(List<PieBean> list){
        mList = list;
        initData();
        handleData();
//        invalidate();
    }

    private void handleData(){
        float sumValue = 0;
        for (int i=0 ;i<mList.size(); i++) {
            sumValue += Integer.parseInt(mList.get(i).getValue());
            mList.get(i).setColor(mColors[i % mColors.length]);
        }
        for (PieBean b:mList) {
            float percent = Float.parseFloat(b.getValue()) / sumValue;
            float angle = Math.round(percent * 360);
            b.setPercent(percent);
            b.setAngle(angle);
            Log.i(TAG, "handleData: "+b.toString());
        }
    }

    private void initData(){
        PieBean b1 = new PieBean("中国","42");
        PieBean b2 = new PieBean("韩国","32");
        PieBean b3 = new PieBean("日本","82");
        PieBean b4 = new PieBean("俄罗斯","5");
        PieBean b5 = new PieBean("泰国","53");
        mList.add(b1);
        mList.add(b2);
        mList.add(b3);
        mList.add(b4);
        mList.add(b5);
    }
}
