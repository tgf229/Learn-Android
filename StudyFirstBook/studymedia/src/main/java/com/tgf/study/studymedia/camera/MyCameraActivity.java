package com.tgf.study.studymedia.camera;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.tgf.study.studymedia.R;

import java.io.File;

public class MyCameraActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MyCameraActivity";
    private ImageView img;
    private Uri imgUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_camera);
        Button btn = (Button) findViewById(R.id.btn);
        img = (ImageView) findViewById(R.id.img);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn:
                camera();
                break;
        }
    }

    private void camera(){
        Log.i(TAG, "getExternalCacheDir(): "+ getExternalCacheDir()); ///storage/emulated/0/Android/data/com.tgf.study.studymedia/cache
        Log.i(TAG, "getExternalStorageDirectory: "+ Environment.getExternalStorageDirectory()); ///storage/emulated/0

        //指定保存路径为应用缓存目录,每次拍照这个img.jpg会被替换
        File imgFile = new File(getExternalCacheDir(),"img.jpg");

        if (Build.VERSION.SDK_INT >= 24){
            imgUri = FileProvider.getUriForFile(MyCameraActivity.this,getPackageName(),imgFile);
        }else{
            imgUri = Uri.fromFile(imgFile);
        }

        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT,imgUri);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1:
                Bitmap bitmap = null;
                try {
                    bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imgUri));
//                    bitmap = BitmapFactory.decodeFile(getExternalCacheDir()+"/img.jpg");
                    img.setImageBitmap(bitmap);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
