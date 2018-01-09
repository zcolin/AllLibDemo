/*
 * *********************************************************
 *   author   colin
 *   company  telchina
 *   email    wanglin2046@126.com
 *   date     18-1-9 下午5:02
 * ********************************************************
 */

package com.zcolin.usedemo.biz;

import com.zcolin.usedemo.db.DaoManager;
import com.zcolin.usedemo.db.entity.Employee;
import com.zcolin.usedemo.db.entity.EmployeeDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import static com.zcolin.usedemo.db.DaoManager.getDaoHelper;

/**
 * 数据库操作管理
 */
public class DaoMgr {
    /**
     * 插入对象
     */
    public static boolean insertObject(Employee employee) {
        return getDaoHelper().insertObject(employee);
    }

    /**
     * 有则替换，无则插入
     */
    public static boolean insertOrReplaceObject(Employee employee) {
        return getDaoHelper().insertOrReplaceObject(employee);
    }

    /**
     * 查询所有数据的数据列表
     */
    public static List<Employee> queryAllObject() {
        return getDaoHelper().queryAll(Employee.class);
    }

    /**
     * 查询group为“部门一”的人员,从第二条开始限制为3个,按照日期降序
     * <p>
     */
    public static List<Employee> queryObjectWithCondition(int sortType) {
        QueryBuilder<Employee> queryBuilder = getDaoHelper().getQueryBuilder(Employee.class);
        queryBuilder.where(EmployeeDao.Properties.Group.eq("部门二"));
        queryBuilder.offset(1);
        queryBuilder.limit(3);
        if (sortType == 1) {
            queryBuilder.orderDesc(EmployeeDao.Properties.Date);
        } else {
            queryBuilder.orderAsc(EmployeeDao.Properties.Date);
        }
        return DaoManager.getDaoHelper().queryObjects(queryBuilder);
    }

    /**
     * 删除所有数据
     */
    public static boolean deleteAllObject() {
        return getDaoHelper().deleteAll(Employee.class);
    }
}
