package com.cxp.androidut.net;

import android.util.Log;

import com.cxp.androidut.BuildConfig;
import com.cxp.androidut.bean.User;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import java.net.URISyntaxException;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.Assert.assertEquals;

/**
 * 文 件 名: MockGithubServiceTest
 * 创 建 人: CXP
 * 创建日期: 2019-03-11 8:42
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 23)
public class MockGithubServiceTest {

    private static final String JSON_ROOT_PATH = "/json/";
    private String jsonFullPath;
    private GithubApi mockGithubService;

    @Rule
    public RxJavaRule rule = new RxJavaRule();

    @Before
    public void setUp() throws URISyntaxException {

        ShadowLog.stream = System.out;
        //获取测试json文件地址
        jsonFullPath = getClass().getResource(JSON_ROOT_PATH).toURI().getPath();

        //定义Http Client,并添加拦截器
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new LoggingInterceptor())
                .addInterceptor(new MockInterceptor(jsonFullPath))
                .build();

        //设置Http Client
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GithubApi.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        mockGithubService = retrofit.create(GithubApi.class);
    }

    @Test
    public void getUserTest()  {
        mockGithubService.getUser("cheng-peng")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(User user) {
                        assertEquals("程小鹏。", user.name);
                        assertEquals("www.cxpblog.com", user.blog);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Test", e.toString());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

}

