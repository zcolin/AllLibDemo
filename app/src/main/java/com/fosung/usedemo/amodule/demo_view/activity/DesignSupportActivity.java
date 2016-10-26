/*
 * **********************************************************
 *   author   colin
 *   company  fosung
 *   email    wanglin2046@126.com
 *   date     16-10-11 下午4:05
 * *********************************************************
 */

package com.fosung.usedemo.amodule.demo_view.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.fosung.frame.app.BaseFrameActivity;
import com.fosung.frame.utils.ToastUtil;
import com.fosung.gui.pullrecyclerview.BaseRecyclerAdapter;
import com.fosung.gui.pullrecyclerview.PullRecyclerView;
import com.fosung.usedemo.R;
import com.fosung.usedemo.amodule.demo_view.adapter.PullRecyclerMultiItemAdapter;

import java.util.ArrayList;


/**
 * MD风格的RecyclerView，多个Item的列表示例
 */
public class DesignSupportActivity extends BaseFrameActivity {

    private PullRecyclerView             mPullRecyclerView;
    private PullRecyclerMultiItemAdapter mRecyclerViewAdapter;
    private int mPage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_designsupport);
        init();
    }

    private void init() {
        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
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


        mPullRecyclerView = getView(R.id.pullLoadMoreRecyclerView);
        //设置下拉刷新是否可见
        //mPullRecyclerView.setRefreshing(true);
        //设置是否可以下拉刷新
        //mPullRecyclerView.setPullRefreshEnable(true);
        //设置是否可以上拉刷新
        //mPullRecyclerView.setPushRefreshEnable(false);
        //显示下拉刷新
        mPullRecyclerView.setRefreshing(true);
        mPullRecyclerView.setLinearLayout();

        mPullRecyclerView.setOnPullLoadMoreListener(new PullLoadMoreListener());
        mPullRecyclerView.setEmptyView(LayoutInflater.from(this)
                                                     .inflate(R.layout.view_pullrecycler_empty, null));//setEmptyView

        getDataFromShopList(mPage);
    }


    public void addDataToRecyclerView(ArrayList<String> list, boolean isClear) {
        if (mRecyclerViewAdapter == null) {
            mRecyclerViewAdapter = new PullRecyclerMultiItemAdapter();
            TextView tvHeader = new TextView(this);
            tvHeader.setText("我是Header");
            tvHeader.setPadding(50, 50, 50, 50);
            mRecyclerViewAdapter.setHeaderView(tvHeader);
            mRecyclerViewAdapter.addDatas(list);
            mPullRecyclerView.setAdapter(mRecyclerViewAdapter);
            mRecyclerViewAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<String>() {
                @Override
                public void onItemClick(View covertView, int position, String data) {
                    ToastUtil.toastShort(position + ":" + data);
                }
            });
        } else {
            if (isClear) {
                mRecyclerViewAdapter.setDatas(list);
            } else {
                mRecyclerViewAdapter.addDatas(list);
            }
        }
    }


    public void getDataFromShopList(final int page) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        addDataToRecyclerView(setList(page), page == 1);
                        mPullRecyclerView.setPullLoadMoreCompleted();
                    }
                });
            }
        }, 1000);
    }

    //制造假数据
    private ArrayList<String> setList(int page) {
        ArrayList<String> dataList = new ArrayList<>();
        int start = 20 * (page - 1);
        for (int i = start; i < 20 * page; i++) {
            dataList.add("Frist" + i);
        }
        return dataList;
    }

    class PullLoadMoreListener implements PullRecyclerView.PullLoadMoreListener {
        @Override
        public void onRefresh() {
            mPage = 1;
            getDataFromShopList(mPage);
        }

        @Override
        public void onLoadMore() {
            mPage = mPage + 1;
            getDataFromShopList(mPage);
        }
    }
}
