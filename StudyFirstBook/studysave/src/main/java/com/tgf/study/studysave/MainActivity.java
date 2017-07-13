package com.tgf.study.studysave;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.tgf.study.studysave.file.FileActivity;
import com.tgf.study.studysave.sharedpreferences.ShareActivity;
import com.tgf.study.studysave.spExample.LoginActivity;
import com.tgf.study.studysave.sql.SqliteActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_1 = (Button)findViewById(R.id.btn_1);
        btn_1.setOnClickListener(this);
        Button btn_2 = (Button)findViewById(R.id.btn_2);
        btn_2.setOnClickListener(this);
        Button btn_3 = (Button)findViewById(R.id.btn_3);
        btn_3.setOnClickListener(this);
        Button btn_4 = (Button)findViewById(R.id.btn_4);
        btn_4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_1:
                startActivity(new Intent(MainActivity.this, FileActivity.class));
                break;
            case R.id.btn_2:
                startActivity(new Intent(MainActivity.this, ShareActivity.class));
                break;
            case R.id.btn_3:
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                break;
            case R.id.btn_4:
                startActivity(new Intent(MainActivity.this, SqliteActivity.class));
                break;
        }
    }
}
