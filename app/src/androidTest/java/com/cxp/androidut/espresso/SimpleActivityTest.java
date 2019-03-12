package com.cxp.androidut.espresso;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.cxp.androidut.R;
import com.cxp.androidut.espresso.simple.SimpleActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;

/**
 * 文 件 名: SimpleActivityTest
 * 创 建 人: CXP
 * 创建日期: 2019-03-12 12:14
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
@RunWith(AndroidJUnit4.class)
public class SimpleActivityTest {
    @Rule
    public ActivityTestRule<SimpleActivity> rule = new ActivityTestRule<>(SimpleActivity.class);

    @Test
    public void clickTest() {
        //tvContent是否默认不显示
        onView(withId(R.id.tv_content))
                .check(matches(not(isDisplayed())));    //是否不可见

        //检查btn01的text，然后执行点击事件
        onView(withId(R.id.btn01))
                .check(matches(withText("修改内容")))
                .perform(click());

        //检查tv内容是否修改，并且是否可见
        onView(withId(R.id.tv_content))
                .check(matches(withText("hello espresso!")))
                .check(matches(isDisplayed()));
    }

    @Test
    public void loginTest() {
        //先清除editText的内容，然后输入，然后关闭软键盘，最后校验内容
        //这里如果要输入中文，使用replaceText()方法代替typeText()
        onView(withId(R.id.et_01))
                .perform(clearText(), replaceText("你好 username"), closeSoftKeyboard())
                .check(matches(withText("你好 username")));

        //点击登录
        onView(withId(R.id.btn02))
                .perform(click());

        //校验内容
        onView(withId(R.id.tv_content))
                .check(matches(withText("success")))
                .check(matches(isDisplayed()));

        onView(withId(R.id.et_01))
                .check(matches(withText("")))           //内容是否为""
                .check(matches(withHint("请输入账户名")))         //hint内容是否为"请输入账户名"
                .check(matches(withHint(containsString("账户名"))));       //hint内容是否包含"账户名"
    }
}
