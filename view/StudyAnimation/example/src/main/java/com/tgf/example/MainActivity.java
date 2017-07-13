package com.tgf.example;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView img, img1, img2, img3, img4, img5;
    private boolean mFlag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img = (ImageView) findViewById(R.id.img);
        img1 = (ImageView) findViewById(R.id.img1);
        img2 = (ImageView) findViewById(R.id.img2);
        img3 = (ImageView) findViewById(R.id.img3);
        img4 = (ImageView) findViewById(R.id.img4);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mFlag) {
                    startAnim();
                } else {
                    closeAnim();
                }
            }
        });
    }

    private void startAnim() {
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(img1, "translationX", 200f);
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(img2, "translationY", 200f);
        ObjectAnimator objectAnimator3 = ObjectAnimator.ofFloat(img3, "translationX", -200f);
        ObjectAnimator objectAnimator4 = ObjectAnimator.ofFloat(img4, "translationY", -200f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(objectAnimator1, objectAnimator2, objectAnimator3, objectAnimator4);
        animatorSet.setDuration(1000);
        animatorSet.setInterpolator(new BounceInterpolator());
        animatorSet.start();
        mFlag = false;
    }

    private void closeAnim() {
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(img1, "translationX", 0);
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(img2, "translationY", 0);
        ObjectAnimator objectAnimator3 = ObjectAnimator.ofFloat(img3, "translationX", 0);
        ObjectAnimator objectAnimator4 = ObjectAnimator.ofFloat(img4, "translationY", 0);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(objectAnimator1, objectAnimator2, objectAnimator3, objectAnimator4);
        animatorSet.setDuration(1000);
        animatorSet.setInterpolator(new BounceInterpolator());
        animatorSet.start();
        mFlag = true;
    }
}
