package com.example.admin.gank.service;

import com.example.admin.gank.entity.BaseBean;
import com.example.admin.gank.entity.CategoryBean;
import com.example.admin.gank.http.HttpModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by admin on 2017/9/6.
 */

public interface RandomService {
@GET("/random/data/"+ HttpModel.CATEGORT_ALL+"/20")
    Call<BaseBean<List<CategoryBean>>> getRandom();
}
