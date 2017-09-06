package com.example.admin.gank.utils;

import android.content.Context;
import android.widget.BaseAdapter;

import com.example.admin.gank.adapter.CategoryAdapter;
import com.example.admin.gank.adapter.SimpleBaseAdapter;
import com.example.admin.gank.entity.CategoryBean;
import com.example.admin.gank.http.HttpModel;

/**
 * Created by admin on 2017/9/6.
 */

public class CategoryAdapterFactory {
    public static SimpleBaseAdapter<CategoryBean> get(String category, Context context){
        switch (category){
            case HttpModel.CATEGORT_ANDROID:
            case HttpModel.CATEGORT_FU_LI:
            case HttpModel.CATEGORT_IOS:
            case HttpModel.CATEGORT_QIAN_DUAN:
            case HttpModel.CATEGORT_SHI_PIN:
            case HttpModel.CATEGORT_ZI_YUAN:
                return new CategoryAdapter(context);

        }
    return  null;
    }

}
