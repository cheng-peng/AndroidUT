package com.cxp.androidut.mvp.ui;

import com.cxp.androidut.mvp.base.MvpView;

/**
 * 文 件 名: LoginView
 * 创 建 人: CXP
 * 创建日期: 2019-03-12 8:35
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public interface LoginView extends MvpView {

    /**
     * 倒计时完成
     */
    void countdownComplete();

    /**
     * 倒计时中
     * @param time 剩余时间
     */
    void countdownNext(String time);

    /**
     * 登录成功
     */
    void loginSuccess();
}
