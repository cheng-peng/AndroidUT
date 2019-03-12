package com.cxp.androidut.espresso;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.cxp.androidut.R;
import com.cxp.androidut.espresso.async.AsyncActivity;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * 文 件 名: AsyncActivityTest
 * 创 建 人: CXP
 * 创建日期: 2019-03-12 12:42
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
@RunWith(AndroidJUnit4.class)
public class AsyncActivityTest {
    @Rule
    public ActivityTestRule<AsyncActivity> activityRule = new ActivityTestRule<>(AsyncActivity.class);


    @Test
    public void loadImage() {

        onView(withId(R.id.btn01))
                .check(matches(withText("网络请求图片")));

        //点击按钮，加载图片
        onView(withId(R.id.btn01))
                .perform(click());

        Espresso.registerIdlingResources(
                activityRule.getActivity().getCountingIdlingResource());

        onView(withId(R.id.btn01))
                .check(matches(withText("success!")));
    }

    @After
    public void unregisterIdlingResource() {
        Espresso.unregisterIdlingResources(
                activityRule.getActivity().getCountingIdlingResource());
    }
}
