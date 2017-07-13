package com.tgf.studyview.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tgf.studyview.R;

/**
 * Created by 涂高峰 on 2017/5/12.
 */
public class TitleView extends FrameLayout {
    private static final String TAG = "TitleView";
    private TextView title_txt;
    private Button left_btn;

    public TitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.title,this); //此处若不加this则无法展示,可参考郭林自定义view第一篇

        title_txt = (TextView) findViewById(R.id.title_txt);

        TypedArray ta = context.obtainStyledAttributes(attrs,R.styleable.TitleView);
        String a = ta.getString(R.styleable.TitleView_title);
        Log.i(TAG, "TitleView: "+a);
        title_txt.setText(a);
        ta.recycle();


        left_btn = (Button) findViewById(R.id.left_btn);
        left_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"默认点击事件",Toast.LENGTH_SHORT).show();
                ((Activity)getContext()).finish();
            }
        });
    }

    public void setTitleText(String text){
        title_txt.setText(text);
    }

    public void setLeftBtnOnclickListener(OnClickListener listener){
        left_btn.setOnClickListener(listener);
    }

}
