package com.cxp.androidut.mvp.base;

import android.content.Context;

/**
 * 文 件 名: MvpView
 * 创 建 人: CXP
 * 创建日期: 2019-03-12 8:34
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public interface MvpView {

    /***
     * 获取Context
     * @return Context
     */
    Context getContext();

    /***
     * 显示Progress
     */
    void showProgress();

    /***
     * 关闭Progress
     */
    void closeProgress();

    /***
     * @param string 消息内容
     */
    void showToast(String string);
}
