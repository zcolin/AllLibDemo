/*
 * *********************************************************
 *   author   colin
 *   company  telchina
 *   email    wanglin2046@126.com
 *   date     18-1-9 下午5:02
 * ********************************************************
 */

package com.zcolin.usedemo.amodule.mvp.demo_db.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zcolin.usedemo.amodule.mvp.base.BaseMVPPresenter;
import com.zcolin.usedemo.amodule.mvp.demo_db.view.IDBDemoView;
import com.zcolin.usedemo.biz.DaoMgr;
import com.zcolin.usedemo.biz.TestDataMgr;
import com.zcolin.usedemo.db.entity.Employee;

import java.util.ArrayList;
import java.util.List;

/**
 * DBDemo
 */
public class DbDemoPresenter extends BaseMVPPresenter<IDBDemoView> {
    private int currentSortType = 0;//当前排序方式

    @Override
    public void onLoad(@Nullable Bundle data) {
        List<Employee> list = DaoMgr.queryAllObject();
        showResult(list);
    }

    /**
     * 插入对象
     */
    public void insertObject() {
        List<Employee> list = new ArrayList<>();
        boolean b = DaoMgr.insertObject(TestDataMgr.getTestEmployee());
        if (b) {
            list = DaoMgr.queryAllObject();
        }
        showResult(list);
        mView.toastShort(b ? "插入成功" : "插入失败-主键重复");
    }

    /**
     * 有则替换，无则插入
     */
    public void insertOrReplaceObject() {
        List<Employee> list = new ArrayList<>();
        boolean b = DaoMgr.insertOrReplaceObject(TestDataMgr.getTestEmployee());
        if (b) {
            list = DaoMgr.queryAllObject();
        }
        showResult(list);
        mView.toastShort(b ? "插入成功" : "插入失败-主键重复");
    }

    /**
     * 查询所有数据的数据列表
     */
    public void queryAllObject() {
        List<Employee> list = DaoMgr.queryAllObject();
        showResult(list);
    }

    /**
     * 查询group为“部门一”的人员,从第二条开始限制为3个,按照日期降序/升序
     * <p>
     */
    public void queryObjectWithCondition() {
        currentSortType = currentSortType == 0 ? 1 : 0;
        List<Employee> list = DaoMgr.queryObjectWithCondition(currentSortType);
        showResult(list);
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
        showResult(list);
    }

    public void showResult(List<Employee> list) {
        if (list != null && list.size() > 0) {
            StringBuilder builder = new StringBuilder();
            for (Employee o : list) {
                builder.append("id:")
                       .append(o.getId())
                       .append("  name:")
                       .append(o.getName())
                       .append("  group:")
                       .append(o.getGroup())
                       .append("  time")
                       .append(o.getDate())
                       .append("\n");
            }
            mView.showResult(builder.toString());
        } else {
            mView.showResult(null);
        }
    }
}
