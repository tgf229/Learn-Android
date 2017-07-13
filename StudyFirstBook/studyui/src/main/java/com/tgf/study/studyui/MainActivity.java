package com.tgf.study.studyui;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.tgf.study.studyui.chatroom.MessageActivity;
import com.tgf.study.studyui.listview.ListViewActivity;
import com.tgf.study.studyui.recyclerview.RecyclerViewActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ProgressBar progress_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progress_bar = (ProgressBar)findViewById(R.id.progress_bar);

        Button button_1 = (Button)findViewById(R.id.button_1);
        button_1.setOnClickListener(this);
        Button button_2 = (Button)findViewById(R.id.button_2);
        button_2.setOnClickListener(this);
        Button button_3 = (Button)findViewById(R.id.button_3);
        button_3.setOnClickListener(this);
        Button button_4 = (Button)findViewById(R.id.button_4);
        button_4.setOnClickListener(this);
        Button button_5 = (Button)findViewById(R.id.button_5);
        button_5.setOnClickListener(this);
        Button button_6 = (Button)findViewById(R.id.button_6);
        button_6.setOnClickListener(this);

        //自定义控件的button
        Button button_edit = (Button)findViewById(R.id.button_edit);
        button_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"点击编辑",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        progress_bar.setProgress(50);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_1:
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setCancelable(false); //false设置点遮罩或back不会返回
                dialog.setIcon(ContextCompat.getDrawable(MainActivity.this,R.drawable.zz_it));
                dialog.setTitle("title");
                dialog.setMessage("message");
                dialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"ok",Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"cancel",Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.show();
                break;

            case R.id.button_2:
                ProgressDialog dialog_progress = new ProgressDialog(MainActivity.this);
                dialog_progress.setIcon(ContextCompat.getDrawable(MainActivity.this,R.drawable.zz_it));
                dialog_progress.setTitle("title");
                dialog_progress.setMessage("message");
                dialog_progress.setCancelable(true);
                dialog_progress.show();
                break;

            case R.id.button_3:
                startActivity(new Intent(MainActivity.this,PercentActivity.class));
                break;
            case R.id.button_4:
                startActivity(new Intent(MainActivity.this,ListViewActivity.class));
                break;
            case R.id.button_5:
                startActivity(new Intent(MainActivity.this, RecyclerViewActivity.class));
                break;
            case R.id.button_6:
                startActivity(new Intent(MainActivity.this, MessageActivity.class));
                break;
        }
    }
}
