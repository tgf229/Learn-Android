package com.tgf.study.studyprovider;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.tgf.study.studyprovider.contactsExample.ContactsActivity;
import com.tgf.study.studyprovider.permission.PermissionActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(this);
        Button btn_1 = (Button) findViewById(R.id.btn_1);
        btn_1.setOnClickListener(this);
        Button btn_2 = (Button) findViewById(R.id.btn_2);
        btn_2.setOnClickListener(this);
        Button btn_3 = (Button) findViewById(R.id.btn_3);
        btn_3.setOnClickListener(this);
        Button btn_4 = (Button) findViewById(R.id.btn_4);
        btn_4.setOnClickListener(this);
        Button btn_5 = (Button) findViewById(R.id.btn_5);
        btn_5.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn:
                startActivity(new Intent(MainActivity.this, PermissionActivity.class));
                break;
            case R.id.btn_1:
                startActivity(new Intent(MainActivity.this, ContactsActivity.class));
                break;
            case R.id.btn_2:
                query();
                break;
            case R.id.btn_3:
                insert();
                break;
            case R.id.btn_4:
                update();
                break;
            case R.id.btn_5:
                delete();
                break;
        }
    }

    private void insert(){
        Uri uri = Uri.parse("content://com.tgf.study.studyprovider/book");
        ContentValues values = new ContentValues();
        values.put("name","第一行代码");
        values.put("pages","555");
        Uri newUri = getContentResolver().insert(uri,values);
        String newId = newUri.getPathSegments().get(1);
        Log.i(TAG, "insert: newId= "+newId);
    }

    private void query(){
        //BOOK_DIR
        Uri uri = Uri.parse("content://com.tgf.study.studyprovider/book");
        Cursor cursor = getContentResolver().query(uri,null,null,null,null,null);
//        Cursor cursor = getContentResolver().query(uri,new String[]{"id","name","pages"},"id=?",new String[]{"1"},null,null);

        //BOOK_ITEM
//        Uri uri = Uri.parse("content://com.tgf.study.studyprovider/book/1");
//        Cursor cursor = getContentResolver().query(uri,null,null,null,null,null);

        if (cursor != null){
            while (cursor.moveToNext()){
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String pages = cursor.getString(cursor.getColumnIndex("pages"));
                Log.i(TAG, "id= "+id);
                Log.i(TAG, "name= "+name);
                Log.i(TAG, "pages= "+pages);
            }
            cursor.close();
        }
    }

    private void update(){
        Uri uri = Uri.parse("content://com.tgf.study.studyprovider/book");
        ContentValues values = new ContentValues();
        values.put("name","第二行代码");
        values.put("pages","666");
        int rowNum = getContentResolver().update(uri,values,"id=?",new String[]{"2"});
        Log.i(TAG, "update: 修改行数为= "+rowNum);

    }
    private void delete(){
        Uri uri = Uri.parse("content://com.tgf.study.studyprovider/book/3");
        int rowNum = getContentResolver().delete(uri,null,null);
        Log.i(TAG, "delete:删除行数为= "+rowNum);
    }
}
