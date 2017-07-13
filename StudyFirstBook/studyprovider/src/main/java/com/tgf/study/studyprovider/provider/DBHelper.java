package com.tgf.study.studyprovider.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by tugaofeng on 17/3/23.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "tgf";
    private static final int DB_VERSION = 1;

    private static final String TABLE_BOOK = "book";
    private static final String TABLE_BOOK_CREATE = "create table book(id integer primary key autoincrement,name text,pages text)";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_BOOK_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
