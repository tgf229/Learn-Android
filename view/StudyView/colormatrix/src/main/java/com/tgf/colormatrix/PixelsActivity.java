package com.tgf.colormatrix;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class PixelsActivity extends AppCompatActivity {

    private  Bitmap bitmap;
    private ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pixels);
        bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.def);
        img = (ImageView) findViewById(R.id.img);

        handleImage();
    }

    private void handleImage(){
        //创建临时bitmap
        Bitmap bmp = Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(), Bitmap.Config.ARGB_8888);

        int[] oldPx = new int[bitmap.getWidth()*bitmap.getHeight()];
        int[] newPx = new int[bitmap.getWidth()*bitmap.getHeight()];

        //pixels - 接收位图颜色值的数组
        //offset - 写入到pixels[]中的第一个像素索引值
        //stride - pixels[]中的行间距
        // x     - 从位图中读取的第一个像素x坐标值
        // y     - 从位图中读取的第一个像素y坐标值
        //width  - 从每一行中读取的像素宽度
        //height - 读取的行数
        bitmap.getPixels(oldPx,0,bitmap.getWidth(),0,0,bitmap.getWidth(),bitmap.getHeight());

        for (int i=1;i<bitmap.getWidth()*bitmap.getHeight();i++){
            int color = oldPx[i-1];
            int r = Color.red(color);
            int g = Color.green(color);
            int b = Color.blue(color);
            int a = Color.alpha(color);

            int color2 = oldPx[i];
            int r2 = Color.red(color2);
            int g2 = Color.green(color2);
            int b2 = Color.blue(color2);
            int a2 = Color.alpha(color2);

            //底片效果
//            r = 255-r;
//            g = 255-g;
//            b = 255-b;
            //老照片效果
//            r = (int) (0.393*r+0.769*g+0.189*b);
//            g = (int) (0.349*r+0.686*g+0.168*b);
//            b = (int) (0.272*r+0.534*g+0.131*b);
            //浮雕效果
            r = r-r2+127;
            g = g-g2+127;
            b = b-b2+127;

            if (r>255){
                r=255;
            }else if (r<0){
                r=0;
            }
            if (g>255){
                g=255;
            }else if (g<0){
                g=0;
            }
            if (b>255){
                b=255;
            }else if (b<0){
                b=0;
            }
            newPx[i] = Color.argb(a,r,g,b);
        }
        bmp.setPixels(newPx,0,bitmap.getWidth(),0,0,bitmap.getWidth(),bitmap.getHeight());
        img.setImageBitmap(bmp);
    }
}
