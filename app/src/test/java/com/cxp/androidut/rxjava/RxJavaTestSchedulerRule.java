package com.cxp.androidut.rxjava;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.functions.Function;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.TestScheduler;

/**
 * 文 件 名: RxJavaTestSchedulerRule
 * 创 建 人: CXP
 * 创建日期: 2019-03-11 13:56
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public class RxJavaTestSchedulerRule implements TestRule {

    private final TestScheduler mTestScheduler = new TestScheduler();

    public TestScheduler getTestScheduler() {
        return mTestScheduler;
    }

    @Override
    public Statement apply(final Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                RxJavaPlugins.setIoSchedulerHandler(new Function<Scheduler, Scheduler>() {
                    @Override
                    public Scheduler apply(Scheduler scheduler) throws Exception {
                        return mTestScheduler;
                    }
                });

                RxJavaPlugins.setNewThreadSchedulerHandler(new Function<Scheduler, Scheduler>() {
                    @Override
                    public Scheduler apply(Scheduler scheduler) throws Exception {
                        return mTestScheduler;
                    }
                });

                RxJavaPlugins.setComputationSchedulerHandler(new Function<Scheduler, Scheduler>() {
                    @Override
                    public Scheduler apply(Scheduler scheduler) throws Exception {
                        return mTestScheduler;
                    }
                });

                RxAndroidPlugins.setMainThreadSchedulerHandler(new Function<Scheduler, Scheduler>() {
                    @Override
                    public Scheduler apply(Scheduler scheduler) throws Exception {
                        return mTestScheduler;
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