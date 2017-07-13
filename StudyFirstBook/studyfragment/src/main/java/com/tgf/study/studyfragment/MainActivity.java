package com.tgf.study.studyfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.tgf.study.studyfragment.NewsExample.NewsListActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FrameLayout right_layout = (FrameLayout)findViewById(R.id.right_layout);
        Button btn = (Button)findViewById(R.id.btn);
        btn.setOnClickListener(this);
        Button button_1 = (Button)findViewById(R.id.button_1);
        button_1.setOnClickListener(this);

        replaceFragment(new RightFragment());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn:
                replaceFragment(new AnotherRightFragment());
                break;
            case R.id.button_1:
                startActivity(new Intent(MainActivity.this, NewsListActivity.class));
                break;
        }
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.replace(R.id.right_layout,fragment);
        ft.addToBackStack(null);
        ft.commit();
    }
}
