package com.example.admin.gank.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by admin on 2017/9/6.
 */

public class SimplePagerFragmentAdapter<T extends Fragment> extends FragmentPagerAdapter {
    protected List<T> list;
    public SimplePagerFragmentAdapter(FragmentManager     fm, List<T> list) {
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
