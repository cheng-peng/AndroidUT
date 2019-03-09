package com.cxp.androidut.mockito;

import com.cxp.androidut.bean.Home;
import com.cxp.androidut.bean.Person;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * 文 件 名: MockitoSpyTest
 * 创 建 人: CXP
 * 创建日期: 2019-03-09 17:15
 * 描    述: Spy示例
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public class MockitoSpyTest {

    @Spy
    Person mPerson;

    @InjectMocks
    Home mHome;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void testIsNotNull(){
        assertNotNull(mPerson);
    }

    @Test
    public void testPersonSpy(){
        //输出11
        System.out.println(mPerson.getAge());
    }

    @Test
    public void testHomeInjectMocks(){
        when(mPerson.getName()).thenReturn("weilu");
        System.out.println(mHome.getMaster());
    }
}
