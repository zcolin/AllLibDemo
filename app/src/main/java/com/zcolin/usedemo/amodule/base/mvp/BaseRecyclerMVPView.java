/*
 * *********************************************************
 *   author   colin
 *   company  fosung
 *   email    wanglin2046@126.com
 *   date     17-5-23 下午4:32
 * ********************************************************
 */

package com.zcolin.usedemo.amodule.base.mvp;

import java.util.ArrayList;

/**
 * HttpDemo
 */
public interface BaseRecyclerMVPView<T> extends BaseMVPView {

    void setDataToRecyclerView(ArrayList<T> list, boolean isClear);

    void setPullLoadMoreCompleted();
}
