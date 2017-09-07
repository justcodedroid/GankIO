package com.example.admin.gank.activity;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.admin.gank.R;
import com.example.admin.gank.utils.MD5Utils;
import com.github.chrisbanes.photoview.PhotoView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import retrofit2.http.Url;

public class ImageActivity extends BaseActivity implements View.OnLongClickListener {

    private com.github.chrisbanes.photoview.PhotoView iv;
    private String imgPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        this.iv = (PhotoView) findViewById(R.id.iv);
        iv.setOnLongClickListener(this);
        imgPath = getIntent().getStringExtra("img");
        init();
    }

    private void init() {
        Glide.with(this).load(imgPath).into(iv);
    }
    public void save() throws IOException {
        BitmapDrawable drawable = (BitmapDrawable) iv.getDrawable();
        if(drawable==null){
            Toast.makeText(this, "图片还未加载过来", Toast.LENGTH_SHORT).show();
            return;
        }
        String s = MD5Utils.md5(imgPath);
        File parent = getExternalFilesDir("image");
        File file = new File(parent, s + ".png");
        FileOutputStream fileOutputStream=new FileOutputStream(file);
        drawable.getBitmap().compress(Bitmap.CompressFormat.PNG,100,fileOutputStream);
        fileOutputStream.close();
        Toast.makeText(this, "以保存到"+file.getAbsolutePath(), Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onLongClick(View v) {
        new AlertDialog.Builder(this).setMessage("是否保存图片").setTitle("提示").setNegativeButton("保存", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    dialog.dismiss();
                    save();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).setPositiveButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).show();
        return true;
    }
}
