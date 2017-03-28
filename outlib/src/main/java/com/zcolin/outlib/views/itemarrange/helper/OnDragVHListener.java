/*
 * *********************************************************
 *   author   colin
 *   company  fosung
 *   email    wanglin2046@126.com
 *   date     17-3-27 上午11:48
 * ********************************************************
 */

package com.zcolin.outlib.views.itemarrange.helper;

/**
 * ViewHolder 被选中 以及 拖拽释放 触发监听器
 */
public interface OnDragVHListener {
    /**
     * Item被选中时触发
     */
    void onItemSelected();


    /**
     * Item在拖拽结束/滑动结束后触发
     */
    void onItemFinish();
}
