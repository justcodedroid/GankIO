package com.example.admin.gank.fragment;

import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.example.admin.gank.adapter.SimpleBaseAdapter;
import com.example.admin.gank.entity.BaseBean;
import com.example.admin.gank.entity.CategoryBean;
import com.example.admin.gank.http.HttpModel;
import com.example.admin.gank.http.HttpUtils;
import com.example.admin.gank.listener.SimpleResponseListener;
import com.example.admin.gank.utils.ActivityRouter;
import com.example.admin.gank.utils.CategoryAdapterFactory;

import java.util.List;

/**
 * Created by admin on 2017/9/7.
 */

public class CollectionFragment extends BaseLazyListFragment implements SimpleResponseListener<BaseBean<List<CategoryBean>>> {

    private SimpleBaseAdapter<CategoryBean> adapter;
    private  int page=1;
    private boolean isLoad=false;
    @Override
    protected void lazyLoadData() {
//        swip.setRefreshing(true);
            loadData();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        super.onItemClick(parent, view, position, id);
        ActivityRouter.startForCategoryActivity(a,((CategoryBean)parent.getItemAtPosition(position)).getType(),(CategoryBean)parent.getItemAtPosition(position));
    }

    private void loadData() {
        Log.e("asd","开始加载数据");
        HttpUtils.getCollection(page,this);
    }

    @Override
    protected ListAdapter getAdapter() {
        adapter = CategoryAdapterFactory.get(HttpModel.CATEGORT_ANDROID, a);
        return adapter;
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        page=1;
        isLoad=false;
        loadData();

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if(canLoadData()&&adapter!=null&&adapter.getCount()>=10){
            if(firstVisibleItem+visibleItemCount==totalItemCount&&!isLoad){
                isLoad=true;
                loadData();
            }
        }
    }

    @Override
    public void onSuccess(BaseBean<List<CategoryBean>> data) {
        if(data.getResults()==null||data.getResults().size()<10){
            switchToNoDataView();
        }
        if(page==1){
            swip.setRefreshing(false);
            adapter.update(null);
            Log.e("asdsadsa","开始更新数据");
            adapter.update(data.getResults());
        }else {

           if(data.getResults()!=null) {
               adapter.update(data.getResults());
               isLoad = false;
           }

        }
        page++;
    }

    @Override
    public void onFailed(String errorMsg, int errorCode) {
        Log.e("error",errorMsg+"");

        if(page==1){
            swip.setRefreshing(false);
            Toast.makeText(a, "下拉重试", Toast.LENGTH_SHORT).show();
        }else {
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
