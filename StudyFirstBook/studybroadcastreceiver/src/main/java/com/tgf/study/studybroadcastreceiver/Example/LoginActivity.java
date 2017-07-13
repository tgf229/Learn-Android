package com.tgf.study.studybroadcastreceiver.Example;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tgf.study.studybroadcastreceiver.R;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private EditText username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        Button btn = (Button)findViewById(R.id.btn);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn:
                if (("1".equals(username.getText().toString()) && "1".equals(password.getText().toString()))){
                    startActivity(new Intent(LoginActivity.this,FirstActivity.class));
                }
                break;
        }
    }
}
