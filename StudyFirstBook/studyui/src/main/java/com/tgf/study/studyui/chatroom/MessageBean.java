package com.tgf.study.studyui.chatroom;

/**
 * Created by tugaofeng on 17/3/17.
 */
public class MessageBean {

    public static final int TYPE_RECEIVED = 0;
    public static final int TYPE_SEND = 1;
    private int type;
    private String content;

    public MessageBean(String content, int type) {
        this.content = content;
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
