package com.cxp.androidut.espresso;

import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.Toolbar;

import com.cxp.androidut.R;
import com.cxp.androidut.espresso.toolbar.ToolbarActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;

/**
 * 文 件 名: ToolbarActivityTest
 * 创 建 人: CXP
 * 创建日期: 2019-03-12 13:43
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
@RunWith(AndroidJUnit4.class)
public class ToolbarActivityTest {

    @Rule
    public ActivityTestRule<ToolbarActivity> rule = new ActivityTestRule<>(ToolbarActivity.class);

    @Test
    public void toolbarTest() {
        CharSequence title = rule.getActivity().getResources().getString(R.string.name);

        //记得要在清单文件中添加noActionBar
        onView(isAssignableFrom(Toolbar.class))
                .check(matches(withToolbarTitle(title)));
    }

    private static Matcher<Object> withToolbarTitle(final CharSequence title) {
        return new BoundedMatcher<Object, Toolbar>(Toolbar.class) {

            @Override
            public void describeTo(Description description) {
                description.appendText("with toolbar title :");
            }

            @Override
            protected boolean matchesSafely(Toolbar item) {
                return item.getTitle().equals(title);
            }
        };
    }

}
