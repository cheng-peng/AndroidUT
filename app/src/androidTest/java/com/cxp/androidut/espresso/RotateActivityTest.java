package com.cxp.androidut.espresso;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.cxp.androidut.R;
import com.cxp.androidut.espresso.rotate.RotateActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * 文 件 名: RotateActivityTest
 * 创 建 人: CXP
 * 创建日期: 2019-03-12 13:30
 * 描    述: 屏幕旋转处理test类
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
@RunWith(AndroidJUnit4.class)
public class RotateActivityTest {

    @Rule
    public ActivityTestRule<RotateActivity> activityRule = new ActivityTestRule<>(RotateActivity.class);

    /**
     * 每次测试都会先执行setUp()中的代码，在这里我们每次都检查初始化是否TextView内容为0
     */
    @Before
    public void setUp(){
        onView(withId(R.id.count))
                .check(matches(withText("0")));
    }

    /**
     * 用户点击button，count+=1
     */
    @Test
    public void buttonClickTest(){
        onView(withId(R.id.increment_button))
                .perform(click());

        onView(withId(R.id.count))
                .check(matches(withText("1")));
    }

    /**
     * 用户点击两次button，并旋转屏幕
     */
    @Test
    public void click2TimesAndRotate() throws Exception {
        onView(withId(R.id.increment_button))
                .perform(click())
                .perform(click());

        onView(withId(R.id.count))
                .check(matches(withText("2")));

        rotateScreen();

        onView(withId(R.id.count))
                .check(matches(withText("2")));

    }

    /**
     * 不点击屏幕直接旋转屏幕
     */
    @Test
    public void noClickRotateScreen() {
        rotateScreen();
        onView(withId(R.id.count))
                .check(matches(withText("0")));
    }


    /**
     * 旋转屏幕
     */
    private void rotateScreen() {
        //获取当前屏幕的旋转方向
        Context context = InstrumentationRegistry.getTargetContext();
        int orientation = context.getResources().getConfiguration().orientation;

        //旋转屏幕
        activityRule.getActivity().setRequestedOrientation(
                (orientation == Configuration.ORIENTATION_LANDSCAPE) ?
                        ActivityInfo.SCREEN_ORIENTATION_PORTRAIT :
                        ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }
}
