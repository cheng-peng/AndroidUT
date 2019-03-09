package com.cxp.androidut.junit;

import com.cxp.androidut.utils.DateUtil;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.text.ParseException;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


/**
 * 文 件 名: DateUtilTest
 * 创 建 人: CXP
 * 创建日期: 2019-03-09 14:42
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public class DateUtilTest {


    private String time = "2019-01-01 12:12:12";

    private long timeStamp = 1546315932000L;

    private Date mDate;

    @Before
    public void begin() {
        System.out.println("测试开始！");
        mDate = new Date();
        mDate.setTime(timeStamp);
    }

    @After
    public void end()  {
        System.out.println("测试结束！");
    }

    @Test
    public void stampToDateTest()   {
        assertEquals("预期时间", DateUtil.stampToDate(timeStamp));
    }

    @Test
    public void dateToStampTest() throws Exception {
        assertNotEquals(4, DateUtil.dateToStamp(time));
    }

    /**
     * expected = ParseException.class
     * 如果异常则执行成功，没有异常则执行失败
     */
    @Test(expected = ParseException.class)
    public void dateToStampTest1() throws Exception{
        DateUtil.dateToStamp("2019-01-01");
    }

    //忽略此方法
    @Test
    @Ignore("test方法不执行\n")
    public void test() {
        System.out.println("-----");
    }

    @Test
    public void testDate()  throws Exception{
        System.out.println(DateUtil.dateToStamp("2019-01-01 12:12:12"));
    }




}
