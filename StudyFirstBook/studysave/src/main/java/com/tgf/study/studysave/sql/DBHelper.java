package com.tgf.study.studysave.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by tugaofeng on 17/3/22.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME="tgf";
    private static final int DB_VERSION = 4;

    public static final String TABLE_BOOK = "book";
    private static final String TABLE_BOOK_CREATE = "create table book(id integer primary key autoincrement,name text,pages text,price text)";


    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_BOOK_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("drop table if exists book");
//        onCreate(db);

//        db.execSQL("alter table book add author text");

//        db.execSQL("alter table book rename column pages to page"); //sql不支持直接修改字段名.
    }
}
