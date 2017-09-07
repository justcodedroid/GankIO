package com.example.admin.gank.utils;

import android.util.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by admin on 2017/9/7.
 */

public class MD5Utils {
    private static MessageDigest digest;
    static {
        try {
            digest=MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
    public static String md5(String str){
        digest.update(str.getBytes());
        byte[] bytes = MD5Utils.digest.digest();
        return Base64.encodeToString(bytes,Base64.DEFAULT);
    }
}
