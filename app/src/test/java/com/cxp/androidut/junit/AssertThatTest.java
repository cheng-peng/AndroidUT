package com.cxp.androidut.junit;

import org.junit.Rule;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.both;
import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * 文 件 名: AssertThatTest
 * 创 建 人: CXP
 * 创建日期: 2019-03-09 15:46
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public class AssertThatTest {

    @Rule
    public MyRule rule = new MyRule();

    @Test
    public void testMobilePhone()  {
        assertThat("13588888888", new IsMobilePhoneMatcher());
    }

    @Test
    public void testAssertThat1() {
        assertThat(6, is(6));
    }

    @Test
    public void testAssertThat2()  {
        assertThat(null, nullValue());
    }

    @Test
    public void testAssertThat3() {
        assertThat("Hello UT", both(startsWith("Hello")).and(endsWith("CXP")));
    }

}
