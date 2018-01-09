/*
 * *********************************************************
 *   author   colin
 *   company  telchina
 *   email    wanglin2046@126.com
 *   date     18-1-9 下午5:03
 * ********************************************************
 */

package com.zcolin.usedemo.amodule.mvvm.demo_view.view;

import com.zcolin.usedemo.amodule.mvp.base.BaseMVPView;

import java.util.ArrayList;

/**
 */
public interface OtherViewView extends BaseMVPView {
    void startBanner(ArrayList<String> listUrl);

    void startTextSwitcher(String str);
}
