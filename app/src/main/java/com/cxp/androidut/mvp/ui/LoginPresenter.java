package com.cxp.androidut.mvp.ui;

import com.cxp.androidut.bean.User;
import com.cxp.androidut.mvp.base.BasePresenter;
import com.cxp.androidut.net.GithubService;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * 文 件 名: LoginPresenter
 * 创 建 人: CXP
 * 创建日期: 2019-03-12 8:36
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public class LoginPresenter extends BasePresenter<LoginView> {

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public void getIdentify() {
        // interval隔一秒发一次，到120结束
        Disposable mDisposable = Observable
                .interval(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .take(120)
                .subscribeWith(new DisposableObserver<Long>() {
                    @Override
                    public void onComplete() {
                        mMvpView.countdownComplete();
                    }
                    @Override
                    public void onError(Throwable e) {
                        mMvpView.showToast("倒计时出现错误！");
                    }

                    @Override
                    public void onNext(Long aLong) {
                        mMvpView.countdownNext(String.valueOf(Math.abs(aLong - 120)));
                    }
                });
        mCompositeDisposable.add(mDisposable);
    }

    public void login(String mobile, String code) {
        if(mobile.length() != 11){
            mMvpView.showToast("手机号码不正确");
            return;
        }
        if(code.length() != 6){
            mMvpView.showToast("验证码不正确");
            return;
        }

        GithubService.createGithubService()
                .getUser("cheng-peng")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        if (isViewAttached()){
                            mMvpView.showProgress();
                        }

                    }
                })
                .doAfterTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (isViewAttached()){
                            mMvpView.closeProgress();
                        }
                    }
                })
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(User user) {
                        mMvpView.showToast("登录成功");
                        mMvpView.loginSuccess();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mMvpView.showToast("登录失败");
                    }

                    @Override
                    public void onComplete() {}
                });
    }

    @Override
    public void detachView(){
        super.detachView();
        mCompositeDisposable.clear();
    }

}