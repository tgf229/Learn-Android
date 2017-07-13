package com.tgf.colormatrix;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener{
    private static final String TAG = "MainActivity";
    private ImageView img;
    private Bitmap bm;
    private int mHue=1,mSaturation=1,mLum=1;
    private SeekBar seek_hue,seek_saturation,seek_lum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seek_hue = (SeekBar) findViewById(R.id.seek_hue);
        seek_saturation = (SeekBar) findViewById(R.id.seek_saturation);
        seek_lum = (SeekBar) findViewById(R.id.seek_lum);
        seek_hue.setOnSeekBarChangeListener(this);
        seek_saturation.setOnSeekBarChangeListener(this);
        seek_lum.setOnSeekBarChangeListener(this);

        bm = BitmapFactory.decodeResource(getResources(),R.drawable.def);
        img = (ImageView) findViewById(R.id.img);
    }

    private Bitmap handleImageEffect(Bitmap bm,float hue,float saturation,float lum){
        Bitmap bmp = Bitmap.createBitmap(bm.getWidth(),bm.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        Paint paint = new Paint();

        //色调
        ColorMatrix hueMatrix = new ColorMatrix();
        hueMatrix.setRotate(0,hue);
        hueMatrix.setRotate(1,hue);
        hueMatrix.setRotate(2,hue);

        //保饱和度
        ColorMatrix saturationMatrix = new ColorMatrix();
        saturationMatrix.setSaturation(saturation);

        //亮度
        ColorMatrix lumMatrix = new ColorMatrix();
        lumMatrix.setScale(lum,lum,lum,1);

        ColorMatrix imageMatrix = new ColorMatrix();
        imageMatrix.postConcat(hueMatrix);
        imageMatrix.postConcat(saturationMatrix);
        imageMatrix.postConcat(lumMatrix);

        paint.setColorFilter(new ColorMatrixColorFilter(imageMatrix));
        canvas.drawBitmap(bm,0,0,paint);
        return bmp;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        Log.i(TAG, "============================ ");
        switch (seekBar.getId()){
            case R.id.seek_hue:
                mHue = progress;
                break;
            case R.id.seek_saturation:
                mSaturation = progress;
                break;
            case R.id.seek_lum:
                mLum = progress;
                break;
        }
        Log.i(TAG, "mHue= "+mHue+" mSaturation= "+mSaturation+" mLum= "+mLum);
        img.setImageBitmap(handleImageEffect(bm,mHue,mSaturation,mLum));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    }
}
