package com.example.admin.gank.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.admin.gank.R;
import com.example.admin.gank.entity.SearchBean;

/**
 * Created by admin on 2017/9/7.
 */

public class SearchAdapter extends SimpleBaseAdapter<SearchBean> {
    public SearchAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseHolder holder=null;
        if(convertView==null){
            holder=new BaseHolder(inflater, R.layout.item_search);
        }else {
            holder= (BaseHolder) convertView.getTag();
        }
        SearchBean item = getItem(position);
        holder.setText(R.id.descTv,item.getDesc());
//        holder.setWebUrl(R.id.readWeb,item.getReadability());
        holder.setText(R.id.timeTv,item.getPublishedAt());
        holder.setText(R.id.authorTv,item.getWho());
        return holder.getConvertView();
    }
//    class  ImageHandler implements Html.ImageGetter {
//
//        @Override
//        public Drawable getDrawable(String source) {
//            final MyDrawable myDrawable=new MyDrawable();
//            Glide.with(getContext()).asBitmap().load(source).into(new SimpleTarget<Bitmap>() {
//                @Override
//                public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
//
//                    myDrawable.setBitmap(resource);
//                    myDrawable.invalidateSelf();// 重新显示图片
//                }
//            });
//            return myDrawable;
//        }
//    }
//    class MyDrawable extends BitmapDrawable{
//       private Bitmap bitmap;
//
//        public void setBitmap(Bitmap bitmap) {
//            this.bitmap = bitmap;
//        }
//
//        @Override
//        public void draw(Canvas canvas) {
//            super.draw(canvas);
//            if(bitmap!=null){
//                canvas.drawBitmap(bitmap,0,0,getPaint());
//            }
//        }
//    }
}
