package com.tgf.paint;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.tgf.paint.porterDuffXfermode.Main2Activity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView img = (ImageView) findViewById(R.id.img);
        Button btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(this);

        Bitmap mBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.def);

        Bitmap bmp = Bitmap.createBitmap(mBitmap.getWidth(),mBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        Paint mPaint = new Paint();
        mPaint.setAntiAlias(true);


        RectF rectF = new RectF(0,0,mBitmap.getWidth(),mBitmap.getHeight());
        //画圆
        canvas.drawRoundRect(rectF,mBitmap.getWidth()/2,mBitmap.getHeight()/2,mPaint);

        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(mBitmap,0,0,mPaint);

        img.setImageBitmap(bmp);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn1:
                startActivity(new Intent(this, Main2Activity.class));
                break;
        }
    }
}
