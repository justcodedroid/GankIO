package com.example.admin.gank.adapter;

import android.content.Context;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.admin.gank.R;
import com.example.admin.gank.entity.CategoryBean;
import com.example.admin.gank.http.HttpModel;

import java.util.List;

/**
 * Created by admin on 2017/9/6.
 */

public class CategoryAdapter extends SimpleBaseAdapter<CategoryBean> {
    public CategoryAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseHolder holder=null;
        if(convertView==null){
            holder=new BaseHolder(inflater, R.layout.item_category);
        }else {
            holder= (BaseHolder) convertView.getTag();
        }
        CategoryBean item = getItem(position);
        holder.setText(R.id.titleTv,item.getDesc());
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

    private String noImage(int position) {
        CategoryBean item = getItem(position);
        boolean equals = item.getType().equals(HttpModel.CATEGORT_FU_LI);
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
