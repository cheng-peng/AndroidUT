package com.cxp.androidut.espresso.async2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.cxp.androidut.R;
import com.cxp.androidut.utils.EspressoIdlingResource;

/**
 * 文 件 名: AsyncActivity2
 * 创 建 人: CXP
 * 创建日期: 2019-03-12 13:53
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public class AsyncActivity2 extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async2);

        textView =  findViewById(R.id.text);
        textView.setVisibility(View.GONE);

        LoadingDialogFragment fragment = new LoadingDialogFragment();
        fragment.setCancelable(false);
        fragment.show(getSupportFragmentManager(), LoadingDialogFragment.TAG);

        EspressoIdlingResource.increment();
    }

    public void onLoadingFinished() {
        if (!EspressoIdlingResource.getIdlingResource().isIdleNow()) {
            EspressoIdlingResource.decrement();
        }

        textView.setText("done");
        textView.setVisibility(View.VISIBLE);
    }

    @VisibleForTesting
    public IdlingResource getIdlingresource() {
        return EspressoIdlingResource.getIdlingResource();
    }
}
