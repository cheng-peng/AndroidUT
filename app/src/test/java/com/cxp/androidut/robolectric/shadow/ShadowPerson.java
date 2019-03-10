package com.cxp.androidut.robolectric.shadow;

import com.cxp.androidut.bean.Person;

import org.robolectric.annotation.Implementation;
import org.robolectric.annotation.Implements;

/**
 * 文 件 名: ShadowPerson
 * 创 建 人: CXP
 * 创建日期: 2019-03-10 22:36
 * 描    述: 自定义Shadow
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
@Implements(Person.class)
public class ShadowPerson {

    @Implementation
    public String getName() {
        return "AndroidUT";
    }

    @Implementation
    public int getSex() {
        return 0;
    }

    @Implementation
    public int getAge(){
        return 18;
    }
}
