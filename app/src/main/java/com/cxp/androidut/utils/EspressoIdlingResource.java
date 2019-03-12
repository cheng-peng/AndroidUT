package com.cxp.androidut.utils;

/**
 * 文 件 名: EspressoIdlingResource
 * 创 建 人: CXP
 * 创建日期: 2019-03-12 12:45
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public class EspressoIdlingResource {
    private static final String RESOURCE = "GLOBAL";

    private static SimpleCountingIdlingResource mCountingIdlingResource =
            new SimpleCountingIdlingResource(RESOURCE);

    public static void increment() {
        mCountingIdlingResource.increment();
    }

    public static void decrement() {
        mCountingIdlingResource.decrement();
    }

    public static SimpleCountingIdlingResource getIdlingResource() {
        return mCountingIdlingResource;
    }
}
