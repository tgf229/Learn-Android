package com.tgf.study.studyfragment.NewsExample;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tgf.study.studyfragment.R;

import java.util.List;

/**
 * Created by tugaofeng on 17/3/20.
 */
public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.NewsViewHolder>{
    private static final String TAG = "NewsListAdapter";
    private List<NewsBean> mList;
    private boolean isTwoPan;
    private NewsListActivity mContext;

    public NewsListAdapter(List<NewsBean> mList, boolean isTwoPan,NewsListActivity context) {
        this.mList = mList;
        this.isTwoPan = isTwoPan;
        this.mContext = context;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_news_list_item,parent,false);
        final NewsViewHolder vh = new NewsViewHolder(view);

        vh.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewsBean bean = mList.get(vh.getAdapterPosition());
                if (isTwoPan){
                    NewsDetailFragment detailFragment = (NewsDetailFragment)mContext.getSupportFragmentManager().findFragmentById(R.id.news_detail_fragment);
                    detailFragment.refresh(bean);
                }else{
                    NewsDetailActivity.startActivity(parent.getContext(),bean);
                }
            }
        });
        return vh;
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        holder.title.setText(mList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class NewsViewHolder extends RecyclerView.ViewHolder{
        private TextView title;
        private View view;

        public NewsViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            title = (TextView)itemView.findViewById(R.id.title);
        }
    }
}
