/*
 * *********************************************************
 *   author   colin
 *   company  fosung
 *   email    wanglin2046@126.com
 *   date     17-5-8 下午3:31
 * ********************************************************
 */

package com.zcolin.usedemo.amodule.demo_db.presenter;

import android.os.Bundle;

import com.zcolin.usedemo.amodule.demo_db.view.IDBDemoView;
import com.zcolin.usedemo.biz.DaoMgr;
import com.zcolin.usedemo.db.entity.Employee;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;

import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;

/**
 *
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(DaoMgr.class)
public class DbDemoPresenterTest {
    private DbDemoPresenter presenter;
    @Mock
    private IDBDemoView     view;

    @Mock
    Bundle bundle;

    @Before
    public void setupMocksAndView() {
        // 使用Mock标签等需要先init初始化一下
        MockitoAnnotations.initMocks(this);

        // 用真实接口创建反馈Presenter
        presenter = new DbDemoPresenter();
        presenter.onAttach(view);//传入mock对象

        bundle.putString("data", "data");
        PowerMockito.mockStatic(DaoMgr.class);
        PowerMockito.when(DaoMgr.queryAllObject())
                    .thenReturn(new ArrayList<Employee>());
    }

    @Test
    public void testOnLoad() throws Exception {
        presenter.onLoad(bundle);
        verify(view)
                .showResult(anyList());
    }

    @Test
    public void testInsertObject() throws Exception {
        PowerMockito.when(DaoMgr.insertObject(new Employee()))
                    .thenReturn(true);
        presenter.insertObject();
        verify(view)
                .showResult(anyList());
        verify(view)
                .toastShort(anyString());
    }

    @Test
    public void testInsertOrReplaceObject() throws Exception {
        PowerMockito.when(DaoMgr.insertOrReplaceObject(new Employee()))
                    .thenReturn(true);
        presenter.insertOrReplaceObject();
        verify(view)
                .showResult(anyList());
        verify(view)
                .toastShort(anyString());
    }

    @Test
    public void testQueryAllObject() throws Exception {
        presenter.queryAllObject();
        verify(view)
                .showResult(anyList());
    }

    @Test
    public void testQueryObjectWithCondition() throws Exception {
        PowerMockito.when(DaoMgr.queryObjectWithCondition(1))
                    .thenReturn(new ArrayList<Employee>());
        presenter.queryObjectWithCondition();
        verify(view)
                .showResult(anyList());
    }

    @Test
    public void testDeleteAllObject() throws Exception {
        PowerMockito.when(DaoMgr.deleteAllObject())
                    .thenReturn(true);
        presenter.deleteAllObject();
        verify(view)
                .showResult(anyList());
    }
}