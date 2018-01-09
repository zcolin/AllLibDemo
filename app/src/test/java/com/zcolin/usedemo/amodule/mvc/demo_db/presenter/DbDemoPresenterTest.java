/*
 * *********************************************************
 *   author   colin
 *   company  telchina
 *   email    wanglin2046@126.com
 *   date     18-1-9 下午5:03
 * ********************************************************
 */

package com.zcolin.usedemo.amodule.mvc.demo_db.presenter;

import android.os.Bundle;

import com.zcolin.usedemo.amodule.mvp.demo_db.presenter.DbDemoPresenter;
import com.zcolin.usedemo.amodule.mvp.demo_db.view.IDBDemoView;
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
        PowerMockito.when(DaoMgr.queryAllObject()).thenReturn(new ArrayList<>());
    }

    @Test
    public void testOnLoad() throws Exception {
        presenter.onLoad(bundle);
        verify(view).showResult(anyString());
    }

    @Test
    public void testInsertObject() throws Exception {
        PowerMockito.when(DaoMgr.insertObject(new Employee())).thenReturn(true);
        presenter.insertObject();
        verify(view).showResult(anyString());
        verify(view).toastShort(anyString());
    }

    @Test
    public void testInsertOrReplaceObject() throws Exception {
        PowerMockito.when(DaoMgr.insertOrReplaceObject(new Employee())).thenReturn(true);
        presenter.insertOrReplaceObject();
        verify(view).showResult(anyString());
        verify(view).toastShort(anyString());
    }

    @Test
    public void testQueryAllObject() throws Exception {
        presenter.queryAllObject();
        verify(view).showResult(anyString());
    }

    @Test
    public void testQueryObjectWithCondition() throws Exception {
        PowerMockito.when(DaoMgr.queryObjectWithCondition(1)).thenReturn(new ArrayList<>());
        presenter.queryObjectWithCondition();
        verify(view).showResult(anyString());
    }

    @Test
    public void testDeleteAllObject() throws Exception {
        PowerMockito.when(DaoMgr.deleteAllObject()).thenReturn(true);
        presenter.deleteAllObject();
        verify(view).showResult(anyString());
    }
}