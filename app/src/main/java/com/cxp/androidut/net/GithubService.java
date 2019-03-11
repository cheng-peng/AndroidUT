package com.cxp.androidut.net;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 文 件 名: GithubService
 * 创 建 人: CXP
 * 创建日期: 2019-03-11 7:49
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public class GithubService {

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(GithubApi.BASE_URL)
            .client(getOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();

    public static GithubApi createGithubService() {
        return retrofit.create(GithubApi.class);
    }

    private static OkHttpClient getOkHttpClient(){
        return new OkHttpClient.Builder()
                .addInterceptor(new LoggingInterceptor())
                .build();
    }

}
