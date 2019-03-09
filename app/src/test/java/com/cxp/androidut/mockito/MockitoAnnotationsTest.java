package com.cxp.androidut.mockito;

import com.cxp.androidut.bean.Person;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertNotNull;

/**
 * 文 件 名: MockitoAnnotationsTest
 * 创 建 人: CXP
 * 创建日期: 2019-03-09 15:57
 * 描    述: 注解方法Mock
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public class MockitoAnnotationsTest {

    @Mock //<--使用@Mock注解
    Person mPerson;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this); //<--初始化
    }

    @Test
    public void testIsNotNull(){
        assertNotNull(mPerson);
    }

}
