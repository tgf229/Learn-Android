package com.tgf.study.studybroadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.tgf.study.studybroadcastreceiver.Example.LoginActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private NetChangeReceiver receiver;

    private LocalBroadcastManager manager;
    private LocalReceiver localReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //网络改变的广播
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE"); //网络状态改变
        receiver = new NetChangeReceiver();
        registerReceiver(receiver,filter);

        manager = LocalBroadcastManager.getInstance(MainActivity.this);
        IntentFilter localFilter = new IntentFilter();
        localFilter.addAction("com.tgf.study.MY_LOCAL");
        localReceiver = new LocalReceiver();
        manager.registerReceiver(localReceiver,localFilter);

        Button btn = (Button)findViewById(R.id.btn);
        Button btn2 = (Button)findViewById(R.id.btn2);
        Button btn3 = (Button)findViewById(R.id.btn3);
        btn.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
        manager.unregisterReceiver(localReceiver);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn:
                Intent intent = new Intent("com.tgf.study.studybroadcastreceiver.MY_BROADCAST");
//                sendBroadcast(intent);
                sendOrderedBroadcast(intent,null);  //有序广播
                break;
            case R.id.btn2:
                manager.sendBroadcast(new Intent("com.tgf.study.MY_LOCAL"));
                break;
            case R.id.btn3:
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                break;
        }
    }

    class LocalReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context,"收到了本地广播",Toast.LENGTH_SHORT).show();
        }
    }

    class NetChangeReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager manager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = manager.getActiveNetworkInfo(); //需Manifest注册
            if (info != null && info.isAvailable()){
                Toast.makeText(context,"network is available",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(context,"network is unavailable",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
