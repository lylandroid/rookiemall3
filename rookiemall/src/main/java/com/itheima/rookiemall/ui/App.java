package com.itheima.rookiemall.ui;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.itheima.retrofitutils.ItheimaHttp;
import com.itheima.rookiemall.config.Urls;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by lyl on 2016/10/3.
 */

public class App extends Application {

    private static App sIntance;

    private Intent mIntent;

    @Override
    public void onCreate() {
        super.onCreate();
        sIntance = this;
        ItheimaHttp.init(this, Urls.getBaseUrl());
        ItheimaHttp.setHttpCache(true);

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        refWatcher = LeakCanary.install(this);
    }

    public static App getIntance() {
        return sIntance;
    }

    public void putIntent(Intent intent) {
        mIntent = intent;
    }

    public Intent getIntent() {
        Intent intent = mIntent;
        mIntent = null;
        return intent;
    }

    public static RefWatcher getRefWatcher(Context context) {
        App application = (App) context.getApplicationContext();
        return application.refWatcher;
    }

    private RefWatcher refWatcher;

}
