package com.tgf.study.studyactivity.lifecycle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.tgf.study.studyactivity.R;

public class LifeActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "LifeActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.life_layout);
        Log.i(TAG, "onCreate: ");

        Button button1 = (Button)findViewById(R.id.button_1);
        button1.setOnClickListener(this);
        Button button2 = (Button)findViewById(R.id.button_2);
        button2.setOnClickListener(this);
    }

    @Override
    protected void onRestart() {
        Log.i(TAG, "onRestart: ");
        super.onRestart();
    }

    @Override
    protected void onStart() {
        Log.i(TAG, "onStart: ");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.i(TAG, "onResume: ");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.i(TAG, "onPause: ");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.i(TAG, "onStop: ");
        super.onStop();
    }
    @Override
    protected void onDestroy() {
        Log.i(TAG, "onDestroy: ");
        super.onDestroy();
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_1:
                Intent intent = new Intent(LifeActivity.this,NormalActivity.class);
                Bundle data_data = new Bundle();
                data_data.putString("data_string","Bundle传递的string字符串");
                intent.putExtra("data_bundle",data_data);
                startActivity(intent);
                break;
            case R.id.button_2:
                Intent intent1 = new Intent(LifeActivity.this,DialogActivity.class);
                startActivity(intent1);
                break;
        }
    }


}
