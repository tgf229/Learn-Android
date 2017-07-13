package com.tgf.study.studyui.listview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.tgf.study.studyui.R;
import com.tgf.study.studyui.listview.bean.Country;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        Country c1 = new Country();
        c1.setImg(R.drawable.zz_it);
        c1.setName("中国");
        Country c2 = new Country();
        c2.setImg(R.drawable.zz_it);
        c2.setName("韩国");
        Country c3 = new Country();
        c3.setImg(R.drawable.zz_it);
        c3.setName("日本");
        Country c4 = new Country();
        c4.setImg(R.drawable.zz_it);
        c4.setName("努巴尼");

        //final表示这个listData不会被重新赋值,但是其内部元素可以操作的,所以可以add
        final List<Country> listData = new ArrayList<Country>();
        listData.add(c1);
        listData.add(c2);
        listData.add(c3);
        listData.add(c4);

        ListAdapter adapter = new ListAdapter(this,R.layout.activity_list_item,listData);

        ListView list_view = (ListView)findViewById(R.id.list_view);
        list_view.setAdapter(adapter);

        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Country c = listData.get(position);
                Toast.makeText(ListViewActivity.this,c.getName(),Toast.LENGTH_SHORT).show();
            }
        });

    }
}
