package com.cxp.androidut.mockito;

import com.cxp.androidut.bean.Person;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.assertNotNull;

/**
 * 文 件 名: MockitoRuleTest
 * 创 建 人: CXP
 * 创建日期: 2019-03-09 16:00
 * 描    述: MockitoRule方式Mock
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public class MockitoRuleTest {

    @Mock
    Person mPerson;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void testIsNotNull(){
        assertNotNull(mPerson);
    }

}
