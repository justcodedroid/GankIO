package com.example.admin.gank.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.example.admin.gank.R;

/**
 * Created by admin on 2017/9/6.
 */

public class BaseWebActivity extends BaseActivity implements View.OnClickListener {
    protected android.support.v7.widget.Toolbar toolbar;
    private android.widget.ProgressBar pb;
    protected WebView web;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_base_activity);
        this.web = (WebView) findViewById(R.id.web);
        this.pb = (ProgressBar) findViewById(R.id.pb);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(this);
        web.getSettings().setJavaScriptEnabled(true);// 支持javascript代码
        web.getSettings().setUseWideViewPort(true);
        web.getSettings().setLoadWithOverviewMode(true);
        web.getSettings().setSupportZoom(true);
        web.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);// 让链接点击的时候，仍然在Activity中，不启动第三方浏览器
                return true;
            }
        });

        web.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                // 收到网页中的title的时候操作
                setTitle(title);
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if(newProgress==0){
                   setWebProgressVisiable();
                }
                if(newProgress>0){
                    setWebProgress(newProgress);
                }
                if(newProgress==100){
                    setWebProgressInvisiable();
                }

            }
        });


    }

    @Override
    public void onBackPressed() {
        if(web.canGoBack()){
            web.goBack();
            return;
        }
        super.onBackPressed();
    }

    public void setTitle(String title){
        toolbar.setTitle(title);
    }
    public void setWebProgress(int progress){
        pb.setProgress(progress);
    }
    public void setWebProgressVisiable(){
        pb.setVisibility(View.VISIBLE);
    } public void setWebProgressInvisiable(){
        pb.setVisibility(View.GONE);
    }


    @Override
    public void onClick(View v) {
        finish();
    }
}
