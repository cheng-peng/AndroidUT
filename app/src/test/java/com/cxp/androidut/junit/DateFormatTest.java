package com.cxp.androidut.junit;


import com.cxp.androidut.utils.DateUtil;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Collection;

/**
 * 文 件 名: DateFormatTest
 * 创 建 人: CXP
 * 创建日期: 2019-03-09 15:43
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
//批量测试失败
@RunWith(Parameterized.class)
public class DateFormatTest {

    private String time;

    public DateFormatTest(String time) {
        this.time = time;
    }

    @Parameterized.Parameters
    public static Collection primeNumbers() {
        return Arrays.asList("2019-01-01",
//                "2019-01-01 12:12:12", // 抛出异常
                "2019年01月01日 12时12分12秒");
    }

    @Test(expected = ParseException.class)
    public void dateToStampTest1() throws Exception{
        DateUtil.dateToStamp(time);
    }
}
