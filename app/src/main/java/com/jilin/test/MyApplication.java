package com.jilin.test;

import org.litepal.LitePalApplication;


/**
 * Created by ruthout on 2016/8/17.
 */
public class MyApplication extends LitePalApplication {

    public static MyApplication mInstance;

    public MyApplication() {
        mInstance = this;
    }

    public static MyApplication getApp() {
        if (mInstance != null && mInstance instanceof MyApplication) {
            return mInstance;
        } else {
            mInstance = new MyApplication();
            mInstance.onCreate();
            return mInstance;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }
}
