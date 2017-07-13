package com.tgf.study.studyprovider.contactsExample;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tgf.study.studyprovider.R;

import java.util.List;

/**
 * Created by tugaofeng on 17/3/22.
 */
public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder> {

    private List<ContactsBean> mList;

    public ContactsAdapter(List<ContactsBean> mList) {
        this.mList = mList;
    }

    @Override
    public ContactsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_contacts_item,parent,false);
        return new ContactsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactsViewHolder holder, int position) {
        holder.name.setText(mList.get(position).getName());
        holder.number.setText(mList.get(position).getNumber());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class ContactsViewHolder extends RecyclerView.ViewHolder{
        private TextView name;
        private TextView number;
        public ContactsViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            number = (TextView) itemView.findViewById(R.id.number);
        }
    }
}
