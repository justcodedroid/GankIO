package com.example.admin.gank.fragment;


import android.util.Log;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.example.admin.gank.adapter.HistoryAdapter;
import com.example.admin.gank.entity.BaseBean;
import com.example.admin.gank.entity.CategoryBean;
import com.example.admin.gank.http.HttpUtils;
import com.example.admin.gank.listener.SimpleResponseListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import okhttp3.ResponseBody;

/**
 * Created by admin on 2017/9/8.
 */

public class HistoryFragment extends BaseLazyListFragment implements HttpUtils.SimpleListener<ResponseBody> {
    private boolean isLoad=false;
    private List<String> dates;
    private SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
    private  int page=0;
    private HistoryAdapter adapter;

    @Override
    protected void lazyLoadData() {
    loadData();
    }

    @Override
    protected ListAdapter getAdapter() {
        adapter = new HistoryAdapter(a);
        return null;
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

    private void loadData() {
        if(dates==null){
            HttpUtils.getHistoryDate(new HistoryDateListener() );
            return;
        }
        loadJuTiDate();
    }

    private void loadJuTiDate() {
        String s = dates.get(page);
        try {
            Date date = df.parse(s);
            Calendar calendar= Calendar.getInstance();
            calendar.setTime(date);
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH)+1;
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            Log.e("date",year+"---->"+month+"---->"+day);
            HttpUtils.getRawHistoryData(year,month,day,this);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void reLoadData() {
        super.reLoadData();
        switchToLoadding();
        loadData();
    }

    @Override
    public void onSuccess(ResponseBody data) {
        try {
            String raw = data.string();
            JSONObject rawJob = new JSONObject(raw);
            List<String> categorys = (List<String>) new Gson().fromJson(rawJob.getString("category"),new TypeToken<List<String>>(){}.getRawType());
            Log.e("asdsadsadsadsadsadsad",categorys.toString());
            swip.setRefreshing(false);
            if(categorys==null)return;
            List<CategoryBean> temp=new ArrayList<>();
            String   gson=rawJob.getString("results");
            JSONObject job=new JSONObject(gson);
            CategoryBean type2=new CategoryBean();
            type2.setItemType(2);
            type2.setDesc(dates.get(page));
            temp.add(type2);
            for (int i=0;i<categorys.size();i++){
                String category = categorys.get(i);
                String jobString = job.getString(category);
                Log.e("ncd",jobString);
                List<CategoryBean> beanList=new ArrayList<>();
                JSONArray arr=new JSONArray(jobString);
                for (int j = 0; j < arr.length(); j++) {
                    JSONObject jsonObject = arr.getJSONObject(j);
                    Field[] declaredFields = CategoryBean.class.getDeclaredFields();
                    CategoryBean bean=new CategoryBean();
                    for (Field f:declaredFields) {
                    try {
                        f.setAccessible(true);
                        f.set(bean,jsonObject.get(f.getName()));
                    }catch (Exception e){
//                        e.printStackTrace();
                    }
                    }
                    beanList.add(bean);
                }

                Log.e("ncd","----->"+beanList.get(0));
               if(beanList!=null&&beanList.size()>0){
                   CategoryBean type1=new CategoryBean();
                   type1.setItemType(1);
                   type1.setDesc(category);
                   temp.add(type1);
                   temp.addAll(beanList);
               }
            }
            if(temp.size()==1){
                temp.remove(type2);
            }
            if(page==dates.size()-1){
                switchToNoDataView();
            }
            Log.e("sdsadsadsad","page--->"+temp.size()+"---------->"+page);
         if(page==0){
             swip.setRefreshing(false);
             adapter.update(null);
             adapter.update(temp);
             Log.e("traf","jiasdasdsadsadsadsa");
         }else {
             if(temp.size()>0) {
                 adapter.update(temp);
                 isLoad = false;
             }

         }
         if(lv.getAdapter()==null) {
             lv.setAdapter(adapter);
         }
            page++;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailed(String errorMsg, int errorCode) {
        Log.e("error",errorMsg+"");
        if(page==0){
            swip.setRefreshing(false);
            Toast.makeText(a, "下拉重试", Toast.LENGTH_SHORT).show();
        }else {
            switchToErrorView();
        }
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        page=0;
        loadData();
    }

    class HistoryDateListener implements SimpleResponseListener<BaseBean<List<String>>>{

        @Override
        public void onSuccess(BaseBean<List<String>> data) {
            dates = data.getResults();
            loadJuTiDate();
        }

        @Override
        public void onFailed(String errorMsg, int errorCode) {
            Toast.makeText(a, ""+errorMsg, Toast.LENGTH_SHORT).show();
        }
    }
}
