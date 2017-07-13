package com.tgf.study.studybroadcastreceiver.Example;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by tugaofeng on 17/3/20.
 */
public class BaseActivity extends AppCompatActivity {
    private static final String TAG = "BaseActivity";

    protected LocalBroadcastManager localBroadcastManager;
    private OfflineReceiver receiver = new OfflineReceiver();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityController.addActivity(this);
        ActivityController.currentActivity = this;
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        Log.i(TAG, "onCreate: "+this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.tgf.study.OFFLINE_BROADCAST");
        localBroadcastManager.registerReceiver(receiver,filter);
//        registerReceiver(receiver,filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (receiver != null){
            localBroadcastManager.unregisterReceiver(receiver);
//            unregisterReceiver(receiver);
            receiver = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityController.removeActivity(this);
    }

    class OfflineReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(final Context context, Intent intent) {
            Log.i(TAG, "onReceive: "+context); //注意若本地广播,context为Application,无法操作Dialog
//            AlertDialog.Builder dialog = new AlertDialog.Builder(context);
            AlertDialog.Builder dialog = new AlertDialog.Builder(ActivityController.currentActivity);
            dialog.setTitle("警告");
            dialog.setMessage("您以被下线");
            dialog.setCancelable(false);
            dialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ActivityController.clearAll();
                    startActivity(new Intent(context,LoginActivity.class));
                }
            });
            dialog.show();
        }
    }
}
