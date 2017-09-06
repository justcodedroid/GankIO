package com.example.admin.gank.fragment;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.gank.R;
import com.example.admin.gank.adapter.SimpleBaseAdapter;
import com.example.admin.gank.entity.BaseBean;
import com.example.admin.gank.entity.CategoryBean;
import com.example.admin.gank.http.HttpUtils;
import com.example.admin.gank.listener.SimpleResponseListener;
import com.example.admin.gank.utils.ActivityRouter;
import com.example.admin.gank.utils.CategoryAdapterFactory;

import java.util.List;

/**
 * Created by admin on 2017/9/6.
 */

public class CategoryFragment extends BaseLazyListFragment implements SimpleResponseListener<BaseBean<List<CategoryBean>>> {

    private int page;
    private String category;
    private SimpleBaseAdapter<CategoryBean> baseAdapter;
    private boolean isLoadData;

    public String getCategory() {
        return category;
    }

    public CategoryFragment setCategory(String category) {
        this.category = category;
        return this;
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ActivityRouter.startForCategoryActivity(a,getCategory(),baseAdapter.getItem(position));
    }

    @Override
    protected void lazyLoadData() {
        loadData();
    }

    private void loadData() {
        HttpUtils.getCategory(category,page,this);
    }

    @Override
    protected ListAdapter getAdapter() {
        baseAdapter = CategoryAdapterFactory.get(getCategory(), a);
        return baseAdapter;
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if(canLoadData()){
            Log.e("tag","fenye===>?"+isLoadData);
            if(firstVisibleItem+visibleItemCount==totalItemCount&&!isLoadData){
                isLoadData=true;
                loadData();
            }
        }
    }

    @Override
    public void onSuccess(BaseBean<List<CategoryBean>> data) {
            if(page==1){
                baseAdapter.update(null);
                swip.setRefreshing(false);
                baseAdapter.update(data.getResults());
            }else {
                if(data.getResults()==null||data.getResults().size()==0){
                    switchToNoDataView();
                }else {
                    isLoadData = false;
                    switchToLoadding();
                    baseAdapter.update(data.getResults());
                }
            }

              page++;
    }

    @Override
    public void onRefresh() {
        page=1;
        isLoadData=false;
        switchToLoadding();
        loadData();
    }

    @Override
    public void onFailed(String errorMsg, int errorCode) {
        Log.e("tag",errorMsg+"");
        if(page==1){
            swip.setRefreshing(false);
            if(baseAdapter.getCount()==0){
                
            }else {
                Toast.makeText(a, "下拉刷新失败", Toast.LENGTH_SHORT).show();
            }
        }else {

            isLoadData=false;
            Log.e("tag","加载失败===>?"+isLoadData);
            switchToErrorView();

        }
    }

    @Override
    protected void reLoadData() {
        super.reLoadData();
        switchToLoadding();
        loadData();
    }
}
