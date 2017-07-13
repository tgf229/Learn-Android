package com.tgf.studyview.view.picture;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.RectF;
import android.graphics.drawable.PictureDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by 涂高峰 on 2017/5/22.
 */
public class PictureView extends View {
    private static final String TAG = "PictureView";
    private Picture mPicture = new Picture();
    private Paint mPaint = new Paint();

    public PictureView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint.setAntiAlias(true);
        record();
    }

    private void record(){
        Canvas canvas = mPicture.beginRecording(500,500);
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);

        canvas.translate(250,250);
        canvas.drawCircle(0,0,100,paint);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //方法一:  一般不使用,在低版本系统会影响canvas状态,操作性弱
//        mPicture.draw(canvas);
        Log.i(TAG, "mPicture.getWidth() = "+mPicture.getWidth());  //500
        //方法二:  使用drawPicture不会影响canvas的状态,可操作性强
        canvas.drawPicture(mPicture,new RectF(0,0,mPicture.getWidth(),200));
        canvas.drawPicture(mPicture);

        Log.i(TAG, "mPicture.getHeight()= "+mPicture.getHeight());  //500
        //方法三:  使用PictureDrawable不会影响canvas的状态,可操作性强
        PictureDrawable drawable = new PictureDrawable(mPicture);
        drawable.setBounds(0,200,250,mPicture.getHeight());
        drawable.draw(canvas);
    }
}
