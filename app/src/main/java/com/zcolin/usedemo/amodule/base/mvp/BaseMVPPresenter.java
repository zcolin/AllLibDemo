/*
 * *********************************************************
 *   author   colin
 *   company  fosung
 *   email    wanglin2046@126.com
 *   date     17-5-17 上午10:00
 * ********************************************************
 */

package com.zcolin.usedemo.amodule.base.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;


/**
 * MVP模式Presenter的基类
 */
public abstract class BaseMVPPresenter<V> {
    protected V mView;

    public void onAttach(V view) {
        this.mView = view;
    }

    /**
     * 初始化数据请在此函数进行
     */
    public void onLoad(@Nullable Bundle data) {

    }

    /**
     * 对应Activity或Fragment的onStart
     */
    public void onStart() {

    }

    /**
     * 对应Activity或Fragment的onResume
     */
    public void onResume() {
    }

    /**
     * 对应Activity或Fragment的onPause
     */
    public void onPause() {
    }

    /**
     * 对应Activity或Fragment的onStop
     */
    public void onStop() {
    }

    /**
     * 对应Activity或Fragment的onDestroy
     */
    public void onDestroy() {
    }

    
    public void onDetach() {
    }
}
