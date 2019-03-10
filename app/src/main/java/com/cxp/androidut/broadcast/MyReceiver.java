package com.cxp.androidut.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * 文 件 名: MyReceiver
 * 创 建 人: CXP
 * 创建日期: 2019-03-10 22:19
 * 描    述: 自定义广播接收器
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public class MyReceiver extends BroadcastReceiver {

    public static final String NAME = "name";

    @Override
    public void onReceive(Context context, Intent intent) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        String name = intent.getStringExtra(NAME);
        editor.putString(NAME, name);
        editor.apply();
    }
}
