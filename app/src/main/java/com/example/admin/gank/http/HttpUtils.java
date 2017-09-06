package com.example.admin.gank.http;

import com.example.admin.gank.entity.BaseBean;
import com.example.admin.gank.entity.CategoryBean;
import com.example.admin.gank.entity.SearchBean;
import com.example.admin.gank.listener.SimpleCallBack;
import com.example.admin.gank.listener.SimpleResponseListener;
import com.example.admin.gank.service.CategoryService;
import com.example.admin.gank.service.HistoryService;
import com.example.admin.gank.service.RandomService;
import com.example.admin.gank.service.SearchService;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by admin on 2017/9/5.
 */

public class HttpUtils {
    private static Retrofit retrofit;
    static {
        retrofit=new Retrofit.Builder().baseUrl(HttpModel.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    }
    public static void getCategory(String category, int page, SimpleResponseListener<BaseBean<List<CategoryBean>>> listener){
        retrofit.create(CategoryService.class).getCategoryBean(category,page).enqueue(new SimpleCallBack<BaseBean<List<CategoryBean>>>(listener));
    }
    public static void getRandom(SimpleResponseListener<BaseBean<List<CategoryBean>>> listener){
        retrofit.create(RandomService.class).getRandom().enqueue(new SimpleCallBack<BaseBean<List<CategoryBean>>>(listener));
    }
    public static void getHistoryDate(SimpleResponseListener<BaseBean<List<String>>> listener){
        retrofit.create(HistoryService.class).getHistoryDate().enqueue(new SimpleCallBack<BaseBean<List<String>>>(listener));
    }
    public static void getRawHistoryData(int year,int month,int day,SimpleResponseListener<BaseBean<ResponseBody>> listener) {
        retrofit.create(HistoryService.class).getRawHistoryData(year,month,day).enqueue(new SimpleCallBack<BaseBean<ResponseBody>>(listener));
    }
    public static void getSearch(String keyword,int page, SimpleResponseListener<BaseBean<List<SearchBean>>> listener){
        retrofit.create(SearchService.class).getSearchBean(keyword,page).enqueue(new SimpleCallBack<BaseBean<List<SearchBean>>>(listener));
    }



}
