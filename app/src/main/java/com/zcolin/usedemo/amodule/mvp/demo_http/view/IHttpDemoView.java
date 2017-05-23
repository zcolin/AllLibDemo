/*
 * *********************************************************
 *   author   colin
 *   company  fosung
 *   email    wanglin2046@126.com
 *   date     17-5-22 上午11:37
 * ********************************************************
 */

package com.zcolin.usedemo.amodule.mvp.demo_http.view;

import com.zcolin.usedemo.amodule.base.mvp.BaseMVPView;

/**
 * HttpDemo
 */
public interface IHttpDemoView extends BaseMVPView {
    void showResult(String data);

    void showError(String error);
}
