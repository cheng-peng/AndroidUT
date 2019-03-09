package com.cxp.androidut.mockito;

import com.cxp.androidut.bean.Person;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.inOrder;

/**
 * 文 件 名: MockitoInOrderTest
 * 创 建 人: CXP
 * 创建日期: 2019-03-09 17:23
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public class MockitoInOrderTest {

    @Mock
    Person mPerson;

    @Mock
    Person mPerson1;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void testPersonInOrder(){

        mPerson.setName("小明");
        mPerson.setSex(1);

        mPerson1.setName("小红");
        mPerson1.setSex(0);

        InOrder mInOrder = inOrder(mPerson, mPerson1);
        //执行顺序正确
        mInOrder.verify(mPerson).setName("小明");
        mInOrder.verify(mPerson).setSex(1);

        //执行顺序错误
//        mInOrder.verify(mPerson1).setSex(0);
//        mInOrder.verify(mPerson1).setName("小红");

    }

}
