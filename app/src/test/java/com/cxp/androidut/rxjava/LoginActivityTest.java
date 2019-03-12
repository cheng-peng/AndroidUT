package com.cxp.androidut.rxjava;

import android.app.Application;
import android.widget.TextView;

import com.cxp.androidut.BuildConfig;
import com.cxp.androidut.R;
import com.cxp.androidut.ui.LoginActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * 文 件 名: LoginMvpActivityTest
 * 创 建 人: CXP
 * 创建日期: 2019-03-11 14:00
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 23)
public class LoginActivityTest {

    private LoginActivity loginActivity;
    private TextView mTvSendIdentify;

    @Rule
    public RxJavaTestSchedulerRule rule = new RxJavaTestSchedulerRule();

    @Before
    public void setUp() {
        loginActivity = Robolectric.setupActivity(LoginActivity.class);
        mTvSendIdentify = loginActivity.findViewById(R.id.tv_send_identify);
    }

    @Test
    public void testLoginActivity() {
        assertNotNull(loginActivity);
    }

    @Test
    public void testGetIdentify() {
        Application application = RuntimeEnvironment.application;
        assertEquals(mTvSendIdentify.getText().toString(),
                application.getString(R.string.login_send_identify));

        // 触发按钮点击
        mTvSendIdentify.performClick();
        // 时间到10秒
        rule.getTestScheduler().advanceTimeTo(10, TimeUnit.SECONDS);
        assertEquals(mTvSendIdentify.isEnabled(), false);
        assertEquals(mTvSendIdentify.getText().toString(), "111秒后重试");

        // 时间到120秒
        rule.getTestScheduler().advanceTimeTo(120, TimeUnit.SECONDS);

        assertEquals(mTvSendIdentify.getText().toString(),
                application.getString(R.string.login_send_identify));
        assertEquals(mTvSendIdentify.isEnabled(), true);
    }
}
