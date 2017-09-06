package com.example.admin.gank.fragment;

import android.support.v4.app.FragmentManager;

import java.util.List;

/**
 * Created by admin on 2017/9/6.
 */

public class CategoryPagerFragmentAdapter extends SimplePagerFragmentAdapter<CategoryFragment> {
    public CategoryPagerFragmentAdapter(FragmentManager fm, List<CategoryFragment> list) {
        super(fm, list);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position).getCategory();
    }
}
