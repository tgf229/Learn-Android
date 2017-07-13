package com.tgf.study.studyactivity;

import android.os.Bundle;
import android.util.Log;

public class ThirdActivity extends BaseActivity {
    private static final String TAG = "ThirdActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_layout);

        Log.i(TAG, "onCreate: "+getIntent().getDataString());

        //看manifest,配置了  <data android:scheme="http"/> ,
        // 当有Intent请求setData()Uri.parse("http://xxx"),就会弹出选择可以跳转到此Activity中
        //可以在FirstActivity中点击button_2测试
    }
}
