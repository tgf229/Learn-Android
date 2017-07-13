package com.tgf.study.studyservice.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by tugaofeng on 17/3/27.
 */
public class MyIntentService extends IntentService {

    private static final String TAG = "MyIntentService";

    public MyIntentService() {
        super("");
    }
    @Override
    public void onCreate() {
        Log.i(TAG, "onCreate: ");
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy: ");
        super.onDestroy();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i(TAG, "onHandleIntent: service线程=  "+Thread.currentThread().getId());
    }
}
