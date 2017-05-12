/*
 * *********************************************************
 *   author   colin
 *   company  fosung
 *   email    wanglin2046@126.com
 *   date     17-5-6 下午12:58
 * ********************************************************
 */

package com.zcolin.usedemo.amodule.base.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zcolin.usedemo.biz.ApiMgr;
import com.zcolin.usedemo.biz.DaoMgr;

/**
 * MVP模式Presenter的基类
 */
public abstract class BaseMVPPresenter<V> {
    protected V mView;
    protected ApiMgr apiMgr = ApiMgr.getInstance();
    protected DaoMgr daoMgr = DaoMgr.getInstance();


    public void onAttach(V view) {
        this.mView = view;
    }

    /**
     * 始化数据请在此函数进行
     */
    public void onLoad(@Nullable Bundle data) {
        
    }

    public void onStart() {

    }

    public void onResume() {
    }

    public void onPause() {
    }

    public void onStop() {
    }

    public void onDestroy() {
    }

    public void onDetach() {
        this.mView = null;
    }

    public void onSave(Bundle state) {

    }

    //用于Mock测试变量传入
    public void setApiMgr(ApiMgr apiMgr) {
        this.apiMgr = apiMgr;
    }

    //用于Mock测试变量传入
    public void setDaoMgr(DaoMgr daoMgr) {
        this.daoMgr = daoMgr;
    }
}
