package com.cxp.androidut.espresso.okhttp;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * 文 件 名: OkHttpProvider
 * 创 建 人: CXP
 * 创建日期: 2019-03-12 14:07
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public abstract class OkHttpProvider {

    private static OkHttpClient instance = null;

    public static OkHttpClient getOkHttpInstance() {
        if (instance == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY);

            instance = new OkHttpClient()
                    .newBuilder()
                    .addInterceptor(interceptor)
                    .build();
        }
        return instance;
    }
}
