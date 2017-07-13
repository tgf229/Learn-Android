package com.tgf.study.studyactivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;

public class SecondActivity extends BaseActivity {

    private static final String TAG = "SecondActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);
//        String data = getIntent().getStringExtra("data");
//        BookBean b = getIntent().getParcelableExtra("book_data");
//        Log.i(TAG, "onCreate: book_data: name= "+b.getName() +" pages= "+b.getPages());
//        Log.i(TAG, "onCreate: data= "+data);

        alarmManagerTest();
    }

    //Android2.0之后提供back物理按键,不用写onKeyDown了
    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("data_return","返回的数据哦");
        setResult(RESULT_OK,intent);
//        finish();
        super.onBackPressed();  //super.onBackPressed()内部执行了finish().所以放最后且不用写finish()了
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }

    private void alarmManagerTest(){
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(SecondActivity.this,SecondActivity.class);
        PendingIntent pi = PendingIntent.getActivity(SecondActivity.this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        long triggerTime = SystemClock.elapsedRealtime() + 2 * 1000;
        Log.i(TAG, "alarmManagerTest:SystemClock.elapsedRealtime=  "+SystemClock.elapsedRealtime());
        Log.i(TAG, "alarmManagerTest:System.currentTimeMillis()=  "+System.currentTimeMillis());
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,triggerTime,pi);
    }
}
