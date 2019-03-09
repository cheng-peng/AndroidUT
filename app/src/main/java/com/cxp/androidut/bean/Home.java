package com.cxp.androidut.bean;

/**
 * 文 件 名: Home
 * 创 建 人: CXP
 * 创建日期: 2019-03-09 17:15
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public class Home {

    private Person mPerson;

    public Home(Person person) {
        mPerson = person;
    }

    public String getMaster(){
        return mPerson.getName();
    }
}
