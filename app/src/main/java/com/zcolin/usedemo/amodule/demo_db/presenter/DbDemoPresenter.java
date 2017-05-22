/*
 * *********************************************************
 *   author   colin
 *   company  fosung
 *   email    wanglin2046@126.com
 *   date     17-5-4 下午5:17
 * ********************************************************
 */

package com.zcolin.usedemo.amodule.demo_db.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zcolin.usedemo.amodule.base.mvp.BaseMVPPresenter;
import com.zcolin.usedemo.amodule.demo_db.view.IDBDemoView;
import com.zcolin.usedemo.biz.DaoMgr;
import com.zcolin.usedemo.db.entity.Employee;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * DBDemo
 */
public class DbDemoPresenter extends BaseMVPPresenter<IDBDemoView> {
    private int         currentSortType = 0;//当前排序方式

    @Override
    public void onLoad(@Nullable Bundle data) {
        List<Employee> list = DaoMgr.queryAllObject();
        mView.showResult(list);
    }

    /**
     * 插入对象
     */
    public void insertObject() {
        List<Employee> list = new ArrayList<>();
        boolean b = DaoMgr.insertObject(getEmployee());
        if (b) {
            list = DaoMgr.queryAllObject();
        }
        mView.showResult(list);
        mView.toastShort(b ? "插入成功" : "插入失败-主键重复");
    }

    /**
     * 有则替换，无则插入
     */
    public void insertOrReplaceObject() {
        List<Employee> list = new ArrayList<>();
        boolean b = DaoMgr.insertOrReplaceObject(getEmployee());
        if (b) {
            list = DaoMgr.queryAllObject();
        }
        mView.showResult(list);
        mView.toastShort(b ? "插入成功" : "插入失败-主键重复");
    }

    /**
     * 查询所有数据的数据列表
     */
    public void queryAllObject() {
        List<Employee> list = DaoMgr.queryAllObject();
        mView.showResult(list);
    }

    /**
     * 查询group为“部门一”的人员,从第二条开始限制为3个,按照日期降序/升序
     * <p>
     */
    public void queryObjectWithCondition() {
        currentSortType = currentSortType == 0 ? 1 : 0;
        List<Employee> list = DaoMgr.queryObjectWithCondition(currentSortType);
        mView.showResult(list);
    }

    /**
     * 删除所有数据
     */
    public void deleteAllObject() {
        List<Employee> list = new ArrayList<>();
        boolean b = DaoMgr.deleteAllObject();
        if (b) {
            list = DaoMgr.queryAllObject();
        }
        mView.showResult(list);
    }

    /**
     * 制造假数据
     */
    private Employee getEmployee() {
        Employee employee = new Employee();
        Date date = new Date();
        long t = date.getTime();

        employee.setId(t % 20);
        employee.setCompany("fosung");
        employee.setName("张" + t % 15);
        employee.setGroup(t % 2 == 0 ? "部门一" : "部门二");
        employee.setDate(date);
        return employee;
    }
}
