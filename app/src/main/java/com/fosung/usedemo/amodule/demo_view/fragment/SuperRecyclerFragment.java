/*
 * **********************************************************
 *   author   colin
 *   company  fosung
 *   email    wanglin2046@126.com
 *   date     16-10-11 下午4:00
 * *********************************************************
 */
package com.fosung.usedemo.amodule.demo_view.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.fosung.frame.app.BaseFrameLazyLoadFrag;
import com.fosung.frame.utils.ToastUtil;
import com.fosung.gui.pullrecyclerview.BaseRecyclerAdapter;
import com.fosung.gui.superrecyclerview.SuperRecyclerView;
import com.fosung.usedemo.R;
import com.fosung.usedemo.amodule.demo_view.adapter.PullRecyclerAdapter;

import java.util.ArrayList;


/**
 * SuperRecyclerView Demo
 */
public class SuperRecyclerFragment extends BaseFrameLazyLoadFrag {

    private SuperRecyclerView   mPullRecyclerView;
    private PullRecyclerAdapter mRecyclerViewAdapter;
    private int mPage = 1;

    public static SuperRecyclerFragment newInstance() {
        Bundle args = new Bundle();
        SuperRecyclerFragment fragment = new SuperRecyclerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getRootViewLayId() {
        return R.layout.fragment_superrecycler;
    }

    @Override
    protected void lazyLoad() {
        mPullRecyclerView = getView(R.id.pullLoadMoreRecyclerView);
        mPullRecyclerView.setLinearLayout();
        mPullRecyclerView.setOnPullLoadMoreListener(new PullLoadMoreListener());
        mPullRecyclerView.setEmptyView(LayoutInflater.from(mActivity)
                                                     .inflate(R.layout.view_pullrecycler_empty, null));//setEmptyView
        
        addDataToRecyclerView(new ArrayList<String>(), false);
        mPullRecyclerView.setRefreshing(true);
        getDataFromShopList(mActivity, mPage);
    }

    public void getDataFromShopList(final Activity activity, final int page) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        addDataToRecyclerView(setList(page), page == 1);
                        mPullRecyclerView.setPullLoadMoreCompleted();
                        if (page == 2) {
                            mPullRecyclerView.setNoMore(true);
                        }
                    }
                });
            }
        }, 1000);
    }

    //制造假数据
    private ArrayList<String> setList(int page) {
        ArrayList<String> dataList = new ArrayList<>();
        int start = 10 * (page - 1);
        for (int i = start; i < 10 * page; i++) {
            dataList.add("Frist" + i);
        }
        return dataList;
    }

    public void addDataToRecyclerView(ArrayList<String> list, boolean isClear) {
        if (mRecyclerViewAdapter == null) {
            mRecyclerViewAdapter = new PullRecyclerAdapter();
            TextView tvHeader = new TextView(mActivity);
            tvHeader.setText("我是Header");
            tvHeader.setGravity(Gravity.CENTER);
            tvHeader.setBackgroundColor(getResources().getColor(R.color.blue_mid));
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
            mRecyclerViewAdapter.notifyDataSetChanged();
        }
    }

    class PullLoadMoreListener implements SuperRecyclerView.PullLoadMoreListener {
        @Override
        public void onRefresh() {
            mPage = 1;
            getDataFromShopList(mActivity, mPage);
        }

        @Override
        public void onLoadMore() {
            Log.e("wxl", "onLoadMore");
            mPage = mPage + 1;
            getDataFromShopList(mActivity, mPage);
        }
    }
}
