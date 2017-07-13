package com.tgf.study.studyfragment.NewsExample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tgf.study.studyfragment.R;

public class NewsDetailActivity extends AppCompatActivity {

    private static final String TAG = "NewsDetailActivity";

    public static void startActivity(Context context, NewsBean bean){
        Intent intent = new Intent(context,NewsDetailActivity.class);
        intent.putExtra("newsData",bean);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        NewsBean bean = getIntent().getParcelableExtra("newsData");
        NewsDetailFragment fragment = (NewsDetailFragment) getSupportFragmentManager().findFragmentById(R.id.news_detail_fragment);
        fragment.refresh(bean);
    }
}
