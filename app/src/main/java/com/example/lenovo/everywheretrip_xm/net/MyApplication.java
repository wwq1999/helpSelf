package com.example.lenovo.everywheretrip_xm.net;

import android.app.Application;

import com.umeng.commonsdk.UMConfigure;

/**
 * Created by Lenovo on 2019/5/2.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        UMConfigure.init(this,"5ccab1af3fc195c4bd00067a"
                ,"umeng",UMConfigure.DEVICE_TYPE_PHONE,"");//58edcfeb310c93091c000be2 5965ee00734be40b580001a0
    }
}
