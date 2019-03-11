package com.cxp.androidut.net;

import com.cxp.androidut.bean.User;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * 文 件 名: GithubApi
 * 创 建 人: CXP
 * 创建日期: 2019-03-11 7:49
 * 描    述: Github接口
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public interface GithubApi {

    String BASE_URL = "https://api.github.com/";

    @GET("users/{username}")
    Observable<User> getUser(@Path("username") String username);
}
