package com.example.admin.gank.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Created by admin on 2017/9/6.
 */

public abstract class LazyBaseFragment extends BaseFragment {
    private  boolean isFirstLazyLoad=true;
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if(getRootView()!=null){
            performLazyLoadData();
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        performLazyLoadData();
    }

    private void performLazyLoadData() {
        if(getUserVisibleHint()&&isFirstLazyLoad){
            isFirstLazyLoad=false;
            lazyLoadData();
        }
    }

    protected abstract void lazyLoadData() ;

    @Override
    protected abstract void initView(View rootView) ;

    @Override
    protected abstract int getLayoutId() ;
}
