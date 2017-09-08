package com.example.admin.gank.http;

import android.os.Handler;
import android.os.Looper;

import com.example.admin.gank.MyApplication;
import com.example.admin.gank.entity.BaseBean;
import com.example.admin.gank.entity.CategoryBean;
import com.example.admin.gank.entity.SearchBean;
import com.example.admin.gank.listener.SimpleCallBack;
import com.example.admin.gank.listener.SimpleResponseListener;
import com.example.admin.gank.service.CategoryService;
import com.example.admin.gank.service.HistoryService;
import com.example.admin.gank.service.RandomService;
import com.example.admin.gank.service.SearchService;
import com.example.admin.gank.utils.SqliteUtilsHelper;
import com.sina.weibo.sdk.api.share.Base;

import java.io.File;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.internal.cache.*;
import okhttp3.internal.cache.CacheInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by admin on 2017/9/5.
 */

public class HttpUtils {
    private static Retrofit retrofit;
    private static ExecutorService POOL= Executors.newSingleThreadExecutor();
    private static Handler handler=new Handler(Looper.getMainLooper());
    static {
        OkHttpClient client = new OkHttpClient.Builder().cache(new Cache(new File(MyApplication.app.getExternalCacheDir(), "web"), 1024321421)).addNetworkInterceptor(new com.example.admin.gank.http.CacheInterceptor()).build();
        retrofit=new Retrofit.Builder().baseUrl(HttpModel.BASE_URL).client(client).addConverterFactory(GsonConverterFactory.create()).build();
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
    public static void getCollection(final int page, final SimpleResponseListener<BaseBean<List<CategoryBean>>> listSimpleResponseListener){
        POOL.submit(new Runnable() {
            @Override
            public void run() {
               try{
                   final BaseBean<List<CategoryBean>> categoryBean = SqliteUtilsHelper.getInstance().getCategoryBean(page);
                   handler.post(new Runnable() {
                       @Override
                       public void run() {
                           listSimpleResponseListener.onSuccess(categoryBean);
                       }
                   });
               }catch (final Exception e){
                   handler.post(new Runnable() {
                       @Override
                       public void run() {
                           listSimpleResponseListener.onFailed(e.getMessage(),ErrorModel.HTTP_API_ERROR_CODE);
                       }
                   });
               }
            }
        });
    }



}
