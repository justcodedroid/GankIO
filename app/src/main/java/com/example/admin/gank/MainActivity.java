package com.example.admin.gank;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.MenuItem;

import com.example.admin.gank.activity.BaseActivity;
import com.example.admin.gank.activity.SearchActivity;
import com.example.admin.gank.fragment.CollectionFragment;
import com.example.admin.gank.fragment.HomeFragment;
import com.example.admin.gank.fragment.RandomFragment;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    private android.support.v7.widget.Toolbar toolbar;
    private android.support.design.widget.NavigationView navitionView;
    private android.support.v4.widget.DrawerLayout drawer;

    private SparseArray<Fragment> map=new SparseArray<>();
    {
        map.put(R.id.homeMenu,new HomeFragment());
        map.put(R.id.randomMenu,new RandomFragment());
        map.put(R.id.collectionMenu,new CollectionFragment());
    }
    private SparseArray<String> mapTitle=new SparseArray<>();
    {
        mapTitle.put(R.id.homeMenu,"首页");
        mapTitle.put(R.id.randomMenu,"随机");
        mapTitle.put(R.id.collectionMenu,"收藏");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initFragment();
        initListener();

    }

    private void initFragment() {
        replaceFragment(R.id.homeMenu);
    }
    public void replaceFragment(int menuId){
        replaceFragment(R.id.fragmentGroup,map.get(menuId));
        toolbar.setSubtitle(mapTitle.get(menuId));
    }

    private void initView() {
        this.drawer = (DrawerLayout) findViewById(R.id.drawer);
        this.navitionView = (NavigationView) findViewById(R.id.navitionView);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        navitionView.setCheckedItem(R.id.homeMenu);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawer,toolbar,R.string.app_name,R.string.app_name);
        toggle.syncState();
        drawer.setDrawerListener(toggle);
    }

    private void initListener() {
        navitionView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
      switch (item.getItemId()){
          case R.id.homeMenu:
          case R.id.randomMenu:
          case R.id.collectionMenu:
              drawer.closeDrawer(navitionView);
              replaceFragment(item.getItemId());
              break;
          case R.id.searchMenu:
              startActivity(new Intent(this, SearchActivity.class));
              break;
      }
        return true;
    }
}
