package com.tgf.study.studysave.sql;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.tgf.study.studysave.R;

import java.util.ArrayList;
import java.util.List;

public class SqliteActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "SqliteActivity";
    private DBHelper dbHelper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        dbHelper = new DBHelper(this);

        Button btn_1 = (Button)findViewById(R.id.btn_1);
        Button btn_2 = (Button)findViewById(R.id.btn_2);
        Button btn_3 = (Button)findViewById(R.id.btn_3);
        Button btn_4 = (Button)findViewById(R.id.btn_4);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_1:
                query();
                break;
            case R.id.btn_2:
                insert();
                break;
            case R.id.btn_3:
                update();
                break;
            case R.id.btn_4:
                delete();
                break;
        }
    }

    private void insert(){
        db = dbHelper.getWritableDatabase();
        db.beginTransaction();
        ContentValues values = new ContentValues();
        values.put("name","第一行代码");
        values.put("pages","567");
        values.put("price","68.8");
        db.insert(DBHelper.TABLE_BOOK,null,values);
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
    }

    private List<Book> query(){
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(DBHelper.TABLE_BOOK,null,null,null,null,null,null);
        List<Book> list = new ArrayList<Book>();
        while (cursor.moveToNext()){
            Book book = new Book();
            book.setName(cursor.getString(cursor.getColumnIndex("name")));
            book.setPages(cursor.getString(cursor.getColumnIndex("pages")));
            book.setPrice(cursor.getString(cursor.getColumnIndex("price")));
            list.add(book);
            Log.i(TAG, "id="+ cursor.getInt(cursor.getColumnIndex("id")) +" | name= "+book.getName()+" | pages= "+book.getPages()+" | price= "+book.getPrice());
        }
        cursor.close();
        db.close();
        return list;
    }

    private void update(){
        db = dbHelper.getWritableDatabase();
        db.beginTransaction();
        ContentValues values = new ContentValues();
        values.put("pages","456");
        db.update(DBHelper.TABLE_BOOK,values,"id=?",new String[]{"4"});
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
    }

    private void delete(){
        db = dbHelper.getWritableDatabase();
        db.beginTransaction();
        db.delete(DBHelper.TABLE_BOOK,"id=?",new String[]{"4"});
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
    }
}
