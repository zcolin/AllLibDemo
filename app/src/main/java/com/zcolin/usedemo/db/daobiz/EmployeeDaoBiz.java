/***********************************************************
 * author   colin
 * company  fosung
 * email    wanglin2046@126.com
 * date     16-7-15 上午9:54
 **********************************************************/

package com.zcolin.usedemo.db.daobiz;


import com.fosung.frame.db.BaseDaoBiz;
import com.zcolin.usedemo.db.DaoManager;
import com.fosung.usedemo.greendao.dao.DaoSession;
import com.fosung.usedemo.greendao.entity.Employee;

/**
 * 示例
 */
public class EmployeeDaoBiz extends BaseDaoBiz<Employee, DaoSession> {

    @Override
    public DaoSession getDaoSession() {
        //return DaoManager.getDaoSession(context, "alias");//指定路径和别名的数据库
        return DaoManager.getDaoSession();
    }
}
