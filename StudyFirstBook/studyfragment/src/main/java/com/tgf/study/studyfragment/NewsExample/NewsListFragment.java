package com.tgf.study.studyfragment.NewsExample;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.tgf.study.studyfragment.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsListFragment extends Fragment {
    private static final String TAG = "NewsListFragment";
    private boolean isTwoPan;
    private View view;

    public NewsListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view  = inflater.inflate(R.layout.fragment_news_list, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //注释这样的写法有问题,会导致切换的时候 isTwoPan不会改变!!!
//        NewsDetailFragment fragment = (NewsDetailFragment)getActivity().getSupportFragmentManager().findFragmentById(R.id.news_detail_fragment);

        FrameLayout layout = (FrameLayout)getActivity().findViewById(R.id.news_detail_layout);
        if (layout !=null){
            isTwoPan = true;
        }else{
            isTwoPan = false;
        }

        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        NewsListAdapter adapter = new NewsListAdapter(getData(),isTwoPan,(NewsListActivity)getActivity());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

    private List<NewsBean> getData(){
        NewsBean b1 = new NewsBean("标题1","内容1");
        NewsBean b2 = new NewsBean("标题2","内容2");
        NewsBean b3 = new NewsBean("标题3","内容3");
        NewsBean b4 = new NewsBean("标题4","内容4");
        NewsBean b5 = new NewsBean("标题5","内容5");
        NewsBean b6 = new NewsBean("标题6","内容6");
        List<NewsBean> listData = new ArrayList<NewsBean>();
        listData.add(b1);
        listData.add(b2);
        listData.add(b3);
        listData.add(b4);
        listData.add(b5);
        listData.add(b6);
        return listData;
    }
}
