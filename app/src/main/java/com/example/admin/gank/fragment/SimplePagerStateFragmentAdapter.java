package com.example.admin.gank.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by admin on 2017/9/6.
 */

public class SimplePagerStateFragmentAdapter<T extends Fragment> extends FragmentStatePagerAdapter {
    protected List<T> list;
    public SimplePagerStateFragmentAdapter(FragmentManager     fm, List<T> list) {
        super(fm);
        this.list=list;

    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
