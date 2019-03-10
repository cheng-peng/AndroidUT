package com.cxp.androidut.robolectric.service;

import com.cxp.androidut.BuildConfig;
import com.cxp.androidut.service.MyService;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ServiceController;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

/**
 * 文 件 名: MyServiceTest
 * 创 建 人: CXP
 * 创建日期: 2019-03-10 22:28
 * 描    述: 自定义服务测试
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 23)
public class MyServiceTest {

    private ServiceController<MyService> controller;
    private MyService mService;

    @Before
    public void setUp()  {
        ShadowLog.stream = System.out;
        controller = Robolectric.buildService(MyService.class);
        mService = controller.get();
    }

    /**
     * 控制Service生命周期进行验证
     */
    @Test
    public void testServiceLifecycle() {
        controller.create();
        controller.startCommand(0, 0);
        controller.bind();
        controller.unbind();
        controller.destroy();

        Assert.assertEquals("MyService",mService.getClass().getSimpleName());
    }
}
