package com.tgf.studyview.view.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.tgf.studyview.R;

/**
 * Created by 涂高峰 on 2017/5/22.
 */
public class BitmapView extends View {
    private Context mContext;
    public BitmapView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //资源文件==========================================================================================
        Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.def);
        //assets中资源文件===================================================================================
//        Bitmap bitmap = null;
//        try {
//            InputStream is = mContext.getAssets().open("def.jpeg"); //assets目录需与res同级(studio中)
//             bitmap = BitmapFactory.decodeStream(is);
//            is.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        //文件存储文件==========================================================================================
//        Bitmap bitmap = BitmapFactory.decodeFile("/sdcard/def.jpeg");
        // 此处省略了获取网络输入流的代码=========================================================================
//        Bitmap bitmap = BitmapFactory.decodeStream(is);
//        is.close();
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        //方法一
//        canvas.drawBitmap(bitmap,new Matrix(),new Paint());
        //方法二
//        canvas.drawBitmap(bitmap,200,500,new Paint());
        //方法三
        canvas.translate(getWidth()/2,getHeight()/2);
        Rect src = new Rect(0,0,bitmap.getWidth()/2,bitmap.getHeight()/2); //图片本身展示的区域
        RectF dst = new RectF(0,0,50,100); //图片在画布上展示的区域
        canvas.drawBitmap(bitmap,src,dst,new Paint());

    }
}
