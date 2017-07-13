package com.tgf.path;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.tgf.path.view.advance.AdvanceActivity;
import com.tgf.path.view.basic.BasicActivity;
import com.tgf.path.view.bezier.BezierActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(this);
        Button btn2 = (Button) findViewById(R.id.btn2);
        btn2.setOnClickListener(this);
        Button btn3 = (Button) findViewById(R.id.btn3);
        btn3.setOnClickListener(this);

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                startActivity(new Intent(MainActivity.this, BasicActivity.class));
                break;
            case R.id.btn2:
                startActivity(new Intent(MainActivity.this, BezierActivity.class));
                break;
            case R.id.btn3:
                startActivity(new Intent(MainActivity.this, AdvanceActivity.class));
                break;
        }
    }
}
