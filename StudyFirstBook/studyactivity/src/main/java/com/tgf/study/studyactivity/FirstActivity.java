package com.tgf.study.studyactivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.tgf.study.studyactivity.lifecycle.LifeActivity;

public class FirstActivity extends BaseActivity implements View.OnClickListener{
    private static final String TAG = "FirstActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);
        Button button1 = (Button)findViewById(R.id.button_1);
        button1.setOnClickListener(this);
        Button button2 = (Button)findViewById(R.id.button_2);
        button2.setOnClickListener(this);
        Button button3 = (Button)findViewById(R.id.button_3);
        button3.setOnClickListener(this);
        Button button4 = (Button)findViewById(R.id.button_4);
        button4.setOnClickListener(this);
        Button button5 = (Button)findViewById(R.id.button_5);
        button5.setOnClickListener(this);
    }

    //singleTop和singleTask重新打开自己不会onCreate,只会进onNewIntent方法
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i(TAG, "onNewIntent: ");
    }

    //给activity创建menu,即右上角...
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.first_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    //设置menu点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_item:
                Toast.makeText(FirstActivity.this,"click add",Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_item:
                Toast.makeText(FirstActivity.this,"click remove",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_1:
                //隐式启动activity
                Intent intent = new Intent("com.tgf.study.studyactivity.ACTION_START");
                intent.addCategory("com.tgf.study.studyactivity.MY_CATEGORY"); //可以再添加自定义的category过滤

                BookBean b = new BookBean();
                b.setName("第一行代码");
                b.setPages(462);
                intent.putExtra("book_data",b);
                intent.putExtra("data","传送数据哦");
                startActivityForResult(intent,1); //requestCode唯一即可
                break;
            case R.id.button_2:
                Intent intent1 = new Intent(Intent.ACTION_VIEW);
                intent1.setData(Uri.parse("http://www.baidu.com"));
                startActivity(intent1);
                break;
            case R.id.button_3:
                Intent intent2 = new Intent(Intent.ACTION_DIAL);
                intent2.setData(Uri.parse("tel:10086"));
                startActivity(intent2);
                break;
            case R.id.button_4:
                Intent intent3 = new Intent(FirstActivity.this, LifeActivity.class);
                startActivity(intent3);
                break;
            case R.id.button_5:
                Intent intent4 = new Intent(FirstActivity.this, FirstActivity.class);
                intent4.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);//也可以在manifest中设置android:launchMode
                startActivity(intent4);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1:
                if (RESULT_OK == resultCode){
                    String str = data.getStringExtra("data_return");
                    Log.i(TAG, "onActivityResult: "+str);
                }
                break;
        }
    }
}
