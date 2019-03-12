package com.cxp.androidut.espresso;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.cxp.androidut.R;
import com.cxp.androidut.espresso.async2.AsyncActivity2;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * 文 件 名: AsyncActivity2Test
 * 创 建 人: CXP
 * 创建日期: 2019-03-12 13:54
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
@RunWith(AndroidJUnit4.class)
public class AsyncActivity2Test {

    @Rule
    public ActivityTestRule<AsyncActivity2> activityRule = new ActivityTestRule<>(AsyncActivity2.class);

    private IdlingResource idlingresource;


    @Before
    public void setUp() {
        idlingresource = activityRule.getActivity().getIdlingresource();

        //去掉下行注释，只有异步结束后，TextView处于显示状态，才进行接下来的测试代码（tests passed）
        Espresso.registerIdlingResources(idlingresource);
    }

    @Test
    public void onLoadingFinished(){

        // 未注册idlingResource时，立即进行test，此时异步并未结束，报错（tests failed）
        onView(withId(R.id.text))
                .check(matches(withText("done")));
    }

    @After
    public void release(){
        Espresso.unregisterIdlingResources(idlingresource);
    }

}
