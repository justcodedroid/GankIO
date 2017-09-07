package com.example.admin.gank.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.admin.gank.R;
import com.example.admin.gank.dialog.SharedDialog;
import com.example.admin.gank.entity.CategoryBean;
import com.example.admin.gank.utils.SqliteUtilsHelper;

import java.util.List;

import me.shaohui.shareutil.ShareUtil;
import me.shaohui.shareutil.share.ShareListener;

/**
 * Created by admin on 2017/9/6.
 */

public class CategoryWebActivity extends BaseWebActivity implements Toolbar.OnMenuItemClickListener, SharedDialog.OnSharedItemClickListener {
   private int[] p=new int[]{R.mipmap.like,R.mipmap.dislike};
    private  int index;
    private CategoryBean bean;
    private SharedDialog sharedDialog;

    {
        index=1;
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toolbar.inflateMenu(R.menu.menu_web_category);
        toolbar.setOnMenuItemClickListener(this);
        bean = getIntent().getParcelableExtra("bean");
        init();
        toolbar.getMenu().findItem(R.id.toogleCollectionMenu).setIcon(p[index]);

        String url = bean.getUrl();
        web.loadUrl(url);
    }

    private void init() {
        index=SqliteUtilsHelper.getInstance().queryById(bean.get_id());
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
           case  R.id.toogleCollectionMenu:
            toggleCollection(item);
            break;
            case R.id.shareMenu:
                share();
                break;
        }

        return true;
    }

    private void share()    {
        if(sharedDialog==null) {
            sharedDialog = new SharedDialog(this, this);
        }
        sharedDialog.show();
    }

    private void toggleCollection(MenuItem item) {
        index++;
        index=index%p.length;
        item.setIcon(p[index]);
        bean.setLike(index);
        SqliteUtilsHelper.getInstance().updateOrInsert(bean);

    }

    @Override
    public void onShareItemClick(Integer platfrom) {
        List<String> images = bean.getImages();
        String img=null;
        if(images!=null&&images.size()>0){
            img=images.get(0);
        }
        ShareUtil.shareMedia(this, platfrom, web.getTitle(), bean.getDesc(), bean.getUrl(), img, new ShareListener() {
            @Override
            public void shareSuccess() {
                Toast.makeText(CategoryWebActivity.this, "分享成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void shareFailure(Exception e) {
                Toast.makeText(CategoryWebActivity.this, "分享失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void shareCancel() {
                Toast.makeText(CategoryWebActivity.this, "分享取消", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
