package com.tgf.study.studyui.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tgf.study.studyui.R;

import java.util.List;

/**
 * Created by tugaofeng on 17/3/17.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{
    private static final String TAG = "RecyclerAdapter";
    private List<CountryBean> mList;

    public RecyclerAdapter(List<CountryBean> mList) {
        this.mList = mList;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        Log.i(TAG, "onCreateViewHolder: "+parent.getContext());
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_recycler_item,parent,false);
        ViewHolder vh = new ViewHolder(view);
        vh.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick: "+v);
                Toast.makeText(v.getContext(),"view被点击了",Toast.LENGTH_SHORT).show();
            }
        });
        vh.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick: "+v);
                Toast.makeText(v.getContext(),"imageView被点击了",Toast.LENGTH_SHORT).show();
            }
        });
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.i(TAG, "onBindViewHolder: ");
        CountryBean bean = mList.get(position);
        holder.img.setImageResource(bean.getImg());
        holder.name.setText(bean.getName());
    }

    @Override
    public int getItemCount() {
        return this.mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private View view;
        private ImageView img;
        private TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            Log.i(TAG, "ViewHolder: ");
            view = itemView;
            img = (ImageView)itemView.findViewById(R.id.img);
            name = (TextView)itemView.findViewById(R.id.name);
        }
    }
}
