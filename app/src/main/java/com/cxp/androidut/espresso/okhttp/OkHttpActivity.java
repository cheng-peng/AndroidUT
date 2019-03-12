package com.cxp.androidut.espresso.okhttp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.cxp.androidut.R;
import com.cxp.androidut.bean.User;
import com.cxp.androidut.net.GithubService;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * 文 件 名: OkHttpActivity
 * 创 建 人: CXP
 * 创建日期: 2019-03-12 14:03
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public class OkHttpActivity extends AppCompatActivity {

    TextView tvName;
    private GitHubService service;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);

        tvName=findViewById(R.id.tv_name);

        initHttp();
        requestHttp();
//        requestRxHttp();

    }

    private void initHttp() {
        service = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(OkHttpProvider.getOkHttpInstance())
                .build()
                .create(GitHubService.class);
    }

    private void requestRxHttp() {
        GithubService.createGithubService()
                .getUser("cheng-peng")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(User user) {
                        tvName.setText(user.name);
                    }

                    @Override
                    public void onError(Throwable e) {
                        tvName.setText( e.toString());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }



    private void requestHttp() {
        service.getUser("cheng-peng").enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                tvName.setText(user.name);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                tvName.setText(t.getMessage());
            }
        });

    }

    public void clickLis(View view){

    }
}
