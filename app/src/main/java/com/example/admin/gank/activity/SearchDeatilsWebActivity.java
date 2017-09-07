package com.example.admin.gank.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.admin.gank.R;

public class SearchDeatilsWebActivity extends BaseWebActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String htmlpart = getIntent().getStringExtra("htmlpart");
//        web.loadDataWithBaseURL(null,htmlpart,"text/html","UTF-8",null);
        web.loadUrl(htmlpart);
    }
}
