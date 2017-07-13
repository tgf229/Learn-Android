package com.tgf.matrix.view.basic;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by 涂高峰 on 2017/6/5.
 */
public class MyLayout extends LinearLayout{
    private static final String TAG = "MyLayout";
    public MyLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(TAG, "onTouchEvent: "+event.getAction());
        return super.onTouchEvent(event);
    }

}
