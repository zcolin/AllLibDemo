/*
 * *********************************************************
 *   author   colin
 *   company  telchina
 *   email    wanglin2046@126.com
 *   date     18-1-9 下午5:03
 * ********************************************************
 */

package com.zcolin.usedemo.amodule.mvp.demo_view.presenter;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;

import com.zcolin.usedemo.amodule.mvp.base.BaseMVPPresenter;
import com.zcolin.usedemo.amodule.mvp.demo_view.view.OtherViewView;
import com.zcolin.usedemo.biz.TestDataMgr;

public class OtherViewPresenter extends BaseMVPPresenter<OtherViewView> {

    @Override
    public void onLoad(@Nullable Bundle data) {
        mView.startBanner(TestDataMgr.getBannerImageUrlList());
        mView.startTextSwitcher(TestDataMgr.getTextSwitcherText());
    }

    /**
     * 模拟后台任务
     */
    public void mockBackGroundThreadTask(int millionSeconds) {
        SystemClock.sleep(millionSeconds);
    }
}
