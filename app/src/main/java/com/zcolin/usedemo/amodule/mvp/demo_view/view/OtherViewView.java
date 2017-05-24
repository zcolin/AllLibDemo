/*
 * *********************************************************
 *   author   colin
 *   company  fosung
 *   email    wanglin2046@126.com
 *   date     17-5-23 下午4:52
 * ********************************************************
 */

package com.zcolin.usedemo.amodule.mvp.demo_view.view;

import com.zcolin.usedemo.amodule.mvp.base.BaseMVPView;

import java.util.ArrayList;

/**
 */
public interface OtherViewView extends BaseMVPView {
    void startBanner(ArrayList<String> listUrl);

    void startTextSwitcher(String str);
}
