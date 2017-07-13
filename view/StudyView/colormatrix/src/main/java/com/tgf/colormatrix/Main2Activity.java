package com.tgf.colormatrix;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "Main2Activity";
    private GridLayout mGroup;
    private EditText[] edts = new EditText[20];
    private Bitmap bitmap;
    private float[] mColorMatrix = new float[20];
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.def);
        img = (ImageView) findViewById(R.id.img);
        img.setImageBitmap(bitmap);
        mGroup = (GridLayout) findViewById(R.id.group);

        Button btn1 = (Button) findViewById(R.id.btn1);
        Button btn2 = (Button) findViewById(R.id.btn2);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);

        initEdts();
        initMatrix();
    }

    //初始化EditText
    private void initEdts(){
        Log.i(TAG, "mGroup.getHeight(): "+mGroup.getHeight());
        for (int i=0;i<20;i++){
            EditText editText = new EditText(this);
            edts[i] = editText;
            mGroup.addView(editText,getResources().getDisplayMetrics().widthPixels/5,100);
        }
    }

    //初始化矩阵值
    private void initMatrix(){
        for (int i=0; i<20; i++){
            if (i%6 == 0){
                edts[i].setText("1");
            }else {
                edts[i].setText("0");
            }
        }
    }

    //获取矩阵值
    private void getMatrix(){
        for (int i=0;i<20;i++){
            mColorMatrix[i] = Float.parseFloat(edts[i].getText().toString());
        }
    }

    //将矩阵设置到图像
    private void setImageMatrix(){
        //原始图片是不能修改的,可根据原始图片生成新图进行修改
        Bitmap bmp = Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(mColorMatrix));
        canvas.drawBitmap(bitmap,0,0,paint);
        img.setImageBitmap(bmp);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //设置值
            case R.id.btn1:
                getMatrix();
                setImageMatrix();
                break;
            //重置
            case R.id.btn2:
                initMatrix();
                getMatrix();
                setImageMatrix();
                break;
        }
    }
}
