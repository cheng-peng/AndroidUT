package com.cxp.androidut.espresso.okhttp;

import com.cxp.androidut.bean.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * 文 件 名: GitHubService
 * 创 建 人: CXP
 * 创建日期: 2019-03-12 14:06
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public interface GitHubService {

    @GET("users/{user}")
    Call<User> getUser(@Path("user") String user);
}
