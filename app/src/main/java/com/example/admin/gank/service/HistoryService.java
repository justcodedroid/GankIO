package com.example.admin.gank.service;

import com.example.admin.gank.entity.BaseBean;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by admin on 2017/9/6.
 */

public interface HistoryService {
    @GET("/day/history")
    Call<BaseBean<List<String>>> getHistoryDate();
    @GET("/day/{year}/{month}/{day}")
    Call<BaseBean<ResponseBody>> getRawHistoryData(@Path("year")int year,@Path("month") int month,@Path("day") int day);
}
