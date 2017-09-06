package com.example.admin.gank.fragment;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.admin.gank.R;

/**
 * Created by admin on 2017/9/6.
 */

public abstract class BaseLazyListFragment extends LazyBaseFragment implements SwipeRefreshLayout.OnRefreshListener, AbsListView.OnScrollListener, View.OnClickListener, AdapterView.OnItemClickListener {

    protected SwipeRefreshLayout swip;
    private ListView lv;
    private  boolean isNoHead;
    private boolean isNoFooter;
    private View footerView;
    private View loaddingView;
    private View errorView;
    private View noDataView;

    protected  BaseLazyListFragment setNoHead(boolean isNoHead){
         this.isNoHead=isNoHead;
        return this;
     }protected  BaseLazyListFragment setNoFooter(boolean isNoFooter){
         this.isNoFooter=isNoFooter;
        return  this;
     }
    protected  boolean canLoadData(){
        return  !isNoFooter;
    }
    @Override
    protected abstract void lazyLoadData();

    @Override
    protected void initView(View rootView) {
        initSwip(rootView);
        initList(rootView);
    }

    private void initList(View rootView) {
        lv = (ListView) rootView.findViewById(R.id.lv);
        if(!isNoFooter){
            footerView = LayoutInflater.from(a).inflate(R.layout.footer_lv, null);
            loaddingView = footerView.findViewById(R.id.loaddingView);
            errorView = footerView.findViewById(R.id.errorView);
            errorView.setOnClickListener(this);
            noDataView = footerView.findViewById(R.id.noDataView);
            lv.addFooterView(footerView,null,false);
            lv.setOnScrollListener(this);
        }
        lv.setAdapter(getAdapter());
        lv.setOnItemClickListener(this);
    }

    protected abstract ListAdapter getAdapter();

    protected void switchToLoadding(){
        loaddingView.setVisibility(View.VISIBLE);
        errorView.setVisibility(View.GONE);
        noDataView.setVisibility(View.GONE);
    }
    protected void switchToErrorView(){
        loaddingView.setVisibility(View.GONE);
        errorView.setVisibility(View.VISIBLE);
        noDataView.setVisibility(View.GONE);

    }
    protected void switchToNoDataView(){
        loaddingView.setVisibility(View.GONE);
        errorView.setVisibility(View.GONE);
        noDataView.setVisibility(View.VISIBLE);
    }


    private void initSwip(View rootView) {
        swip = (SwipeRefreshLayout) rootView.findViewById(R.id.swip);
        swip.setEnabled(!isNoHead);
        if(!isNoHead){
            swip.setColorSchemeColors(getResources().getColor(R.color.colorAccent));
            swip.setOnRefreshListener(this);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_list_lazy_base;
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public abstract void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) ;

    @Override
    public void onClick(View v) {
        reLoadData();
    }

    protected void reLoadData() {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
