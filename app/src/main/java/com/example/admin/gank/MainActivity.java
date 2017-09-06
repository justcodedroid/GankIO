package com.example.admin.gank;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.admin.gank.activity.BaseActivity;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    private android.support.v7.widget.Toolbar toolbar;
    private android.support.design.widget.NavigationView navitionView;
    private android.support.v4.widget.DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.drawer = (DrawerLayout) findViewById(R.id.drawer);
        this.navitionView = (NavigationView) findViewById(R.id.navitionView);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        navitionView.setCheckedItem(R.id.homeMenu);
        initListener();
    }

    private void initListener() {
        navitionView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawer.closeDrawer(navitionView);

        return true;
    }
}
