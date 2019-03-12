package com.cxp.androidut.espresso;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.cxp.androidut.R;
import com.cxp.androidut.espresso.okhttp.OkHttpActivity;
import com.cxp.androidut.espresso.okhttp.OkHttpProvider;
import com.jakewharton.espresso.OkHttp3IdlingResource;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * 文 件 名: OkHttpActivityTest
 * 创 建 人: CXP
 * 创建日期: 2019-03-12 14:12
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
@RunWith(AndroidJUnit4.class)
public class OkHttpActivityTest {

    @Rule
    public ActivityTestRule<OkHttpActivity> rule = new ActivityTestRule<>(OkHttpActivity.class);

    @Test
    public void requestHttpTest() {
        OkHttp3IdlingResource idlingResource = OkHttp3IdlingResource.create("okhttp", OkHttpProvider.getOkHttpInstance());

        Espresso.registerIdlingResources(idlingResource);

        onView(withId(R.id.tv_name))
                .check(matches(withText("程小鹏。")));

        //解除注册
        Espresso.unregisterIdlingResources(idlingResource);
    }
}
