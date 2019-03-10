package com.cxp.androidut.robolectric.broadcast;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.cxp.androidut.BuildConfig;
import com.cxp.androidut.broadcast.MyReceiver;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowApplication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * 文 件 名: MyReceiverTest
 * 创 建 人: CXP
 * 创建日期: 2019-03-10 22:20
 * 描    述: 广播测试
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 23)
public class MyReceiverTest {

    private final String action = "com.cxp.androidut";

    @Test
    public void testRegister()  {
        ShadowApplication shadowApplication = ShadowApplication.getInstance();
        Intent intent = new Intent(action);
        // 验证是否注册了相应的Receiver
        assertTrue(shadowApplication.hasReceiverForIntent(intent));
    }

    @Test
    public void testReceive() {
        //发送广播
        Intent intent = new Intent(action);
        intent.putExtra(MyReceiver.NAME, "AndroidUT");
        MyReceiver myReceiver = new MyReceiver();
        myReceiver.onReceive(RuntimeEnvironment.application, intent);
        //验证广播的处理逻辑是否正确
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(RuntimeEnvironment.application);
        assertEquals( "AndroidUT", preferences.getString(MyReceiver.NAME, ""));
    }
}
