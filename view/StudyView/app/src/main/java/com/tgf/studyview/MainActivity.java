package com.tgf.studyview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.tgf.studyview.view.TitleView;
import com.tgf.studyview.view.bitmap.BitmapActivity;
import com.tgf.studyview.view.canvas.CanvasActivity;
import com.tgf.studyview.view.picture.PictureActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @InjectView(R.id.btn1)
    Button btn1;
    @InjectView(R.id.btn2)
    Button btn2;
    @InjectView(R.id.btn3)
    Button btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TitleView title_view = (TitleView) findViewById(R.id.title_view);
//        title_view.setTitleText("标题");
        title_view.setLeftBtnOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Activity重写点击事件",Toast.LENGTH_SHORT).show();
            }
        });


        Button demo1 = (Button) findViewById(R.id.demo1);
        demo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,RecyclerViewDemo.class));
            }
        });

        ButterKnife.inject(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn1:
                startActivity(new Intent(MainActivity.this, CanvasActivity.class));
                break;
            case R.id.btn2:
                startActivity(new Intent(MainActivity.this, PictureActivity.class));
                break;
            case R.id.btn3:
                startActivity(new Intent(MainActivity.this, BitmapActivity.class));
                break;
        }
    }
}
