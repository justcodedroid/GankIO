package com.example.admin.gank.adapter;

import android.content.Context;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.gank.R;
import com.example.admin.gank.entity.CategoryBean;
import com.example.admin.gank.http.HttpModel;

/**
 * Created by admin on 2017/9/8.
 */

public class HistoryAdapter extends SimpleBaseAdapter<CategoryBean> {
    private SparseArray<Integer> map=new SparseArray<>();
    {
    map.put(0, R.layout.item0_history);
    map.put(1, R.layout.item1_history);
    map.put(2, R.layout.item2_history);
    }
    public HistoryAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseHolder holder=null;
        if(convertView==null){
            holder=new BaseHolder(inflater,map.get(getItemViewType(position)));
        }else {
            holder= (BaseHolder) convertView.getTag();
        }
        CategoryBean item = getItem(position);
        Log.e("tag","sadasdsad--->"+item.getDesc());
        holder.setText(R.id.descTv,item.getDesc());
        holder.setText(R.id.srcTv,item.getSource()+"  "+item.getWho()+"  "+item.getPublishedAt());
        String image;
        if((image=noImage(position))!=null){
            holder.setVisiable(R.id.iv);
            holder.setImage(R.id.iv,image);
        }else {
            holder.setGone(R.id.iv);
        }
        return holder.getConvertView();
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).getItemType();
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }
    private String noImage(int position) {
        CategoryBean item = getItem(position);
        String type = item.getType();
        if(type==null)return  null;
        boolean equals = type.equals(HttpModel.CATEGORT_FU_LI);
        if(equals){
            return  item.getUrl();
        }else {
            if(item.getImages()!=null&&item.getImages().size()>0){
                return  item.getImages().get(0);
            }
        }
        return null;

    }
}
