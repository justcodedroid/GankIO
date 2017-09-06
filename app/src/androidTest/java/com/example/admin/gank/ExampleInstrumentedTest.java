package com.example.admin.gank;

import android.content.Context;
import android.support.annotation.WorkerThread;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.example.admin.gank.entity.BaseBean;
import com.example.admin.gank.entity.CategoryBean;
import com.example.admin.gank.http.HttpModel;
import com.example.admin.gank.http.HttpUtils;
import com.example.admin.gank.listener.SimpleResponseListener;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("com.example.admin.gank", appContext.getPackageName());
    }

}
