package com.cxp.androidut.mvp;

import android.app.Application;
import android.widget.EditText;
import android.widget.TextView;

import com.cxp.androidut.BuildConfig;
import com.cxp.androidut.R;
import com.cxp.androidut.mvp.ui.LoginMvpActivity;
import com.cxp.androidut.rxjava.RxJavaTestSchedulerRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;
import org.robolectric.shadows.ShadowProgressDialog;
import org.robolectric.shadows.ShadowToast;

import java.util.concurrent.TimeUnit;

import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.functions.Function;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * 文 件 名: LoginMvpActivityTest
 * 创 建 人: CXP
 * 创建日期: 2019-03-12 8:41
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 23)
public class LoginMvpActivityTest {

    private LoginMvpActivity loginActivity;
    private TextView mTvSendIdentify;
    private TextView mTvLogin;
    private EditText mEtMobile;
    private EditText mEtIdentify;

    @Rule
    public RxJavaTestSchedulerRule rule = new RxJavaTestSchedulerRule();

    @Before
    public void setUp() {
        ShadowLog.stream = System.out;
        loginActivity = Robolectric.setupActivity(LoginMvpActivity.class);
        mTvSendIdentify = loginActivity.findViewById(R.id.tv_send_identify);
        mTvLogin = loginActivity.findViewById(R.id.tv_login);
        mEtMobile = loginActivity.findViewById(R.id.et_mobile);
        mEtIdentify = loginActivity.findViewById(R.id.et_identify);
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

    @Test
    public void testLogin() {

        mEtMobile.setText("123");
        mEtIdentify.setText("123");
        mTvLogin.performClick();
        assertEquals("手机号码不正确", ShadowToast.getTextOfLatestToast());

        mEtMobile.setText("13000000000");
        mEtIdentify.setText("123");
        mTvLogin.performClick();
        assertEquals("验证码不正确", ShadowToast.getTextOfLatestToast());

        initRxJava();

        mEtMobile.setText("13000000000");
        mEtIdentify.setText("123456");
        mTvLogin.performClick();

        // 判断ProgressDialog弹出
        assertNotNull(ShadowProgressDialog.getLatestDialog());
        assertEquals("登录成功", ShadowToast.getTextOfLatestToast());
    }

    private void initRxJava() {
        RxJavaPlugins.reset();
        RxJavaPlugins.setIoSchedulerHandler(new Function<Scheduler, Scheduler>() {
            @Override
            public Scheduler apply(Scheduler scheduler) throws Exception {
                return Schedulers.trampoline();
            }
        });
        RxAndroidPlugins.reset();
        RxAndroidPlugins.setMainThreadSchedulerHandler(new Function<Scheduler, Scheduler>() {
            @Override
            public Scheduler apply(Scheduler scheduler) throws Exception {
                return Schedulers.trampoline();
            }
        });
    }
}
