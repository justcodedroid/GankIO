package com.example.admin.gank.fragment;

import android.content.Intent;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.example.admin.gank.activity.SearchDeatilsWebActivity;
import com.example.admin.gank.adapter.SearchAdapter;
import com.example.admin.gank.entity.BaseBean;
import com.example.admin.gank.entity.SearchBean;
import com.example.admin.gank.http.HttpUtils;
import com.example.admin.gank.listener.SimpleResponseListener;

import java.util.List;

/**
 * Created by admin on 2017/9/7.
 */

public class SearchFragment extends BaseLazyListFragment implements SimpleResponseListener<BaseBean<List<SearchBean>>> {
    private  int page=1;
    private  String keyword;
    private SearchAdapter searchAdapter;
    private  boolean isLoad;

    @Override
    protected void lazyLoadData() {

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

            if(getAdapter()==null||getAdapter().getCount()==0){
            setFooterViewGone();
            }else if(getAdapter()!=null&&getAdapter().getCount()>0){
                setFooterViewVisiable();
            }

    }

    @Override
    protected ListAdapter getAdapter() {
        searchAdapter = new SearchAdapter(a);
        return searchAdapter;
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        page=1;
        loadData();
    }

    @Override
    protected void reLoadData() {
        super.reLoadData();
        loadData();
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if(canLoadData()&&searchAdapter!=null&&searchAdapter.getCount()>=10){
            if(firstVisibleItem+visibleItemCount==totalItemCount&&!isLoad){
                isLoad=true;
                loadData();
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        super.onItemClick(parent, view, position, id);
        Intent intent=new Intent(a, SearchDeatilsWebActivity.class);
        SearchBean bean=searchAdapter.getItem(position);
        intent.putExtra("htmlpart",bean.getUrl());
        startActivity(intent);
    }

    public void reloadData(String keyword) {
        page=1;
        this.keyword=keyword;
        searchAdapter.update(null);
        searchAdapter.notifyDataSetChanged();
        swip.setRefreshing(true);
        loadData();
    }

    private void loadData() {
        HttpUtils.getSearch(keyword,page,this);
    }

    @Override
    public void onSuccess(BaseBean<List<SearchBean>> data) {

        if(data.getResults()==null||data.getResults().size()<10){
            switchToNoDataView();
        }
        if(page==1){
            swip.setRefreshing(false);
            searchAdapter.update(null);
            searchAdapter.update(data.getResults());
        }else {
            if(data.getResults().size()>0){
                isLoad=false;
                searchAdapter.update(data.getResults());

            }
        }
        page++;
    }

    @Override
    public void onFailed(String errorMsg, int errorCode) {
        if(page==1){
            swip.setRefreshing(false);
            Toast.makeText(a, "下拉重试", Toast.LENGTH_SHORT).show();
        }else {
            isLoad=false;
            switchToErrorView();
        }
    }
}
