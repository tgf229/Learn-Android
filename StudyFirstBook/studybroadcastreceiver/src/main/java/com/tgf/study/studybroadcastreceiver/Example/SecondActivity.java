package com.tgf.study.studybroadcastreceiver.Example;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tgf.study.studybroadcastreceiver.R;

public class SecondActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Button btn = (Button)findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.tgf.study.OFFLINE_BROADCAST");
                localBroadcastManager.sendBroadcast(intent);
            }
        });
    }
}
