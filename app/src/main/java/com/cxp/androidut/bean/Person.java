package com.cxp.androidut.bean;

/**
 * 文 件 名: Person
 * 创 建 人: CXP
 * 创建日期: 2019-03-09 15:56
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public class Person {
    private String name;
    private int sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getAge() {
        return 11;
    }

    public String eat(String food) {
        return food;
    }
}
