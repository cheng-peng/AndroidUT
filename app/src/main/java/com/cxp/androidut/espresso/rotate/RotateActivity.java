package com.cxp.androidut.espresso.rotate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.cxp.androidut.R;

/**
 * 文 件 名: RotateActivity
 * 创 建 人: CXP
 * 创建日期: 2019-03-12 13:24
 * 描    述: espresso 屏幕旋转处理
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public class RotateActivity extends AppCompatActivity {

    private static final String KEY_COUNT = "count";

    TextView countText;

    private int count = 0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotate);
        countText=findViewById(R.id.count);

        if (savedInstanceState != null)
            count = savedInstanceState.getInt(KEY_COUNT,0);

        updateCount();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_COUNT, count);
    }

    public void clickLis(View view){
        count += 1;
        updateCount();
    }


    private void updateCount() {
        countText.setText(String.valueOf(count));
    }
}
