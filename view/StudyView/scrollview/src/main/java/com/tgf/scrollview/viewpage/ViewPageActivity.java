package com.tgf.scrollview.viewpage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.tgf.scrollview.R;

public class ViewPageActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_page);
        Button btn1 = (Button) findViewById(R.id.btn1);
        TextView btn2 = (TextView) findViewById(R.id.btn2);
        Button btn3 = (Button) findViewById(R.id.btn3);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn1:
                Toast.makeText(this,"1",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn2:
                Toast.makeText(this,"2",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn3:
                Toast.makeText(this,"3",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
