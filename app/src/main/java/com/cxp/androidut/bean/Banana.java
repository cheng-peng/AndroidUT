package com.cxp.androidut.bean;

/**
 * 文 件 名: Banana
 * 创 建 人: CXP
 * 创建日期: 2019-03-09 23:45
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public class Banana extends Fruit {

    private static String COLOR = "黄色的";

    public Banana() {}

    public static String getColor() {
        return COLOR;
    }

    public String getBananaInfo() {
        return flavor() + getColor();
    }

    private String flavor() {
        return "甜甜的";
    }

    public final boolean isLike() {
        return true;
    }
}
