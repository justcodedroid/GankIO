package com.example.admin.gank.http;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.load.engine.cache.DiskLruCacheWrapper;
import com.bumptech.glide.module.GlideModule;

import java.io.File;

/**
 * Created by admin on 2017/9/8.
 */

public class CustomDiskcacheMoudle implements GlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        builder.setDiskCache(new MyDiskCache(context.getExternalCacheDir(),1024*1024*80));
    }

    @Override
    public void registerComponents(Context context, Glide glide, Registry registry) {

    }
    class MyDiskCache extends DiskLruCacheWrapper{

        protected MyDiskCache(File directory, int maxSize) {
            super(directory, maxSize);
        }
    }
}
