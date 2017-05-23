/*
 * *********************************************************
 *   author   colin
 *   company  fosung
 *   email    wanglin2046@126.com
 *   date     17-5-22 下午3:33
 * ********************************************************
 */
package com.zcolin.usedemo.amodule.mvp.demo_view.activity.itemarrange;


import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zcolin.frame.utils.ToastUtil;
import com.zcolin.outlib.views.itemarrange.AppsItemEntity;
import com.zcolin.outlib.views.itemarrange.drag.adapter.AppsAdapter;
import com.zcolin.usedemo.R;
import com.zcolin.usedemo.amodule.base.BaseActivity;

import java.util.ArrayList;


/**
 * 应用图标排序
 */
public class ItemArrangeDragActivity extends BaseActivity implements AppsAdapter.OnAppsItemClickListener, AppsAdapter.OnItemEditListener {

    private RecyclerView              recyclerviewApp;
    private AppsAdapter               adapter;
    private ArrayList<AppsItemEntity> listMyItems;
    private ArrayList<AppsItemEntity> listOtherItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itemarrange);

        setToolbarTitle("应用");
        setToolbarRightBtnText("编辑");
        initView();
        initData();
    }

    protected void initView() {
        recyclerviewApp = getView(R.id.recyclerview_app);
    }

    private void initData() {
        ArrayList<AppsItemEntity> list = ItemDragMgr.getAllItems();
        listMyItems = ItemDragMgr.getMyItems(list);
        listOtherItems = ItemDragMgr.getOtherItems(list);

        GridLayoutManager manager = new GridLayoutManager(this, 4);
        recyclerviewApp.setLayoutManager(manager);
        adapter = new AppsAdapter(recyclerviewApp, listMyItems, listOtherItems);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int viewType = adapter.getItemViewType(position);
                return viewType == AppsAdapter.TYPE_MY_APPS || viewType == AppsAdapter.TYPE_OTHER_APPS ? 1 : 4;
            }
        });
        recyclerviewApp.setAdapter(adapter);
        adapter.setOnAppsItemClickListener(this);
        adapter.setOnItemEditListener(this);
    }

    @Override
    public void onItemClick(View v, int position, int itemType) {
        String appId = "";
        if (itemType == 0) {
            appId = listMyItems.get(position).appId;
        } else if (itemType == 1) {
            appId = listOtherItems.get(position).appId;
        }

        ToastUtil.toastShort("appId:" + appId);
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
        ItemDragMgr.saveItems(listMyItems, listOtherItems);
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
