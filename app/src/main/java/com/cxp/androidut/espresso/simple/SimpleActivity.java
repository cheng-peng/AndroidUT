package com.cxp.androidut.espresso.simple;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.cxp.androidut.R;

/**
 * 文 件 名: SimpleActivity
 * 创 建 人: CXP
 * 创建日期: 2019-03-12 12:10
 * 描    述: Espresso 简单使用
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public class SimpleActivity extends AppCompatActivity {

    TextView mTvContent;
    EditText mEt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);
        mTvContent=findViewById(R.id.tv_content);
        mEt=findViewById(R.id.et_01);
    }

    public void clickLis(View view){
        switch (view.getId()) {
            case R.id.btn01:
                mTvContent.setVisibility(View.VISIBLE);
                mTvContent.setText("hello espresso!");
                break;
            case R.id.btn02:
                mTvContent.setVisibility(View.VISIBLE);
                mTvContent.setText("success");
                mEt.setText("");
                break;
        }
    }
}
