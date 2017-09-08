package com.example.admin.gank.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.gank.MyApplication;
import com.example.admin.gank.R;
import com.example.admin.gank.utils.FileUtils;

import java.lang.ref.SoftReference;

import skin.support.SkinCompatManager;

public class SettingActivity extends BaseActivity implements View.OnClickListener {

    private android.widget.TextView huancunTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        if(Build.VERSION.SDK_INT>Build.VERSION_CODES.LOLLIPOP){
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        this.huancunTv = (TextView) findViewById(R.id.huancunTv);
        huancunTv.setOnClickListener(this);
        try {
            huancunTv.setText("缓存"+ FileUtils.getFolderSize(getExternalCacheDir())/8/1024/1024+"M");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.huancunTv:
                showDelteDialog();
                break;
        }
    }

    private void showDelteDialog() {
        new AlertDialog.Builder(this).setTitle("警告").setMessage("缓存可让应用运行更加流畅，是否清空?").setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                FileUtils.deleteDir(getExternalCacheDir());
                huancunTv.setText("缓存"+ "0M");

            }
        }).show();
    }
    public void toogle(View v){
        // 递归编译所有的view
      MyApplication.isYeJIan=true;
      MyApplication.app.toogle();
    }


}
