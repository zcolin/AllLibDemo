/*
 * *********************************************************
 *   author   colin
 *   company  fosung
 *   email    wanglin2046@126.com
 *   date     17-5-23 下午4:48
 * ********************************************************
 */

package com.zcolin.usedemo.amodule.mvp.demo_view.presenter;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;

import com.zcolin.usedemo.amodule.base.mvp.BaseMVPPresenter;
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
    public void mockBackGroundThreadTask(int millionSeconds){
        SystemClock.sleep(millionSeconds);
    }
}
