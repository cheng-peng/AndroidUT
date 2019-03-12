package com.cxp.androidut.utils;

import android.support.test.espresso.IdlingResource;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 文 件 名: SimpleCountingIdlingResource
 * 创 建 人: CXP
 * 创建日期: 2019-03-12 12:45
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public final class SimpleCountingIdlingResource implements IdlingResource {

    private final String mResourceName;

    ////这个counter值就像一个标记，默认为0
    private final AtomicInteger counter = new AtomicInteger(0);

    // written from main thread, read from any thread.
    private volatile ResourceCallback resourceCallback;

    /**
     * Creates a SimpleCountingIdlingResource
     *
     * @param resourceName the resource name this resource should report to Espresso.
     */
    public SimpleCountingIdlingResource(String resourceName) {
        mResourceName = resourceName;
    }

    /**
     * 用来标识 IdlingResource 名称
     */
    @Override
    public String getName() {
        return mResourceName;
    }

    /**
     * 当前 IdlingResource 是否空闲
     */
    @Override
    public boolean isIdleNow() {
        return counter.get() == 0;
    }

    /**
     * 注册一个空闲状态变换的ResourceCallback回调
     */
    @Override
    public void registerIdleTransitionCallback(ResourceCallback resourceCallback) {
        this.resourceCallback = resourceCallback;
    }

    /**
     * 每当我们开始异步请求，把counter值+1
     */
    public void increment() {
        counter.getAndIncrement();
    }

    /**
     * 当我们获取到网络数据后，counter值-1；
     */
    public void decrement() {
        int counterVal = counter.decrementAndGet();
        if (counterVal == 0) {
            //如果这时counter == 0，说明异步结束，执行回调。
            if (null != resourceCallback) {
                resourceCallback.onTransitionToIdle();
            }
        }

        //如果小于0，抛出异常
        if (counterVal < 0) {
            throw new IllegalArgumentException("Counter has been corrupted!");
        }
    }
}

