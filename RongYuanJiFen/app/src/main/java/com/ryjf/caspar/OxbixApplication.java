package com.ryjf.caspar;


import android.app.Application;
import android.content.Context;

import java.util.concurrent.TimeUnit;

import z.sye.space.library.OkHttpManager;

public class OxbixApplication extends Application {
    private static OxbixApplication mInstance = null;



    @Override
    public void onCreate() {
        super.onCreate();
        if (null == mInstance) {
            mInstance = this;
        }

        //初始化OkHttp
        OkHttpManager.setConnectTimeOut(15, TimeUnit.SECONDS)
                .setWriteTimeout(15, TimeUnit.SECONDS)
                .setReadTimeout(15, TimeUnit.SECONDS);

    }




    public static Context getContext() {
        return mInstance;
    }


}
