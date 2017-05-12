/*
 * *********************************************************
 *   author   colin
 *   company  fosung
 *   email    wanglin2046@126.com
 *   date     17-5-4 下午5:24
 * ********************************************************
 */

package com.zcolin.usedemo.amodule.demo_db.view;

import com.zcolin.usedemo.amodule.base.mvp.BaseMVPView;
import com.zcolin.usedemo.db.entity.Employee;

import java.util.List;

/**
 * DBDemo
 */
public interface IDBDemoView extends BaseMVPView<List<Employee>> {
    void toastShort(String str);
}
