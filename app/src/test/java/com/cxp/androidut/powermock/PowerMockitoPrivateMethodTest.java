package com.cxp.androidut.powermock;

import com.cxp.androidut.bean.Banana;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.api.support.membermodification.MemberModifier;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * 文 件 名: PowerMockitoPrivateMethodTest
 * 创 建 人: CXP
 * 创建日期: 2019-03-09 23:50
 * 描    述: mock私有方法
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({Banana.class})
public class PowerMockitoPrivateMethodTest {

    @Test
    public void testPrivateMethod() throws Exception {
        Banana mBanana = PowerMockito.mock(Banana.class);
        PowerMockito.when(mBanana.getBananaInfo()).thenCallRealMethod();
        PowerMockito.when(mBanana, "flavor").thenReturn("苦苦的");
        Assert.assertEquals("苦苦的黄色的", mBanana.getBananaInfo());
        //验证flavor是否调用了一次
        PowerMockito.verifyPrivate(mBanana).invoke("flavor");
    }

    /**
     * 跳过flavor方法
     */
    @Test
    public void skipPrivateMethod() {
        Banana mBanana = new Banana();
        //跳过flavor方法
        PowerMockito.suppress(PowerMockito.method(Banana.class, "flavor"));
        Assert.assertEquals("null黄色的", mBanana.getBananaInfo());
    }

    /**
     * 更改父类私有变量
     */
    @Test
    public void testChangeParentPrivate() throws Exception {
        Banana mBanana = new Banana();
        MemberModifier.field(Banana.class, "fruit").set(mBanana, "蔬菜");
        Assert.assertEquals("蔬菜", mBanana.getFruit());
    }

}
