/*
 * *********************************************************
 *   author   colin
 *   company  telchina
 *   email    wanglin2046@126.com
 *   date     18-1-9 下午5:02
 * ********************************************************
 */

package com.zcolin.usedemo.amodule.mvp.base;

import java.util.ArrayList;

/**
 * HttpDemo
 */
public interface BaseRecyclerMVPView<T> extends BaseMVPView {

    void setDataToRecyclerView(ArrayList<T> list, boolean isClear);

    void setPullLoadMoreCompleted();
}
