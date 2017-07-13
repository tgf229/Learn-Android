package com.tgf.layoutanimation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout content;
    private int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(this);
        Button btn2 = (Button) findViewById(R.id.btn2);
        btn2.setOnClickListener(this);
        content = (LinearLayout) findViewById(R.id.content);

        AlphaAnimation aa=new AlphaAnimation(0, 1);
        aa.setDuration(1000);
        ScaleAnimation sa = new ScaleAnimation(0,1,0,1, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        sa.setDuration(1000);

        AnimationSet as = new AnimationSet(true);
        as.addAnimation(aa);
        as.addAnimation(sa);

        LayoutAnimationController lac=new LayoutAnimationController(as);
        //ORDER_NORMAL-顺序  ORDER_RANDOM-随机, ORDER_REVERSE-反序
        lac.setOrder(LayoutAnimationController.ORDER_NORMAL);
        content.setLayoutAnimation(lac);

        //演示LayoutAnimationController自定义view过渡效果
        for (int k=0;i<5;i++){
            Button button = new Button(this);
            button.setText("Button "+i);
            content.addView(button);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //演示系统自带的 android:animateLayoutChanges="true" 过渡效果
            case R.id.btn1:
                i++;
                Button button = new Button(this);
                button.setText("Button "+i);
                content.addView(button);
                break;
            case R.id.btn2:
                if (i>0){
                    content.removeViewAt(0);
                    i--;
                }
        }
    }
}
