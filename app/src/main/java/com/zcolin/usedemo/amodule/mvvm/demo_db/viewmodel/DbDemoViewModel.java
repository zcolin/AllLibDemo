/*
 * *********************************************************
 *   author   colin
 *   company  fosung
 *   email    wanglin2046@126.com
 *   date     17-5-26 下午3:36
 * ********************************************************
 */

package com.zcolin.usedemo.amodule.mvvm.demo_db.viewmodel;

import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.zcolin.frame.util.ToastUtil;
import com.zcolin.usedemo.amodule.mvvm.base.BaseViewModel;
import com.zcolin.usedemo.biz.DaoMgr;
import com.zcolin.usedemo.biz.TestDataMgr;
import com.zcolin.usedemo.db.entity.Employee;

import java.util.ArrayList;
import java.util.List;

/**
 * DBDemo
 */
public class DbDemoViewModel extends BaseViewModel {
    private int                     currentSortType = 0;//当前排序方式
    public ObservableField<String> strResult       = new ObservableField<>();

    @Override
    public void onLoad(@Nullable Bundle data) {
        List<Employee> list = DaoMgr.queryAllObject();
        showResult(list);
    }

    /**
     * 插入对象
     */
    public void insertObject(View view) {
        List<Employee> list = new ArrayList<>();
        boolean b = DaoMgr.insertObject(TestDataMgr.getTestEmployee());
        if (b) {
            list = DaoMgr.queryAllObject();
        }
        showResult(list);
        ToastUtil.toastShort(b ? "插入成功" : "插入失败-主键重复");
    }

    /**
     * 有则替换，无则插入
     */
    public void insertOrReplaceObject(View view) {
        List<Employee> list = new ArrayList<>();
        boolean b = DaoMgr.insertOrReplaceObject(TestDataMgr.getTestEmployee());
        if (b) {
            list = DaoMgr.queryAllObject();
        }
        showResult(list);
        ToastUtil.toastShort(b ? "插入成功" : "插入失败-主键重复");
    }

    /**
     * 查询所有数据的数据列表
     */
    public void queryAllObject(View view) {
        List<Employee> list = DaoMgr.queryAllObject();
        showResult(list);
    }

    /**
     * 查询group为“部门一”的人员,从第二条开始限制为3个,按照日期降序/升序
     * <p>
     */
    public void queryObjectWithCondition(View view) {
        currentSortType = currentSortType == 0 ? 1 : 0;
        List<Employee> list = DaoMgr.queryObjectWithCondition(currentSortType);
        showResult(list);
    }

    /**
     * 删除所有数据
     */
    public void deleteAllObject(View view) {
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
            strResult.set(builder.toString());
        } else {
            strResult.set(null);
        }
    }
}
