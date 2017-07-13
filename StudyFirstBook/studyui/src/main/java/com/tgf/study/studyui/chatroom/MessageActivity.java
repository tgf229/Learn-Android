package com.tgf.study.studyui.chatroom;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tgf.study.studyui.R;

import java.util.ArrayList;
import java.util.List;

public class MessageActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText txt;
    private List<MessageBean> mList;
    private MessageAdapter adapter;
    private LinearLayoutManager manager;
    private RecyclerView recycler_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        recycler_view = (RecyclerView) findViewById(R.id.recycler_view);
        txt = (EditText)findViewById(R.id.txt);
        Button btn = (Button)findViewById(R.id.btn);
        Button button_edit = (Button)findViewById(R.id.button_edit);
        btn.setOnClickListener(this);   //发送消息
        button_edit.setOnClickListener(this);  //模拟收到消息

        MessageBean b1 = new MessageBean("我收到1条消息",MessageBean.TYPE_RECEIVED);
        MessageBean b2 = new MessageBean("我发出1条消息",MessageBean.TYPE_SEND);
        MessageBean b3 = new MessageBean("我收到2条消息",MessageBean.TYPE_RECEIVED);
        MessageBean b4 = new MessageBean("我收到3条消息",MessageBean.TYPE_RECEIVED);
        MessageBean b5 = new MessageBean("我发出2条消息",MessageBean.TYPE_SEND);
        MessageBean b6 = new MessageBean("我收到4条消息",MessageBean.TYPE_RECEIVED);
        mList = new ArrayList<MessageBean>();
        mList.add(b1);
        mList.add(b2);
        mList.add(b3);
        mList.add(b4);
        mList.add(b5);
        mList.add(b6);

        manager = new LinearLayoutManager(this);
        recycler_view.setLayoutManager(manager);
        adapter = new MessageAdapter(mList);
        recycler_view.setAdapter(adapter);
        recycler_view.scrollToPosition(mList.size()-1); //滑动展示最后一条
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn:
                if (!"".equals(txt.getText().toString())){
                    MessageBean msg = new MessageBean(txt.getText().toString(),MessageBean.TYPE_SEND);
                    mList.add(msg); //插入数据
                    adapter.notifyItemInserted(mList.size()-1); //notify在最后插入了一条数据
                    txt.setText(""); //输入框置空
                    recycler_view.scrollToPosition(mList.size()-1); //滑动展示最后一条
                }
                break;
            case R.id.button_edit:
                MessageBean msg = new MessageBean("啦啦啦",MessageBean.TYPE_RECEIVED);
                mList.add(msg); //插入数据
                adapter.notifyItemInserted(mList.size()-1); //notify在最后插入了一条数据
                recycler_view.scrollToPosition(mList.size()-1); //滑动展示最后一条
                break;
        }
    }
}
