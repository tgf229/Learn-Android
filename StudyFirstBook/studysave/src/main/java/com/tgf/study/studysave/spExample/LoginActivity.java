package com.tgf.study.studysave.spExample;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.tgf.study.studysave.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText username,password;
    private CheckBox check;
    private SharedPreferences.Editor editor;
    private SharedPreferences shareRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        shareRef = PreferenceManager.getDefaultSharedPreferences(this); //默认会创建 data/data/包名/shared_prefs/包名.xml
        shareRef = getSharedPreferences("tgf_share",MODE_PRIVATE);   //会创建 data/data/包名/shared_prefs/tgf_share.xml

        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        check = (CheckBox)findViewById(R.id.check);
        Button btn = (Button)findViewById(R.id.btn);
        btn.setOnClickListener(this);

        username.setText(shareRef.getString("username",""));
        password.setText(shareRef.getString("password",""));
        check.setChecked(shareRef.getBoolean("isCheck",false));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn:
                login();
                break;
        }
    }

    private void login(){
        if ("1".equals(username.getText().toString()) && "1".equals(password.getText().toString())){

            editor = shareRef.edit();
            if (check.isChecked()){
                editor.putBoolean("isCheck",check.isChecked());
                editor.putString("username",username.getText().toString());
                editor.putString("password",password.getText().toString());
            }else{
                editor.clear(); //清除所有
            }
            editor.apply();  //别忘了提交!!  apply()无返回 异步效率高   commit()返回布尔,同步效率低

            Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
        }
    }
}
