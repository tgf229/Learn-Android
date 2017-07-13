package com.tgf.study.studyservice;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.tgf.study.studyservice.downloadSample.MydownloadActivity;
import com.tgf.study.studyservice.service.MyServiceActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView txt;
    private ProgressBar progress_bar;
    private int count = 1;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    txt.setText(String.valueOf(msg.obj));
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt = (TextView) findViewById(R.id.txt);
        progress_bar = (ProgressBar) findViewById(R.id.progress_bar);
        Button btn_1 = (Button) findViewById(R.id.btn_1);
        btn_1.setOnClickListener(this);
        Button btn_2 = (Button) findViewById(R.id.btn_2);
        btn_2.setOnClickListener(this);
        Button btn_3 = (Button) findViewById(R.id.btn_3);
        btn_3.setOnClickListener(this);
        Button btn_4 = (Button) findViewById(R.id.btn_4);
        btn_4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_1:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
//                        Message msg = handler.obtainMessage(1,"hello");
//                        handler.sendMessage(msg);
                        handler.obtainMessage(1,"hello tgf").sendToTarget();
                    }
                }).start();
                break;
            case R.id.btn_2:
                progress_bar.setProgress(1);
                new DownloadAsync().execute();
                break;
            case R.id.btn_3:
                startActivity(new Intent(this, MyServiceActivity.class));
                break;
            case R.id.btn_4:
                startActivity(new Intent(this, MydownloadActivity.class));
                break;
        }
    }

    class DownloadAsync extends AsyncTask<Void,Integer,Boolean>{
        @Override
        protected void onPreExecute() {
//            super.onPreExecute();
            progress_bar.setProgress(count);
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            while (count != 100){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(count);
                count++;
            }
            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
//            super.onProgressUpdate(values);
            progress_bar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
//            super.onPostExecute(aBoolean);
            count = 1;
        }
    }
}
