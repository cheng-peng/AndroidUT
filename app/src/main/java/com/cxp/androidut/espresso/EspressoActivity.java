package com.cxp.androidut.espresso;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.cxp.androidut.R;
import com.cxp.androidut.espresso.async.AsyncActivity;
import com.cxp.androidut.espresso.async2.AsyncActivity2;
import com.cxp.androidut.espresso.list.ListActivity;
import com.cxp.androidut.espresso.okhttp.OkHttpActivity;
import com.cxp.androidut.espresso.rotate.RotateActivity;
import com.cxp.androidut.espresso.simple.SimpleActivity;
import com.cxp.androidut.espresso.toolbar.ToolbarActivity;

/**
 * 文 件 名: EspressoActivity
 * 创 建 人: CXP
 * 创建日期: 2019-03-12 10:37
 * 描    述: 自动化测试类
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public class EspressoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_espresso);
    }

    public void clickLis(View view) {
        switch (view.getId()) {
            case R.id.btn_01:
                //Espresso 简单使用
                startActivity(new Intent(this, SimpleActivity.class));
                break;
            case R.id.btn_02:
                //Espresso recyclerView使用
                startActivity(new Intent(this, ListActivity.class));
                break;
            case R.id.btn_03:
                //Espresso 屏幕旋转处理
                startActivity(new Intent(this, RotateActivity.class));
                break;
            case R.id.btn_04:
                //Espresso高级使用：Matcher
                startActivity(new Intent(this, ToolbarActivity.class));
                break;
            case R.id.btn_05:
                //Espresso 异步请求
                startActivity(new Intent(this, AsyncActivity.class));
                break;
            case R.id.btn_06:
                //Espresso 异步请求2
                startActivity(new Intent(this, AsyncActivity2.class));
                break;
            case R.id.btn_07:
                //Espresso okhttp异步请求
                startActivity(new Intent(this, OkHttpActivity.class));
                break;
        }
    }
}
