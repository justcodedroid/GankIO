package com.example.admin.gank.listener;



import com.example.admin.gank.entity.BaseBean;
import com.example.admin.gank.http.ErrorModel;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by admin on 2017/9/5.
 */

public class SimpleCallBack<T extends BaseBean>  implements Callback<T> {
    private SimpleResponseListener<T> listener;

    public SimpleCallBack(SimpleResponseListener<T> listener) {
        this.listener = listener;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        boolean successful = response.isSuccessful();
        if(successful){
            boolean error = response.body().isError();
            if(!error){
                listener.onSuccess(response.body());
            }else {
                listener.onFailed(response.body()+"", ErrorModel.HTTP_RAMOTE_ERROR_CODE);
            }
        }else {
            try {
                listener.onFailed(response.errorBody().string(),response.code());
            } catch (IOException e) {
                e.printStackTrace();
                listener.onFailed(e.getMessage(),ErrorModel.HTTP_IO_ERROR_CODE);
            }
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        listener.onFailed(t.getMessage(),ErrorModel.HTTP_API_ERROR_CODE);
    }
}
