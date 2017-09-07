package com.example.admin.gank.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.admin.gank.MyApplication;
import com.example.admin.gank.entity.BaseBean;
import com.example.admin.gank.entity.CategoryBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/9/7.
 */

public class SqliteUtilsHelper extends SQLiteOpenHelper {
    public static final int LIKE = 0;
    public static final int DISLIKE = 1;

    private static SqliteUtilsHelper helper;

    public static SqliteUtilsHelper getInstance() {
       synchronized (SqliteUtilsHelper.class){
           if (helper == null) {
               synchronized (SqliteUtilsHelper.class){
                   if(helper==null){
                       helper = new SqliteUtilsHelper(MyApplication.app);
                   }
               }
               }
       }
        return helper;
    }


    private SqliteUtilsHelper(Context context) {
        super(context, "collection", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table collection (sort_id integer primary key autoincrement,_id text unique,createdAt text,desc text,publishedAt text,source text,type text,url text,used integer, who text,images text,like integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void updateOrInsert(CategoryBean categoryBean) {
        // 不喜欢并没有从数据库删除，而是直接将标识设置为弃他
        // 手机通讯录，
        ContentValues contentValues = new ContentValues();
        contentValues.put("_id", categoryBean.get_id());
        contentValues.put("createdAt", categoryBean.getCreatedAt());
        contentValues.put("desc", categoryBean.getDesc());
        contentValues.put("publishedAt", categoryBean.getPublishedAt());
        contentValues.put("source", categoryBean.getSource());
        contentValues.put("type", categoryBean.getType());
        contentValues.put("url", categoryBean.getUrl());
        contentValues.put("used", categoryBean.getUsed() ? 0 : 1);
        contentValues.put("images", new Gson().toJson(categoryBean.getImages()));
        contentValues.put("who", categoryBean.getWho());
        contentValues.put("like", categoryBean.getLike());
        getWritableDatabase().insertWithOnConflict("collection", null, contentValues, SQLiteDatabase.CONFLICT_REPLACE);
    }

    public int queryById(String id) {
        Cursor cursor = getReadableDatabase().rawQuery("select like from collection where _id = ? ", new String[]{id});
        if(cursor!=null){
            try{
                if(cursor.moveToNext()){
                    return cursor.getInt(0);
                }
            }finally {
                cursor.close();
            }
        }
        return  DISLIKE;
    }
    // 10
    public BaseBean<List<CategoryBean>> getCategoryBean(int page){
        BaseBean<List<CategoryBean>> baseBean=new BaseBean<>();
        List<CategoryBean> list=new ArrayList<>();
        baseBean.setResults(list);
        Cursor cursor=null;
       try {
            cursor = getReadableDatabase().rawQuery("select * from collection where like = 0 order by sort_id desc limit 10 offset ? ", new String[]{(page - 1) * 10 + ""});
           Log.e("tag",cursor+"--------------->");
           if(cursor!=null){
                while(cursor.moveToNext()){
                    CategoryBean bean=new CategoryBean();
                    bean.set_id(cursor.getString(cursor.getColumnIndex("_id")));
                    bean.setCreatedAt(cursor.getString(cursor.getColumnIndex("createdAt")));
                    bean.setDesc(cursor.getString(cursor.getColumnIndex("desc")));
                    String images = cursor.getString(cursor.getColumnIndex("images"));
                    if(images!=null){
                    bean.setImages((List<String>) new Gson().fromJson(images,new TypeToken<List<String>>(){}.getRawType()));
                    }
                    bean.setPublishedAt(cursor.getString(cursor.getColumnIndex("publishedAt")));
                    bean.setSource(cursor.getString(cursor.getColumnIndex("source")));
                    bean.setType(cursor.getString(cursor.getColumnIndex("type")));
                    bean.setUrl(cursor.getString(cursor.getColumnIndex("url")));
                    bean.setWho(cursor.getString(cursor.getColumnIndex("who")));
                    bean.setUsed(cursor.getInt(cursor.getColumnIndex("used"))==0);
                    bean.setLike(cursor.getInt(cursor.getColumnIndex("like")));
                    list.add(bean);
                }
            }
       }finally {
           if(cursor==null){
               cursor.close();
           }
       }

        Log.e("size",list.size()+"");
        return baseBean;
    }
}
