package com.tgf.study.studynet.json;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tgf.study.studynet.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class JsonActivity extends AppCompatActivity {
    private static final String TAG = "JsonActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);

        sendRequestWithOkHttp();

    }

    private void sendRequestWithOkHttp(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("http://192.168.1.101/test.json")
                        .build();
                try {
                    Response response = client.newCall(request).execute();
//                    parseWithJson(response.body().string());
                    parseWithGson(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void parseWithJson(String str){
        try {
            JSONArray arr = new JSONArray(str);
            for (int i=0; i<arr.length(); i++){
                JSONObject obj = arr.getJSONObject(i);
                Log.i(TAG, "parseWithJson: name = "+obj.getString("name"));
                Log.i(TAG, "parseWithJson: age = "+obj.getString("age"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void parseWithGson(String str){
        Gson gson = new Gson();
        List<PersonBean> list = gson.fromJson(str,new TypeToken<List<PersonBean>>(){}.getType());
        for (PersonBean bean:list){
            Log.i(TAG, "parseWithGson: name = "+bean.getName());
            Log.i(TAG, "parseWithGson: age = "+bean.getAge());
        }
        Log.i(TAG, "parseWithGson: "+gson.toJson(list));
    }
}
