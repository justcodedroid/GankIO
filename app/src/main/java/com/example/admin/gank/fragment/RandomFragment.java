package com.example.admin.gank.fragment;

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
 * Created by admin on 2017/9/6.
 */

public class RandomFragment extends BaseLazyListFragment implements SimpleResponseListener<BaseBean<List<CategoryBean>>> {

    private SimpleBaseAdapter<CategoryBean> categoryBeanSimpleBaseAdapter;

    public RandomFragment(){
        setNoFooter(true);

    }
    @Override
    protected void lazyLoadData() {
        swip.setRefreshing(true);
        loadData();
    }

    private void loadData() {
        HttpUtils.getRandom(this);
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        loadData();
    }

    @Override
    protected ListAdapter getAdapter() {
        categoryBeanSimpleBaseAdapter = CategoryAdapterFactory.get(HttpModel.CATEGORT_ANDROID, a);
        return categoryBeanSimpleBaseAdapter;
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }

    @Override
    public void onSuccess(BaseBean<List<CategoryBean>> data) {
        swip.setRefreshing(false);
        categoryBeanSimpleBaseAdapter.update(null);
        categoryBeanSimpleBaseAdapter.update(data.getResults());
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        super.onItemClick(parent, view, position, id);
        ActivityRouter.startForCategoryActivity(a,categoryBeanSimpleBaseAdapter.getItem(position).getType(),categoryBeanSimpleBaseAdapter.getItem(position));
    }

    @Override
    public void onFailed(String errorMsg, int errorCode) {
        swip.setRefreshing(false);
        Toast.makeText(a, "失败，请重试->"+errorMsg, Toast.LENGTH_SHORT).show();
    }
}
