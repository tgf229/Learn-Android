package com.tgf.study.studyactivity.lifecycle;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.tgf.study.studyactivity.R;

public class NormalActivity extends AppCompatActivity {
    private static final String TAG = "NormalActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.normal_layout);

        //如果不为null,说明被系统回收的,可以通过bundle获取之前保存的数据
        if (savedInstanceState != null){
            String str = savedInstanceState.getString("data_string");
            Log.i(TAG, "onCreate: "+str);
        }else{
            // intent存放的数据,得通过getBundleExtra获取,而不是onCreate(xxx)的参数
            Log.i(TAG, "onCreate: "+getIntent().getBundleExtra("data_bundle").getString("data_string"));
        }
    }

    //系统回收资源的时候会在此方法存储,并通过onCreate获取
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("data_string","onSaveInstanceState字符串");
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
}
