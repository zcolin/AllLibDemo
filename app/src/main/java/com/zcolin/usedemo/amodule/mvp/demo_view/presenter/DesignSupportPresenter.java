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
import android.os.Handler;
import android.support.annotation.Nullable;

import com.zcolin.usedemo.amodule.mvp.base.BaseMVPPresenter;
import com.zcolin.usedemo.amodule.mvp.demo_view.view.DesignSupportView;
import com.zcolin.usedemo.biz.TestDataMgr;

public class DesignSupportPresenter extends BaseMVPPresenter<DesignSupportView> {
    private int mPage = 1;

    @Override
    public void onLoad(@Nullable Bundle data) {

    }

    /**
     * 模拟网络请求获取数据
     */
    public void requestData(final boolean isRefresh) {
        mPage = isRefresh ? 1 : mPage + 1;
        new Handler().postDelayed(() -> {
            mView.setDataToRecyclerView(TestDataMgr.getTextList(mPage), mPage == 1);
            mView.setPullLoadMoreCompleted();
        }, 1000);
    }
}
