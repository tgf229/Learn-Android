package com.tgf.study.studymedia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.tgf.study.studymedia.camera.MyAlbumActivity;
import com.tgf.study.studymedia.camera.MyCameraActivity;
import com.tgf.study.studymedia.notifi.MyNotifiActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_1 = (Button) findViewById(R.id.btn_1);
        btn_1.setOnClickListener(this);
        Button btn_2 = (Button) findViewById(R.id.btn_2);
        btn_2.setOnClickListener(this);
        Button btn_3 = (Button) findViewById(R.id.btn_3);
        btn_3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_1:
                startActivity(new Intent(MainActivity.this, MyNotifiActivity.class));
                break;
            case R.id.btn_2:
                startActivity(new Intent(MainActivity.this, MyCameraActivity.class));
                break;
            case R.id.btn_3:
                startActivity(new Intent(MainActivity.this, MyAlbumActivity.class));
                break;
        }
    }
}
