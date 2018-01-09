/*
 * *********************************************************
 *   author   colin
 *   company  telchina
 *   email    wanglin2046@126.com
 *   date     18-1-9 下午5:02
 * ********************************************************
 */

package com.zcolin.usedemo.amodule.mvc.demo_view.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.widget.Toast;

import com.zcolin.frame.util.ToastUtil;
import com.zcolin.gui.zrecyclerview.BaseRecyclerAdapter;
import com.zcolin.gui.zrecyclerview.ZRecyclerView;
import com.zcolin.usedemo.R;
import com.zcolin.usedemo.amodule.base.ActivityParam;
import com.zcolin.usedemo.amodule.base.BaseActivity;
import com.zcolin.usedemo.amodule.mvc.demo_view.adapter.ZRecyclerMultiItemAdapter;
import com.zcolin.usedemo.entity.ListItemData;

import java.util.ArrayList;


/**
 * MD风格的RecyclerView，多个Item的列表示例
 */
@ActivityParam(isShowToolBar = false)
public class DesignSupportActivity extends BaseActivity {

    private ZRecyclerView             recyclerView;
    private ZRecyclerMultiItemAdapter recyclerViewAdapter;
    private int mPage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_designsupport);
        initView();
    }

    private void initView() {
        findViewById(R.id.fab).setOnClickListener(v -> Snackbar.make(v, "FloatActionBar-click", Snackbar.LENGTH_LONG)
                                                               .setAction("toast", v1 -> ToastUtil.toastShort("button-click")));

        recyclerView = getView(R.id.pullLoadMoreRecyclerView);
        //设置数据为空时的EmptyView，DataObserver是注册在adapter之上的，也就是setAdapter是注册上，notifyDataSetChanged的时候才会生效
        recyclerView.setEmptyView(this, R.layout.view_pullrecycler_empty);
        //设置HeaderView
        recyclerView.addHeaderView(this, R.layout.view_recyclerheader);

        //下拉和到底加载的进度条样式，默认为 ProgressStyle.BallSpinFadeLoaderIndicator
        recyclerView.setOnItemClickListener((BaseRecyclerAdapter.OnItemClickListener<String>) (covertView, position, data) -> Toast.makeText(mActivity, data,
                Toast.LENGTH_SHORT)
                                                                                                                                   .show());
        recyclerView.setOnPullLoadMoreListener(new ZRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                mPage = 1;
                requestData(mPage);
            }

            @Override
            public void onLoadMore() {
                mPage = mPage + 1;
                requestData(mPage);
            }
        });

        recyclerView.refreshWithPull();     //有下拉效果的数据刷新
    }


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


    public void requestData(final int page) {
        new Handler().postDelayed(() -> {
            setDataToRecyclerView(setList(page), page == 1);
            recyclerView.setPullLoadMoreCompleted();
        }, 1000);
    }

    //制造假数据
    private ArrayList<ListItemData> setList(int page) {
        ArrayList<ListItemData> dataList = new ArrayList<>();
        int start = 20 * (page - 1);
        for (int i = start; i < 20 * page; i++) {
            ListItemData data = new ListItemData();
            data.title = "Frist" + i;
            data.id = i;
            dataList.add(data);
        }
        return dataList;
    }
}
