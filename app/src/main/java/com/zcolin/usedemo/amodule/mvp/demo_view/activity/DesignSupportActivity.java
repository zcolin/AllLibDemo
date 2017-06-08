/*
 * *********************************************************
 *   author   colin
 *   company  fosung
 *   email    wanglin2046@126.com
 *   date     17-5-22 下午3:33
 * ********************************************************
 */

package com.zcolin.usedemo.amodule.mvp.demo_view.activity;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

import com.zcolin.frame.util.ToastUtil;
import com.zcolin.gui.zrecyclerview.BaseRecyclerAdapter;
import com.zcolin.gui.zrecyclerview.ZRecyclerView;
import com.zcolin.usedemo.R;
import com.zcolin.usedemo.amodule.base.ActivityParam;
import com.zcolin.usedemo.amodule.mvp.base.BaseMVPActivity;
import com.zcolin.usedemo.amodule.mvp.base.Presenter;
import com.zcolin.usedemo.amodule.mvp.demo_view.adapter.ZRecyclerMultiItemAdapter;
import com.zcolin.usedemo.amodule.mvp.demo_view.presenter.DesignSupportPresenter;
import com.zcolin.usedemo.amodule.mvp.demo_view.view.DesignSupportView;
import com.zcolin.usedemo.entity.ListItemData;

import java.util.ArrayList;


/**
 * MD风格的RecyclerView，多个Item的列表示例
 */
@ActivityParam(isShowToolBar = false)
@Presenter(DesignSupportPresenter.class)
public class DesignSupportActivity extends BaseMVPActivity<DesignSupportPresenter> implements DesignSupportView {
    private ZRecyclerView             recyclerView;
    private ZRecyclerMultiItemAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_designsupport);

        initView();
    }

    private void initView() {
        findViewById(R.id.fab)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Snackbar.make(v, "FloatActionBar-click", Snackbar.LENGTH_LONG)
                                .setAction("toast", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        ToastUtil.toastShort("button-click");
                                    }
                                });
                    }
                });


        recyclerView = getView(R.id.pullLoadMoreRecyclerView);
        //设置数据为空时的EmptyView，DataObserver是注册在adapter之上的，也就是setAdapter是注册上，notifyDataSetChanged的时候才会生效
        recyclerView.setEmptyView(this, R.layout.view_pullrecycler_empty);
        //设置HeaderView
        recyclerView.addHeaderView(this, R.layout.view_recyclerheader);
        recyclerView.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<String>() {
            @Override
            public void onItemClick(View covertView, int position, String data) {
                Toast.makeText(mActivity, data, Toast.LENGTH_SHORT)
                     .show();
            }
        });
        recyclerView.setOnPullLoadMoreListener(new ZRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                mPresenter.requestData(true);
            }

            @Override
            public void onLoadMore() {
                mPresenter.requestData(false);
            }
        });

        recyclerView.refreshWithPull();
    }


    @Override
    public void setDataToRecyclerView(ArrayList<ListItemData> list, boolean isClear) {
        if (recyclerViewAdapter == null) {
            recyclerViewAdapter = new ZRecyclerMultiItemAdapter();
            recyclerView.setAdapter(recyclerViewAdapter);
        }

        if (isClear) {
            recyclerViewAdapter.setDatas(list);
        } else {
            recyclerViewAdapter.addDatas(list);
        }
    }

    @Override
    public void setPullLoadMoreCompleted() {
        recyclerView.setPullLoadMoreCompleted();
    }
}
