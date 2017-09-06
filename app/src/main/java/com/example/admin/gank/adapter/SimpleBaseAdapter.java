package com.example.admin.gank.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/9/6.
 */

public abstract class SimpleBaseAdapter<T> extends BaseAdapter {
    private Context context;
    protected LayoutInflater inflater;
    private List<T> list=new ArrayList<>();

    public SimpleBaseAdapter(Context context) {
        this.context = context;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public T getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public abstract View getView(int position, View convertView, ViewGroup parent) ;

    public Context getContext() {
        return context;
    }
    public void update(List<T> datas){
        if(datas==null){
            list.clear();
        }else {
            list.addAll(datas);
        }
        notifyDataSetChanged();
    }
}
