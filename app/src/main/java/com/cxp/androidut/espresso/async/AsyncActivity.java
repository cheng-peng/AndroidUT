package com.cxp.androidut.espresso.async;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.cxp.androidut.R;
import com.cxp.androidut.utils.EspressoIdlingResource;

/**
 * 文 件 名: AsyncActivity
 * 创 建 人: CXP
 * 创建日期: 2019-03-12 12:36
 * 描    述: Espresso 异步请求
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public class AsyncActivity extends AppCompatActivity {

    Button btn01;
    ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async);
        btn01=findViewById(R.id.btn01);
        imageView=findViewById(R.id.imageView);
    }

    public void clickLis(View view){
        //意味着开始了异步
        EspressoIdlingResource.increment();

        Glide.with(this)
                .load("http://imgsrc.baidu.com/imgad/pic/item/caef76094b36acaf0accebde76d98d1001e99ce7.jpg")
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        btn01.setText("success!");
                        //图片加载成功，结束异步
                        if (!EspressoIdlingResource.getIdlingResource().isIdleNow()) {
                            EspressoIdlingResource.decrement();
                        }
                        return false;
                    }
                }).into(imageView);
    }

    @VisibleForTesting
    public IdlingResource getCountingIdlingResource() {
        return EspressoIdlingResource.getIdlingResource();
    }
}
