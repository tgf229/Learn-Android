package com.tgf.study.studyui.listview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tgf.study.studyui.R;
import com.tgf.study.studyui.listview.bean.Country;

import java.util.List;

/**
 * Created by tugaofeng on 17/3/16.
 */
public class ListAdapter extends ArrayAdapter {
    private static final String TAG = "ListAdapter";
    private int resourceId;
    public ListAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Country c = (Country) getItem(position);
        ViewHolder mHolder;
        if (convertView == null){
            mHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            mHolder.img = (ImageView)convertView.findViewById(R.id.img);
            mHolder.name = (TextView)convertView.findViewById(R.id.name);
            convertView.setTag(mHolder);
        }else{
            mHolder = (ViewHolder) convertView.getTag();
            Log.i(TAG, "getView: 复用了");
        }

        mHolder.img.setImageResource(c.getImg());
        mHolder.name.setText(c.getName());

        return convertView;
    }

    class ViewHolder{
        ImageView img;
        TextView name;
    }
}
