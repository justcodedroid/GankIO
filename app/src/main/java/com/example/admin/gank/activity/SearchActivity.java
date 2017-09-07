package com.example.admin.gank.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.FrameLayout;

import com.example.admin.gank.R;
import com.example.admin.gank.fragment.SearchFragment;

public class SearchActivity extends BaseActivity implements SearchView.OnQueryTextListener {

    private android.support.v7.widget.SearchView searchView;
    private android.widget.FrameLayout serchGroup;
    private SearchFragment fragment=new SearchFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        this.serchGroup = (FrameLayout) findViewById(R.id.serchGroup);
        this.searchView = (SearchView) findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(this);
        replaceFragment(R.id.serchGroup,fragment);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        searchView.onActionViewCollapsed();

        fragment.reloadData(query);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
