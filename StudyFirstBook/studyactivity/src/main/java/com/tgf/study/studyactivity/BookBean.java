package com.tgf.study.studyactivity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by tugaofeng on 17/3/29.
 */
public class BookBean implements Parcelable{

    private String name;
    private int pages;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(pages);
    }

    public static final Parcelable.Creator<BookBean> CREATOR = new Parcelable.Creator<BookBean>(){

        @Override
        public BookBean createFromParcel(Parcel source) {
            BookBean b = new BookBean();
            b.setName(source.readString());
            b.setPages(source.readInt());
            return b;
        }

        @Override
        public BookBean[] newArray(int size) {
            return new BookBean[size];
        }
    };
}
