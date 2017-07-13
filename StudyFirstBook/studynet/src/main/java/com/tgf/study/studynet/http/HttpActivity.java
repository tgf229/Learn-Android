package com.tgf.study.studynet.http;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tgf.study.studynet.R;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);

        Button btn_1 = (Button) findViewById(R.id.btn_1);
        btn_1.setOnClickListener(this);
        Button btn_2 = (Button) findViewById(R.id.btn_2);
        btn_2.setOnClickListener(this);
        txt = (TextView) findViewById(R.id.txt);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_1:
                send();
                break;
            case R.id.btn_2:
                sendOKHTTP();
                break;
        }
    }

    private void sendOKHTTP(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();

                    RequestBody body = new FormBody.Builder()
                            .add("page","1")
                            .add("num","10")
                            .build();

                    Request request = new Request.Builder()
                            .url("https://app.jseea.cn/Bus100301")
                            .post(body)
                            .build();
                    Response response = client.newCall(request).execute();

                    show(response.body().string());

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void send(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                BufferedReader reader = null;
                HttpURLConnection connection = null;
                try {
                    URL url = new URL("https://app.jseea.cn/Bus100301");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setReadTimeout(10000);
                    connection.setConnectTimeout(10000);

                    // 如果有参数 加这段
                    DataOutputStream out = new DataOutputStream(connection.getOutputStream());
                    out.writeBytes("page=1&num=10");

                    InputStream in = connection.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(in));
                    String line = "";
                    StringBuilder sb = new StringBuilder();
                    while ((line = reader.readLine()) != null){
                        sb.append(line);
                    }
                    show(sb.toString());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if (reader != null){
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (connection != null){
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }

    private void show(final String str){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                txt.setText(str);
            }
        });
    }
}
