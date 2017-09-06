package com.example.admin.gank.activity;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.example.admin.gank.entity.CategoryBean;

/**
 * Created by admin on 2017/9/6.
 */

public class CategoryWebActivity extends BaseWebActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CategoryBean bean = getIntent().getParcelableExtra("bean");
        String url = bean.getUrl();
        web.loadUrl(url);
    }
}
