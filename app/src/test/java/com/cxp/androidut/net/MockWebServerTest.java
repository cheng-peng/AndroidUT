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

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.Assert.assertEquals;

/**
 * 文 件 名: MockWebServerTest
 * 创 建 人: CXP
 * 创建日期: 2019-03-11 8:06
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 23)
public class MockWebServerTest {

    private GithubApi mockGithubService;
    private MockWebServer server;

    @Rule
    public RxJavaRule rule = new RxJavaRule();

    @Before
    public void setUp(){
        ShadowLog.stream = System.out;

        // 创建一个 MockWebServer
        server = new MockWebServer();

        Dispatcher dispatcher = new Dispatcher() {

            @Override
            public MockResponse dispatch(RecordedRequest request) throws InterruptedException {

                if (request.getPath().equals("/users/cheng-peng")){
                    return new MockResponse()
                            .addHeader("Content-Type", "application/json;charset=utf-8")
                            .addHeader("Cache-Control", "no-cache")
                            .setBody("{\"id\": 123456, " +
                                    " \"name\": \"程鹏\"," +
                                    " \"blog\": \"www.cxpblog.com\"}");
                } else {
                    return new MockResponse()
                            .addHeader("Content-Type", "application/json;charset=utf-8")
                            .setResponseCode(404)
                            .throttleBody(5, 1, TimeUnit.SECONDS) //一秒传递5个字节
                            .setBody("{\"error\": \"网络异常\"}");
                }
            }
        };

        server.setDispatcher(dispatcher);

        //默认返回http code是 200
//        MockResponse mockResponse = new MockResponse()
//                .addHeader("Content-Type", "application/json;charset=utf-8")
//                .addHeader("Cache-Control", "no-cache")
//                .setBody("{\"id\": 123456, " +
//                         " \"name\": \"程鹏\"," +
//                         " \"blog\": \"www.cxpblog.com\"}");
//
//        MockResponse mockResponse1 = new MockResponse()
//                .addHeader("Content-Type", "application/json;charset=utf-8")
//                .setResponseCode(404)
//                .throttleBody(5, 1, TimeUnit.SECONDS) //一秒传递5个字节
//                .setBody("{\"error\": \"网络异常\"}");
//
//        server.enqueue(mockResponse); //成功响应
//        server.enqueue(mockResponse1);//失败响应

        //定义Http Client,并添加拦截器
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new LoggingInterceptor())
                .build();

        //设置Http Client
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://" + server.getHostName() + ":" + server.getPort() + "/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        mockGithubService = retrofit.create(GithubApi.class);

    }

    @Test
    public void getUserTest() throws Exception {

        mockGithubService.getUser("cheng-peng")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(User user) {
                        assertEquals("程鹏", user.name);
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

        //验证我们的请求客户端是否按预期生成了请求
        RecordedRequest request = server.takeRequest();
        assertEquals("GET /users/cheng-peng HTTP/1.1", request.getRequestLine());
        assertEquals("okhttp/3.9.1", request.getHeader("User-Agent"));

        // 关闭服务
        server.shutdown();
    }
}
