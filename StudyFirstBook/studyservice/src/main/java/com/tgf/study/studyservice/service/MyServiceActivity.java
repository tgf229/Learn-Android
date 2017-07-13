package com.tgf.study.studyservice.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.tgf.study.studyservice.R;

public class MyServiceActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "MyServiceActivity";
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyService.MyBinder myBinder = (MyService.MyBinder) service;
            myBinder.start();
            myBinder.end();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_service);

        Log.i(TAG, "getExternalCacheDir(): "+ getExternalCacheDir()); // /storage/emulated/0/Android/data/com.tgf.study.studymedia/cache
        Log.i(TAG, "getExternalStorageDirectory: "+ Environment.getExternalStorageDirectory()); // /storage/emulated/0 
        Log.i(TAG, "onCreate: "+ Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath()); // /storage/emulated/0/Download

        Button btn_1 = (Button) findViewById(R.id.btn_1);
        Button btn_2 = (Button) findViewById(R.id.btn_2);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        Button btn_3 = (Button) findViewById(R.id.btn_3);
        btn_3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_1:
                Intent intent = new Intent(MyServiceActivity.this,MyService.class);
                startService(intent);
                bindService(intent,serviceConnection,BIND_AUTO_CREATE);
                break;
            case R.id.btn_2:
                unbindService(serviceConnection);
                break;
            case R.id.btn_3:
                Log.i(TAG, "主进程=: "+Process.myPid());
                Log.i(TAG, "主线程=: "+ Thread.currentThread().getId());
                startService(new Intent(this,MyIntentService.class));
                break;
        }
    }
}
