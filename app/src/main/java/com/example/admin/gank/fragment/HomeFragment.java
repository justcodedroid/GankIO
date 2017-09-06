package com.example.admin.gank.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.admin.gank.R;
import com.example.admin.gank.http.HttpModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/9/6.
 */

public class HomeFragment extends BaseFragment {

    private TabLayout homeTab;
    private ViewPager homePager;
    private List<CategoryFragment> homeList = new ArrayList<>();;
    {
        homeList.add(new CategoryFragment().setCategory(HttpModel.CATEGORT_ANDROID));
        homeList.add(new CategoryFragment().setCategory(HttpModel.CATEGORT_IOS));
        homeList.add(new CategoryFragment().setCategory(HttpModel.CATEGORT_QIAN_DUAN));
        homeList.add(new CategoryFragment().setCategory(HttpModel.CATEGORT_FU_LI));
        homeList.add(new CategoryFragment().setCategory(HttpModel.CATEGORT_SHI_PIN));
        homeList.add(new CategoryFragment().setCategory(HttpModel.CATEGORT_ZI_YUAN));
    }
    @Override
    protected void initView(View rootView) {
        homeTab = (TabLayout) rootView.findViewById(R.id.homeTab);
        homePager = (ViewPager) rootView.findViewById(R.id.homePager);
        homePager.setAdapter(new CategoryPagerFragmentAdapter(getFragmentManager(),homeList));
        homeTab.setupWithViewPager(homePager);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }
}
