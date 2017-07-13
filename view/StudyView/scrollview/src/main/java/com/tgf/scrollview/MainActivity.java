package com.tgf.scrollview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tgf.scrollview.viewdraghelper.ViewDragHelperActivity;
import com.tgf.scrollview.viewpage.ViewPageActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(this);
        Button btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn:
                startActivity(new Intent(this, ViewPageActivity.class));
                break;
            case R.id.btn1:
                startActivity(new Intent(this, ViewDragHelperActivity.class));
                break;
        }
    }
}
