package com.tgf.study.studyfragment.NewsExample;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Random;

/**
 * Created by tugaofeng on 17/3/20.
 */
public class NewsBean implements Parcelable{
    private String title;
    private String content;

    public NewsBean(String title, String content) {
        this.content = getRandomLengthContent(content);
        this.title = title;
    }

    protected NewsBean(Parcel in) {
        title = in.readString();
        content = in.readString();
    }

    public static final Creator<NewsBean> CREATOR = new Creator<NewsBean>() {
        @Override
        public NewsBean createFromParcel(Parcel in) {
            return new NewsBean(in);
        }

        @Override
        public NewsBean[] newArray(int size) {
            return new NewsBean[size];
        }
    };

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = getRandomLengthContent(content);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String getRandomLengthContent(String str){
        String a = str.toString();
        Random random = new Random();
        int length = random.nextInt(20)+1;
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<length; i++){
            sb.append(str);
        }
        return sb.toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(content);
    }
}
