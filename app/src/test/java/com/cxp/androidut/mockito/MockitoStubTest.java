package com.cxp.androidut.mockito;

import com.cxp.androidut.bean.Person;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.stubbing.Answer;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

/**
 * 文 件 名: MockitoStubTest
 * 创 建 人: CXP
 * 创建日期: 2019-03-09 16:08
 * 描    述: 常用打桩方法示例
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public class MockitoStubTest {

    @Mock
    Person mPerson;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void testPersonReturn() {
        when(mPerson.getName()).thenReturn("小明");
        when(mPerson.getSex()).thenThrow(new NullPointerException("滑稽：性别不明"));

        // 输出"小明"
        System.out.println(mPerson.getName());

        doReturn("小小").when(mPerson).getName();
        // 输出"小小"
        System.out.println(mPerson.getName());

        // 抛出异常
//        System.out.println(mPerson.getSex());

    }

    @Test
    public void testPersonRealMethod() {
        doCallRealMethod().when(mPerson).getAge();
        //调用Person的真实getAge()方法
        System.out.println(mPerson.getAge());
    }

    @Test
    public void testPersonNothing() {
        doNothing().doThrow(new RuntimeException()).when(mPerson).setSex(1);
        //第一次什么都不做
        mPerson.setSex(1);
        //异常抛出在下面这行
//        mPerson.setSex(1);
    }

    @Test
    public void testPersonAnswer() {
        when(mPerson.eat(anyString())).thenAnswer(new Answer() {
            @Override
            public String answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                return args[0].toString() + "真好吃";
            }
        });
        //输出：饺子真好吃
        System.out.println(mPerson.eat("饺子"));
    }

}
