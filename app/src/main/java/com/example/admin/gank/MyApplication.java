package com.example.admin.gank;

import android.app.Application;

import me.shaohui.shareutil.ShareConfig;
import me.shaohui.shareutil.ShareManager;

/**
 * Created by admin on 2017/9/7.
 */

public class MyApplication  extends Application{
    public static MyApplication app;

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
        ShareManager.init(config);
    }
}
