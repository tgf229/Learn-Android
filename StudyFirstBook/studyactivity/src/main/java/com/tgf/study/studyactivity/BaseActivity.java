package com.tgf.study.studyactivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by tugaofeng on 17/3/15.
 */
public class BaseActivity extends AppCompatActivity{
    private static final String TAG = "BaseActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //打印结果:
//        onCreate: getClass().getName()=com.tgf.study.studyactivity.SecondActivity
//        onCreate: getClass().getSimpleName()=SecondActivity
        Log.i(TAG, "onCreate: getClass().getName()="+getClass().getName());
        Log.i(TAG, "onCreate: getClass().getSimpleName()="+getClass().getSimpleName());
    }
}
