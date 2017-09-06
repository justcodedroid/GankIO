package com.example.admin.gank.adapter;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by admin on 2017/9/6.
 */
// ViewHolder 写一个ViewHolder即可
public class BaseHolder {
    private View convertView;
    private SparseArray<View> map=new SparseArray<>();
    public BaseHolder(LayoutInflater inflater,int layoutId){
        convertView=inflater.inflate(layoutId,null);
        convertView.setTag(this);
    }

    public View getConvertView() {
        return convertView;
    }
    private View findViewById(int id){
        View view = map.get(id);
        if(view==null){
          view=  convertView.findViewById(id);
            map.put(id,view);
        }
        return  view;
    }
    public void setImage(int id,String url){

        ImageView iv = (ImageView) findViewById(id);
        if(url==null||iv==null)return;
        Glide.with(convertView).load(url).into(iv);
    }
    public void setVisiable(int id){
        View view = findViewById(id);
        if(view!=null){
            view.setVisibility(View.VISIBLE);
        }
    }
    public void setGone(int id){
        View view = findViewById(id);
        if(view!=null){
            view.setVisibility(View.GONE);
        }
    }
    public void setText(int id,String text){
        TextView tv = (TextView) findViewById(id);
        if(tv!=null){
            tv.setText(text);
        }
    }
}
