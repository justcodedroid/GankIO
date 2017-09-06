package com.example.admin.gank.service;


import com.example.admin.gank.entity.BaseBean;
import com.example.admin.gank.entity.CategoryBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by admin on 2017/9/5.
 */

public interface CategoryService {
    @GET("data/{category}/10/{page}")
    Call<BaseBean<List<CategoryBean>>> getCategoryBean(@Path("category") String category, @Path("page") int page);
}
