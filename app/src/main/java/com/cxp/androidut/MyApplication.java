package com.cxp.androidut;

import android.app.Application;

import com.elvishew.xlog.LogConfiguration;
import com.elvishew.xlog.LogLevel;
import com.elvishew.xlog.XLog;

/**
 * 文 件 名: MyApplication
 * 创 建 人: CXP
 * 创建日期: 2019-03-11 7:58
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        LogConfiguration config = new LogConfiguration.Builder()
                .logLevel(BuildConfig.DEBUG ? LogLevel.ALL : LogLevel.NONE)
                .build();
        XLog.init(config);
    }
}
