package com.tgf.viewanimation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private AnimationSet as;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView txt = (TextView) findViewById(R.id.txt);

        //透明度动画
        AlphaAnimation aa = new AlphaAnimation(0, 1);
        aa.setDuration(2000);

        //旋转动画
        //fromDegrees和toDegrees：这两个分别是旋转的起始角度和结束角度
        //pivotX和pivotY：是旋转的中心点的X,Y坐标
        //pivotXType和pivotYType：X,Y轴的伸缩模式，定义了pivotXValue和pivotYValue怎么被使用
        //pivotXValue和pivotYValue：在X,Y方向的位置，但是会受pivotXType和pivotYType的影响
        RotateAnimation ra = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5F, Animation.RELATIVE_TO_SELF, 0.5F);
        ra.setDuration(2000);

        //位移动画
        TranslateAnimation ta = new TranslateAnimation(0, 0, 0, 200);
        ta.setDuration(2000);

        //缩放动画
        ScaleAnimation sa = new ScaleAnimation(0,1,0,1,Animation.RELATIVE_TO_SELF,0.5F,Animation.RELATIVE_TO_SELF,0.5f);
        sa.setDuration(2000);

        //动画集合
        AnimationSet as = new AnimationSet(true);
        as.addAnimation(aa);
        as.addAnimation(ra);
        as.addAnimation(ta);
        as.addAnimation(sa);
        txt.startAnimation(as);

        as.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.i(TAG, "onAnimationStart: ");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.i(TAG, "onAnimationEnd: ");
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                Log.i(TAG, "onAnimationRepeat: ");
            }
        });
    }
}
