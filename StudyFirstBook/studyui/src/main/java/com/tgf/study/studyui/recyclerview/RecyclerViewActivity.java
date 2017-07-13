package com.tgf.study.studyui.recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.tgf.study.studyui.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        CountryBean c1 = new CountryBean(R.drawable.zz_it,"沃斯卡亚工业区");
        CountryBean c2 = new CountryBean(R.drawable.zz_it,"漓江塔");
        CountryBean c3 = new CountryBean(R.drawable.zz_it,"好莱坞");
        CountryBean c4 = new CountryBean(R.drawable.zz_it,"努巴尼");
        CountryBean c5 = new CountryBean(R.drawable.zz_it,"多拉多");
        CountryBean c6 = new CountryBean(R.drawable.zz_it,"尼泊尔");
        CountryBean c7 = new CountryBean(R.drawable.zz_it,"绿洲城");

        List<CountryBean> listData = new ArrayList<CountryBean>();
        listData.add(c1);
        listData.add(c2);
        listData.add(c3);
        listData.add(c4);
        listData.add(c5);
        listData.add(c6);
        listData.add(c7);

        RecyclerView recycler_view = (RecyclerView)findViewById(R.id.recycler_view);
        //=====================================线性布局===========================================
        LinearLayoutManager manager = new LinearLayoutManager(this);
//        manager.setOrientation(LinearLayoutManager.HORIZONTAL); //横向展示,还需要改下xml的orientation就行了

        //=====================================瀑布流布局===========================================
//        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);

        //=====================================网格布局===========================================
        //参数分别为 context, 几行||几列 , 方向, 是否reverse反向展示
//        GridLayoutManager manager = new GridLayoutManager(this,3,GridLayoutManager.HORIZONTAL,false);
        recycler_view.setLayoutManager(manager);

        RecyclerAdapter adapter = new RecyclerAdapter(listData);
        recycler_view.setAdapter(adapter);

    }

}
