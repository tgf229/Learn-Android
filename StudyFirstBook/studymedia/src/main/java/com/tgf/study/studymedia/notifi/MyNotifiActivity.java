package com.tgf.study.studymedia.notifi;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.tgf.study.studymedia.R;

public class MyNotifiActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_notifi);
        Button btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn:
                sendNoti();
                break;
        }
    }

    private void sendNoti(){
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        Intent intent = new Intent(MyNotifiActivity.this,MyNotifiActivity.class);
        PendingIntent pi = PendingIntent.getActivity(MyNotifiActivity.this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification =  new NotificationCompat.Builder(this)
                .setContentTitle("标题").setContentText("内容")
                .setTicker("我在通知栏上显示哦")
                .setWhen(SystemClock.currentThreadTimeMillis())
                .setAutoCancel(true)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))

//                .setSound(Uri.fromFile(new File("/system/media/audio/alarms/Bang.ogg")))
//                .setVibrate(new long[]{0,1000,1000,1000}) //需要设置权限
                .setDefaults(NotificationCompat.DEFAULT_ALL) //系统默认,不单独设置声音和振动

//                .setStyle(new NotificationCompat.BigTextStyle().bigText("我是长文本哦:123123123123123123213213123123213123213aadsdasdasdasdasdasdasdasdasdasdasdasdasdasdasdsadasdas"))
                .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(),R.drawable.zz_it)))

                .setPriority(NotificationCompat.PRIORITY_LOW) //min不会在通知栏, max和high会挡住通知栏

                .setContentIntent(pi)
                .build();


        manager.notify(1,notification);
    }
}
