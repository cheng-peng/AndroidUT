package com.cxp.androidut.espresso;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.cxp.androidut.R;
import com.cxp.androidut.espresso.list.ListActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.not;

/**
 * 文 件 名: ListActivityTest
 * 创 建 人: CXP
 * 创建日期: 2019-03-12 13:11
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
@RunWith(AndroidJUnit4.class)
public class ListActivityTest {
    @Rule
    public ActivityTestRule<ListActivity> activityTestRule = new ActivityTestRule<>(ListActivity.class);

    @Before
    public void setUp()  {

    }

    @Test
    public void listClickTest() {
        //默认不可见
        onView(withId(R.id.text_view))
                .check(matches(not(isDisplayed())));

        //先滑动到item15，然后点击item8
        onView(withId(R.id.recycler_view))
                .perform(RecyclerViewActions.scrollToPosition(15))
                .perform(RecyclerViewActions.actionOnItemAtPosition(8,click()));

        //校验是否可见及内容
        onView(withId(R.id.text_view))
                .check(matches(isDisplayed()))
                .check(matches(withText("8")));

    }
}
