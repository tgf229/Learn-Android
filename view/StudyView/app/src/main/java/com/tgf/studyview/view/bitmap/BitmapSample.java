package com.tgf.studyview.view.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import com.tgf.studyview.R;

/**
 * Created by 涂高峰 on 2017/5/22.
 */
public class BitmapSample extends View {
    private static final String TAG = "BitmapSample";
    private Paint mPaint = new Paint();
    private int currentPage = 0;
    private int maxPage = 13-1;
    private Handler handler;
    private int duration = 40;
    public BitmapSample(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint.setAntiAlias(true);
        mPaint.setColor(0xffFF5317);
        init();
    }
    private void init(){
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (currentPage < maxPage){
                    invalidate();
                    currentPage++;
                    sendEmptyMessageDelayed(0,duration);
                }else{

                }
            }
        };
        handler.sendEmptyMessageDelayed(0,duration);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(getWidth()/2,getHeight()/2);
        canvas.drawCircle(0,0,150,mPaint);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.checkmark);
        int height = bitmap.getHeight();
        Rect src = new Rect(currentPage*height,0,bitmap.getHeight()*(currentPage+1), bitmap.getHeight());
        RectF dst = new RectF(-100,-100,100,100);
        canvas.drawBitmap(bitmap,src,dst,null);

    }
}
