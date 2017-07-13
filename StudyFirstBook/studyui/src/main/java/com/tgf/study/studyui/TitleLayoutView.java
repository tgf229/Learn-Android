package com.tgf.study.studyui;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by tugaofeng on 17/3/16.
 */
public class TitleLayoutView extends LinearLayout implements View.OnClickListener{
//自定义控件
    private static final String TAG = "TitleLayoutView";
    public TitleLayoutView(Context context, AttributeSet attrs) {
        super(context,attrs);
        Log.i(TAG, "TitleLayoutView: "+getContext());
        LayoutInflater.from(context).inflate(R.layout.title_layout,this);
        Button button_back = (Button)findViewById(R.id.button_back);
//        Button button_edit = (Button)findViewById(R.id.button_edit);
        button_back.setOnClickListener(this);
//        button_edit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_back:
                ((Activity)getContext()).finish();
                break;
//            case R.id.button_edit:
//                Toast.makeText(getContext(),"点击编辑",Toast.LENGTH_SHORT).show();
//                break;
        }
    }
}
