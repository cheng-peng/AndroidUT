package com.cxp.androidut.mvp.base;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * 文 件 名: BasePresenter
 * 创 建 人: CXP
 * 创建日期: 2019-03-12 8:33
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public abstract class BasePresenter<T extends MvpView> {

    /**
     * View接口类型的弱引用
     */
    private Reference<T> mViewRef;

    protected T mMvpView;

    /**
     * 建立关联
     */
    public void attachView(T view){
        mViewRef = new WeakReference<>(view);
        if(isViewAttached()) {
            mMvpView = getView();
        }
    }

    /**
     * 获取View
     * @return View
     */
    public T getView(){
        return mViewRef.get();
    }

    /**
     * UI展示相关的操作需要判断一下 Activity 是否已经 finish.
     * <p>
     * todo : 只有当 isActivityAlive 返回true时才可以执行与Activity相关的操作,
     * 比如 弹出Dialog、Window、跳转Activity等操作.
     *
     * @return boolean
     */
    public boolean isViewAttached(){
        return mViewRef != null && mViewRef.get() != null;
    }

    /**
     * 解除关联
     */
    public void detachView(){
        if( mViewRef != null){
            mViewRef.clear();
            mViewRef = null;
        }
    }
}

