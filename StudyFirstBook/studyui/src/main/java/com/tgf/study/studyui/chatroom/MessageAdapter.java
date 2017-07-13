package com.tgf.study.studyui.chatroom;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tgf.study.studyui.R;

import java.util.List;

/**
 * Created by tugaofeng on 17/3/17.
 */
public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {
    private static final String TAG = "MessageAdapter";
    private List<MessageBean> mList;

    //构造函数
    public MessageAdapter(List<MessageBean> mList) {
        this.mList = mList;
    }

    //静态内部类 ViewHolder
    static class MessageViewHolder extends RecyclerView.ViewHolder{
        private LinearLayout left_layout;
        private LinearLayout right_layout;
        private TextView left_text;
        private TextView right_text;
        public MessageViewHolder(View itemView) {
            super(itemView);
            left_layout = (LinearLayout)itemView.findViewById(R.id.left_layout);
            right_layout = (LinearLayout)itemView.findViewById(R.id.right_layout);
            left_text = (TextView)itemView.findViewById(R.id.left_text);
            right_text = (TextView)itemView.findViewById(R.id.right_text);
        }
    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_message_item,parent,false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MessageViewHolder holder, int position) {
        MessageBean bean = mList.get(position);
        switch (bean.getType()){
            case MessageBean.TYPE_RECEIVED:
                holder.left_layout.setVisibility(View.VISIBLE);
                holder.right_layout.setVisibility(View.GONE);
                holder.left_text.setText(bean.getContent());
                break;
            case MessageBean.TYPE_SEND:
                holder.left_layout.setVisibility(View.GONE);
                holder.right_layout.setVisibility(View.VISIBLE);
                holder.right_text.setText(bean.getContent());
                break;
        }
    }

    @Override
    public int getItemCount() {
        return this.mList.size();
    }

}
