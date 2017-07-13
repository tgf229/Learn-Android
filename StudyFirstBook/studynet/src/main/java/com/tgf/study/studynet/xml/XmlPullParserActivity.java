package com.tgf.study.studynet.xml;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.tgf.study.studynet.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class XmlPullParserActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "XmlPullParserActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml_pull_parser);
        Button btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn:
                sendRequestWithOkHttp();
                break;
        }
    }

    private void sendRequestWithOkHttp(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient clinet = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("http://192.168.1.101/test.xml")
                        .build();
                try {
                    Response response = clinet.newCall(request).execute();
                    parseXmlWithPull(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void parseXmlWithPull(String str){
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = factory.newPullParser();
            xmlPullParser.setInput(new StringReader(str));
            int type = xmlPullParser.getEventType();

            String id = "";
            String name = "";
            String version = "";

            while(type != XmlPullParser.END_DOCUMENT){
                switch (type){
                    case XmlPullParser.START_TAG:
                        if ("id".equals(xmlPullParser.getName())){
                            id = xmlPullParser.nextText();
                        }else if("name".equals(xmlPullParser.getName())){
                            name = xmlPullParser.nextText();
                        }else if ("version".equals(xmlPullParser.getName())){
                            version = xmlPullParser.nextText();
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if ("app".equals(xmlPullParser.getName())){
                            Log.i(TAG, "parseXmlWithPull: id= "+id);
                            Log.i(TAG, "parseXmlWithPull: name= "+name);
                            Log.i(TAG, "parseXmlWithPull: version= "+version);
                        }
                        break;
                }
                type = xmlPullParser.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
