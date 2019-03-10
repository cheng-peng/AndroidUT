package com.cxp.androidut.powermock;

import com.cxp.androidut.bean.Banana;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * 文 件 名: PowerMockitoFinalMethodTest
 * 创 建 人: CXP
 * 创建日期: 2019-03-09 23:52
 * 描    述: mock final方法
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
@RunWith(PowerMockRunner.class)
public class PowerMockitoFinalMethodTest {

    @Test
    @PrepareForTest({Banana.class})
    public void testFinalMethod()  {
        Banana mBanana = PowerMockito.mock(Banana.class);
        PowerMockito.when(mBanana.isLike()).thenReturn(false);
        Assert.assertFalse(mBanana.isLike());
    }
}