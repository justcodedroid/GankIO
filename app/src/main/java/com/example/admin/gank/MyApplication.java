package com.example.admin.gank;

import android.app.Activity;
import android.app.Application;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.cache.DiskCacheAdapter;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.load.engine.cache.DiskLruCacheWrapper;

import java.io.File;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

import me.shaohui.shareutil.ShareConfig;
import me.shaohui.shareutil.ShareManager;
import skin.support.SkinCompatManager;

/**
 * Created by admin on 2017/9/7.
 */

public class MyApplication  extends Application implements Application.ActivityLifecycleCallbacks {
    public static MyApplication app;
    public static boolean isYeJIan;
    public List<SoftReference<Activity>> activities=new ArrayList<>();
    @Override
    public void onCreate() {
        super.onCreate();
         app=this;
        ShareConfig config = ShareConfig.instance()
                .qqId("123456789")
                .wxId("wx9952889c07feb70f")
                .weiboId("3704919133")
                // 下面两个，如果不需要登录功能，可不填写
                .weiboRedirectUrl("https://api.weibo.com/oauth2/default.html")
                .wxSecret("0ac923401e96bd71ceb1daaab0343dbc");
        registerActivityLifecycleCallbacks(this);
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        activities.add(new SoftReference<Activity>(activity));
        if(isYeJIan){
            toogle();
        }
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        SoftReference<Activity> temp = null;
        for (SoftReference<Activity> a:activities
             ) {
            Activity activity1 = a.get();
            if(activity1!=null&&activity1.getClass()==activity.getClass()){
                temp=a;
                break;
            }
        }
        if(temp!=null){
            activities.remove(temp);
        }
    }

    public void toogle() {
        for (SoftReference<Activity> a: MyApplication.app.activities
                ) {
            Activity activity = a.get();
            if(activity!=null){
                activity.getWindow().setBackgroundDrawable(new ColorDrawable(Color.BLACK));
                View decorView = activity.getWindow().getDecorView();
                set(decorView);
            }
        }
    }
    private void set(View decorView) {
        if(decorView instanceof ViewGroup){
            ViewGroup g= (ViewGroup) decorView;
            for (int i=0;i<g.getChildCount();i++){
                set(g.getChildAt(i));
            }
        }else {
            if(decorView instanceof TextView){
                ((TextView) decorView).setTextColor(Color.RED);
            }
        }
    }
}
