package com.example.admin.gank.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.example.admin.gank.activity.CategoryWebActivity;
import com.example.admin.gank.activity.ImageActivity;
import com.example.admin.gank.entity.CategoryBean;
import com.example.admin.gank.http.HttpModel;

/**
 * Created by admin on 2017/9/6.
 */

public class ActivityRouter {
    public static void startForCategoryActivity(Context context, String category, CategoryBean categoryBean){
        if(category.equals(HttpModel.CATEGORT_FU_LI)){
            String imgPath = categoryBean.getUrl();
            Intent intent=new Intent(context, ImageActivity.class);
            intent.putExtra("img",imgPath);
            context.startActivity(intent);
        }else {
            Intent intent=new Intent(context, CategoryWebActivity.class);
            intent.putExtra("bean",categoryBean);
            context.startActivity(intent);
        }
    }


}
