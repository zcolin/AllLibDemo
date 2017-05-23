/*
 * *********************************************************
 *   author   colin
 *   company  fosung
 *   email    wanglin2046@126.com
 *   date     17-5-17 上午10:00
 * ********************************************************
 */
package com.zcolin.usedemo.amodule.base.mvp;

/**
 * 视图层代理的基类
 */
public interface BaseMVPView {
    void toastShort(String error);

    void toastLong(String error);
}
