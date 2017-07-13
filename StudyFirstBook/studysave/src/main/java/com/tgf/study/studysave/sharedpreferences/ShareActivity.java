package com.tgf.study.studysave.sharedpreferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tgf.study.studysave.R;

public class ShareActivity extends AppCompatActivity implements View.OnClickListener {
private TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        Button btn_1 = (Button)findViewById(R.id.btn_1);
        Button btn_2 = (Button)findViewById(R.id.btn_2);
        txt = (TextView)findViewById(R.id.txt);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_1:
                SharedPreferences.Editor edit = getSharedPreferences("tgf", MODE_PRIVATE).edit();
                edit.putString("姓名","涂高峰");
                edit.putInt("年龄",30);
                edit.apply();  //需要了解和commit的异同\
                break;
            case R.id.btn_2:
                SharedPreferences ref = getSharedPreferences("tgf",MODE_PRIVATE);
                StringBuilder sb = new StringBuilder();
                String name = ref.getString("姓名","?");
                int age = ref.getInt("年龄",0);
                String love = ref.getString("哈哈","??");
                txt.setText((sb.append(name).append(age).append(love)).toString());
                break;
        }
    }
}
