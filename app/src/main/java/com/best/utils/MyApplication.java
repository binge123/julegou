package com.best.utils;

import android.app.Application;

import org.xutils.BuildConfig;
import org.xutils.x;

/**
 * Created by dell2 on 2015/12/17.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);
    }
}
