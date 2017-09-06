package com.example.admin.gank.utils;

import android.content.Context;
import android.content.Intent;

import com.example.admin.gank.activity.CategoryWebActivity;
import com.example.admin.gank.entity.CategoryBean;

/**
 * Created by admin on 2017/9/6.
 */

public class ActivityRouter {
    public static void startForCategoryActivity(Context context, String category, CategoryBean categoryBean){
        Intent intent=new Intent(context, CategoryWebActivity.class);
        intent.putExtra("bean",categoryBean);
        context.startActivity(intent);
    }
}
