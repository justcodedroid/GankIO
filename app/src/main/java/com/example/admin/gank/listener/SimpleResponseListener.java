package com.example.admin.gank.listener;


import com.example.admin.gank.entity.BaseBean;

/**
 * Created by admin on 2017/9/5.
 */

public interface SimpleResponseListener<T extends BaseBean>{
    void onSuccess(T data);
    void onFailed(String errorMsg, int errorCode);
}
