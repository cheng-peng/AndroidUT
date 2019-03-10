package com.cxp.androidut.powermock;

import com.cxp.androidut.bean.Banana;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.rule.PowerMockRule;

/**
 * 文 件 名: PowerMockitoNewClassTest
 * 创 建 人: CXP
 * 创建日期: 2019-03-09 23:53
 * 描    述: mock new方法
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
//用于忽略mock的类，防止报错
@PowerMockIgnore({ "javax.management.*","org.mockito.*", "org.robolectric.*", "android.*" })
public class PowerMockitoNewClassTest {

    @Rule
    public PowerMockRule rule = new PowerMockRule();

    @Test
    @PrepareForTest({Banana.class})
    public void testNewClass() throws Exception {
        Banana mBanana = PowerMockito.mock(Banana.class);
        PowerMockito.when(mBanana.getBananaInfo()).thenReturn("大香蕉");
        //如果new新对象，则返回这个上面设置的这个对象
        PowerMockito.whenNew(Banana.class).withNoArguments().thenReturn(mBanana);
        //new新的对象
        Banana newBanana = new Banana();
        Assert.assertEquals("大香蕉", newBanana.getBananaInfo());
    }
}
