package com.cxp.androidut.espresso.toolbar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.cxp.androidut.R;

/**
 * 文 件 名: ToolbarActivity
 * 创 建 人: CXP
 * 创建日期: 2019-03-12 13:41
 * 描    述: Espresso高级使用：Matcher
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public class ToolbarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);
        Toolbar toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.name));
    }
}
