package com.cxp.androidut.mockito;

import com.cxp.androidut.bean.Person;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;

/**
 * 文 件 名: MockitoJUnitRunnerTest
 * 创 建 人: CXP
 * 创建日期: 2019-03-09 15:59
 * 描    述: 运行器Mock
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
@RunWith(MockitoJUnitRunner.class) //<--使用MockitoJUnitRunner
public class MockitoJUnitRunnerTest {

    @Mock //<--使用@Mock注解
    Person mPerson;

    @Test
    public void testIsNotNull(){
        assertNotNull(mPerson);
    }
}
