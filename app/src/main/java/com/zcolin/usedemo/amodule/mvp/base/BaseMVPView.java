/*
 * *********************************************************
 *   author   colin
 *   company  telchina
 *   email    wanglin2046@126.com
 *   date     18-1-9 下午5:02
 * ********************************************************
 */
package com.zcolin.usedemo.amodule.mvp.base;

/**
 * 视图层代理的基类
 */
public interface BaseMVPView {
    void toastShort(String error);

    void toastLong(String error);
}
