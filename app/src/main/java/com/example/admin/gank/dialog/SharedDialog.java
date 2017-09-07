package com.example.admin.gank.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.admin.gank.R;
import com.example.admin.gank.adapter.SimpleBaseAdapter;

import java.util.ArrayList;
import java.util.List;

import me.shaohui.shareutil.ShareUtil;
import me.shaohui.shareutil.share.SharePlatform;

/**
 * Created by admin on 2017/9/7.
 */

public class SharedDialog extends Dialog implements AdapterView.OnItemClickListener {

    List<Integer> list=new ArrayList<>();
    SparseArray<Integer> map=new SparseArray<>();
    {
        list.add(R.mipmap.wechat);
        list.add(R.mipmap.pengyouquan);
        list.add(R.mipmap.qq);
        list.add(R.mipmap.weibo);
        map.put(R.mipmap.wechat, SharePlatform.WX);
        map.put(R.mipmap.pengyouquan, SharePlatform.WX_TIMELINE);
        map.put(R.mipmap.qq, SharePlatform.QQ);
        map.put(R.mipmap.weibo, SharePlatform.WEIBO);

    }
    private OnSharedItemClickListener clickListener;
    public SharedDialog(@NonNull final Context context,OnSharedItemClickListener listener) {
        super(context);
        this.clickListener=listener;
        Window window = getWindow();
        window.requestFeature(Window.FEATURE_NO_TITLE);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_share, null);
        setContentView(view);
        GridView gv= (GridView) view.findViewById(R.id.gv);
        SharedAdapter sharedAdapter = new SharedAdapter(context);
        gv.setAdapter(sharedAdapter);
        sharedAdapter.update(list);
        window.setLayout(-1,-2);
        window.setBackgroundDrawable(new BitmapDrawable());
        window.setGravity(Gravity.BOTTOM);
        gv.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        dismiss();
    if(clickListener!=null){
        clickListener.onShareItemClick(map.get((Integer) parent.getItemAtPosition(position)));
    }

    }

    public interface OnSharedItemClickListener{
        void onShareItemClick(Integer platfrom);
    }

    class SharedAdapter extends SimpleBaseAdapter<Integer>{

        public SharedAdapter(Context context) {
            super(context);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView iv=new ImageView(getContext());
            iv.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            int height = getContext().getResources().getDisplayMetrics().widthPixels / 3;
            AbsListView.LayoutParams params=new AbsListView.LayoutParams(-1,height);
            iv.setLayoutParams(params);
            iv.setImageResource(getItem(position));
            return iv;
        }
    }
}
