package com.cxp.androidut.powermock;

import com.cxp.androidut.bean.Banana;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

/**
 * 文 件 名: PowerMockitoStaticMethodTest
 * 创 建 人: CXP
 * 创建日期: 2019-03-09 23:47
 * 描    述: mock静态方法
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({Banana.class})
@PowerMockIgnore({ "javax.management.*","org.mockito.*", "org.robolectric.*", "android.*" })
public class PowerMockitoStaticMethodTest {

    @Test
    public void testStaticMethod() {
        PowerMockito.mockStatic(Banana.class); //<-- mock静态类
        Mockito.when(Banana.getColor()).thenReturn("绿色");
        Assert.assertEquals("绿色", Banana.getColor());
    }

    /**
     * 更改类的私有static常量
     */
    @Test
    public void testChangeColor() {
        Whitebox.setInternalState(Banana.class, "COLOR", "红色的");
        Assert.assertEquals("红色的", Banana.getColor());
    }
}
