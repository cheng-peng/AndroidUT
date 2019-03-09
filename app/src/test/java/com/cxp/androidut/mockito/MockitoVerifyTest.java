package com.cxp.androidut.mockito;

import com.cxp.androidut.bean.Person;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.after;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * 文 件 名: MockitoVerifyTest
 * 创 建 人: CXP
 * 创建日期: 2019-03-09 16:54
 * 描    述: 常用验证方法示例
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public class MockitoVerifyTest {

    @Mock
    Person mPerson;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void testPersonVerifyAfter(){
        when(mPerson.getAge()).thenReturn(18);

        System.out.println(mPerson.getAge());
        System.out.println(System.currentTimeMillis());
        //延时1s验证
        verify(mPerson, after(1000)).getAge();
        System.out.println(System.currentTimeMillis());
        // 抛出异常
//        verify(mPerson, atLeast(2)).getAge();
    }


    @Test
    public void testPersonVerifyAtLeast(){
        mPerson.getAge();
        mPerson.getAge();
        //至少验证2次
        verify(mPerson, atLeast(2)).getAge();
    }

    @Test
    public void testPersonVerifyAtMost(){
        mPerson.getAge();
        //至多验证2次
        verify(mPerson, atMost(2)).getAge();
    }

    @Test
    public void testPersonVerifyTimes(){
        mPerson.getAge();
        mPerson.getAge();

//        verify(mPerson, after(1000)).getAge();

        //验证方法在100ms超时前调用2次
        verify(mPerson, timeout(100).times(2)).getAge();
        reset(mPerson);
    }

}
