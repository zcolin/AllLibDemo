/*
 * *********************************************************
 *   author   colin
 *   company  telchina
 *   email    wanglin2046@126.com
 *   date     18-1-9 下午5:02
 * ********************************************************
 */

package com.zcolin.usedemo.amodule.mvp.demo_http.view;

import com.zcolin.usedemo.amodule.mvp.base.BaseMVPView;

/**
 * HttpDemo
 */
public interface IHttpDemoView extends BaseMVPView {
    void showResult(String data);

    void showError(String error);
}
