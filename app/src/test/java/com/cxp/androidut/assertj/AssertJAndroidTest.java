package com.cxp.androidut.assertj;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.cxp.androidut.BuildConfig;
import com.cxp.androidut.R;
import com.cxp.androidut.ui.MainActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import static org.assertj.android.api.Assertions.assertThat;

/**
 * 文 件 名: AssertJAndroidTest
 * 创 建 人: CXP
 * 创建日期: 2019-03-12 10:21
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 23)
public class AssertJAndroidTest {

    private MainActivity mainActivity;
    private Button mJumpBtn;
    private LinearLayout mRoot;
    private CheckBox checkBox;

    @Before
    public void setUp(){
        //输出日志
        ShadowLog.stream = System.out;
        mainActivity = Robolectric.setupActivity(MainActivity.class);
        mJumpBtn = mainActivity.findViewById(R.id.button1);
        mRoot = mainActivity.findViewById(R.id.root);
        checkBox = mainActivity.findViewById(R.id.checkbox);
    }

    @Test
    public void testView() {
         //Button是否可见
        assertThat(mJumpBtn).isVisible();
         //LinearLayout 方向，子View数量
        assertThat(mRoot)
                .isVertical()
                .hasChildCount(4);
        //CheckBox是否未选中
        assertThat(checkBox).isNotChecked();
    }
}
