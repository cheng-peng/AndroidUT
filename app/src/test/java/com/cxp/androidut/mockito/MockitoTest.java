package com.cxp.androidut.mockito;


import com.cxp.androidut.bean.Person;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

/**
 * 文 件 名: MockitoTest
 * 创 建 人: CXP
 * 创建日期: 2019-03-09 15:55
 * 描    述: 普通方法
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public class MockitoTest {

    @Test
    public void testIsNotNull(){
        Person mPerson = mock(Person.class); //<--使用mock方法

        assertNotNull(mPerson);
    }
}
