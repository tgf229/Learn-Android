package com.tgf.objectanimation;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    private Button btn,btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.btn);
        btn2 = (Button) findViewById(R.id.btn2);
        btn.setOnClickListener(this);
        btn2.setOnClickListener(this);

        //属性动画
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(btn, "rotation", 180);
//        btn.setPivotX(300); //控制着view的支点位置,围绕着该支点做rotation和scale
//        btn.setPivotY(100); //控制着view的支点位置,围绕着该支点做rotation和scale
        objectAnimator.setDuration(1000);
        objectAnimator.start();
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Log.i(TAG, "onAnimationUpdate: " + animation.getAnimatedValue());
                //TODO
            }
        });

        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                //动画开始时调用
                Log.i(TAG, "onAnimationStart: ");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                //动画结束时调用
                Log.i(TAG, "onAnimationEnd: ");
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                //动画被取消时调用
                Log.i(TAG, "onAnimationCancel: ");
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                //动画循环播放时调用
                Log.i(TAG, "onAnimationRepeat: ");
            }
        });

        Animator animator = AnimatorInflater.loadAnimator(this,R.anim.object_animator);
        animator.setTarget(btn2);
        animator.setInterpolator(new BounceInterpolator());
        animator.start();

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                ObjectAnimator oa1 = ObjectAnimator.ofFloat(btn,"translationY",100);
                ObjectAnimator oa2 = ObjectAnimator.ofFloat(btn,"rotationY",180);
                ObjectAnimator oa3 = ObjectAnimator.ofFloat(btn,"scaleX",1.5F);
                ObjectAnimator oa4 = ObjectAnimator.ofFloat(btn,"alpha",0.5F);

                // 注意与视图动画的 AnimationSet 的区别
                AnimatorSet as = new AnimatorSet();
                as.setDuration(1000);
                as.play(oa1).with(oa2).with(oa3).with(oa4); //一起执行 相当于as.playTogether(oa1,oa2);
//                as.play(oa1).after(oa2); //顺序执行  as.play(oa1).before(oa2); 相当于as.playSequentially(oa1,oa2);
                as.start();


                //另一种写法 ========
                // PropertyValuesHolder 就相当于视图动画的AnimationSet
//                PropertyValuesHolder pvh1 = PropertyValuesHolder.ofFloat("translationY", 100);
//                PropertyValuesHolder pvh2 = PropertyValuesHolder.ofFloat("rotationY", 180); //3D Y轴旋转180
//                PropertyValuesHolder pvh3 = PropertyValuesHolder.ofFloat("scaleX", 1.5F); //放大1.5
//                ObjectAnimator.ofPropertyValuesHolder(btn, pvh1, pvh2, pvh3).setDuration(2000).start();
                break;

            case R.id.btn2:
                btn2.animate()
                        .alpha(0.5f)
                        .x(300)
                        .translationZ(100f)
                        .setDuration(1000)
                        .withStartAction(new Runnable() {
                            @Override
                            public void run() {
                            }
                        })
                        .withEndAction(new Runnable() {
                            @Override
                            public void run() {
                            }
                        })
                        .start();
                break;
        }
    }
}
