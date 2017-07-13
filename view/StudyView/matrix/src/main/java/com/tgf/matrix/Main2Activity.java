package com.tgf.matrix;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ImageView img = (ImageView) findViewById(R.id.img);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.def);

        Bitmap bmp = Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        Matrix matrix = new Matrix();
//        matrix.setTranslate(100,0);
        matrix.setRotate(90);
        canvas.drawBitmap(bitmap,matrix,null);
        img.setImageBitmap(bmp);
    }
}
