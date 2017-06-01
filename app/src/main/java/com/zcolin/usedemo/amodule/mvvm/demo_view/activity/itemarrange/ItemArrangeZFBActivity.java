/*
 * *********************************************************
 *   author   colin
 *   company  fosung
 *   email    wanglin2046@126.com
 *   date     17-5-26 下午3:36
 * ********************************************************
 */

package com.zcolin.usedemo.amodule.mvvm.demo_view.activity.itemarrange;


import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zcolin.outlib.views.itemarrange.AppsItemEntity;
import com.zcolin.outlib.views.itemarrange.zfb.adapter.AppsAdapter;
import com.zcolin.usedemo.R;
import com.zcolin.usedemo.amodule.base.BaseActivity;

import java.util.ArrayList;


/**
 * 应用图标排序
 */
public class ItemArrangeZFBActivity extends BaseActivity implements AppsAdapter.OnAppsItemClickListener, AppsAdapter.OnItemEditListener {

    private RecyclerView              recyclerviewApp;
    private AppsAdapter               adapter;
    private ArrayList<AppsItemEntity> listAllItems;
    private ArrayList<AppsItemEntity> listMyItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itemarrange);

        setToolbarTitle("应用");
        setToolbarRightBtnText("编辑");

        listAllItems = ItemZFBMgr.getAllItems();
        initView();
        initData();
    }

    protected void initView() {
        recyclerviewApp = getView(R.id.recyclerview_app);

        GridLayoutManager manager = new GridLayoutManager(this, 4);
        recyclerviewApp.setLayoutManager(manager);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int viewType = adapter.getItemViewType(position);
                return viewType == AppsAdapter.TYPE_MY_APPS || viewType == AppsAdapter.TYPE_ALL_APPS ? 1 : 4;
            }
        });
    }

    private void initData() {
        listMyItems = ItemZFBMgr.getMyItems(listAllItems);
        adapter = new AppsAdapter(recyclerviewApp, listMyItems, listAllItems);
        recyclerviewApp.setAdapter(adapter);
        adapter.setOnMyAppsItemClickListener(this);
        adapter.setOnItemEditListener(this);
    }

    @Override
    public void onItemClick(View v, int position, int itemType) {
        AppsItemEntity entity = null;
        if (itemType == 0) {
            listMyItems.get(position);
        } else if (itemType == 1) {
            entity = listAllItems.get(position);
        }

        if (entity != null) {
            ItemZFBMgr.onItemClick(mActivity, entity);
        }
    }

    @Override
    protected void onToolBarRightBtnClick() {
        super.onToolBarRightBtnClick();
        if (adapter.isEditMode()) {
            adapter.completeEdit();
        } else {
            adapter.edit();
        }
    }

    @Override
    protected void onToolBarLeftBtnClick() {
        if (adapter.isEditMode()) {
            adapter.cancelEdit();
            initData();
        } else {
            super.onToolBarLeftBtnClick();
        }
    }

    @Override
    public void onBackPressed() {
        onToolBarLeftBtnClick();
    }

    @Override
    public void onComplete() {
        setToolbarRightBtnText("编辑");
        ItemZFBMgr.saveMyItems(adapter.getListMyAppsItems());
        setResult(RESULT_OK);
    }

    @Override
    public void onCancel() {
        setToolbarRightBtnText("编辑");

    }

    @Override
    public void onEdit() {
        setToolbarRightBtnText("完成");
    }
}
