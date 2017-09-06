package com.example.admin.gank.service;

import com.example.admin.gank.entity.BaseBean;
import com.example.admin.gank.entity.SearchBean;
import com.example.admin.gank.http.HttpModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by admin on 2017/9/6.
 */

public interface SearchService {
    @GET("search/query/{keyword}/category/"+ HttpModel.CATEGORT_ALL+"/count/10/page/{page}")
    Call<BaseBean<List<SearchBean>>> getSearchBean(@Path("keyword") String keyWord,@Path("page") int page );
}
