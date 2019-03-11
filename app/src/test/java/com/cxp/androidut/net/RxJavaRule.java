package com.cxp.androidut.net;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.functions.Function;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

/**
 * 文 件 名: RxJavaRule
 * 创 建 人: CXP
 * 创建日期: 2019-03-11 8:02
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public class RxJavaRule implements TestRule {

    @Override
    public Statement apply(final Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                RxJavaPlugins.reset();
                RxJavaPlugins.setIoSchedulerHandler(new Function<Scheduler, Scheduler>() {
                    @Override
                    public Scheduler apply(Scheduler scheduler) throws Exception {
                        return Schedulers.trampoline();
                    }
                });
                RxAndroidPlugins.reset();
                RxAndroidPlugins.setMainThreadSchedulerHandler(new Function<Scheduler, Scheduler>() {
                    @Override
                    public Scheduler apply(Scheduler scheduler) throws Exception {
                        return Schedulers.trampoline();
                    }
                });

                try {
                    base.evaluate();
                } finally {
                    RxJavaPlugins.reset();
                    RxAndroidPlugins.reset();
                }
            }
        };
    }
}

