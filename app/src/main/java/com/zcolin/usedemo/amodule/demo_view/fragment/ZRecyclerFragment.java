/*
 * **********************************************************
 *   author   colin
 *   company  fosung
 *   email    wanglin2046@126.com
 *   date     16-10-11 下午4:00
 * *********************************************************
 */
package com.zcolin.usedemo.amodule.demo_view.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.zcolin.frame.app.BaseFrameLazyLoadFrag;
import com.zcolin.usedemo.R;
import com.zcolin.usedemo.amodule.demo_view.adapter.ZRecyclerAdapter;
import com.zcolin.gui.zrecyclerview.BaseRecyclerAdapter;
import com.zcolin.gui.zrecyclerview.ZRecyclerView;

import java.util.ArrayList;


/**
 * SuperRecyclerView Demo
 */
public class ZRecyclerFragment extends BaseFrameLazyLoadFrag {

    private ZRecyclerView    zRecyclerView;
    private ZRecyclerAdapter mRecyclerViewAdapter;
    private int mPage = 1;

    public static ZRecyclerFragment newInstance() {
        Bundle args = new Bundle();
        ZRecyclerFragment fragment = new ZRecyclerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getRootViewLayId() {
        return R.layout.fragment_superrecycler;
    }

    @Override
    protected void lazyLoad() {
        zRecyclerView = getView(R.id.pullLoadMoreRecyclerView);


        //recyclerView.setGridLayout(true, 2);  //默认为LinearLayoutManager
        zRecyclerView.setOnPullLoadMoreListener(new PullLoadMoreListener());

        //设置数据为空时的EmptyView，DataObserver是注册在adapter之上的，也就是setAdapter是注册上，notifyDataSetChanged的时候才会生效
        zRecyclerView.setEmptyView(mActivity, R.layout.view_pullrecycler_empty);

        //设置HeaderView和footerView
        zRecyclerView.setHeaderView(mActivity, R.layout.view_recyclerheader);
        zRecyclerView.setFooterView(mActivity, R.layout.view_recyclerfooter);

        //recyclerView.setLoadMoreProgressView(view);
        //recyclerView.setIsShowNoMore(false);      //不显示已加载全部
        // recyclerView.setIsLoadMoreEnabled(false);//到底加载是否可用
        // recyclerView.setIsRefreshEnabled(false);//下拉刷新是否可用
        //recyclerView.setIsProceeConflict(true);   //处理与子控件的冲突，如viewpager
        //recyclerView.setLoadMoreFooter(customview implements ILoadMoreFooter);   //设置自定义的加载Footer
        //recyclerView.setLoadMoreText("正在加载...", "正在加载...", "*****已加载全部*****");//设置加载文字
        //recyclerView.addDefaultItemDecoration();//增加默认分割线
        zRecyclerView.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<String>() {
            @Override
            public void onItemClick(View covertView, int position, String data) {
                Toast.makeText(mActivity, data, Toast.LENGTH_SHORT)
                     .show();
            }
        });

        notifyData(new ArrayList<String>(), false);
        zRecyclerView.refreshWithPull();
        // recyclerView.refresh();//没有下拉刷新效果，直接刷新数据
        // recyclerView.setRefreshing(true);只有下拉刷新效果，不刷新数据
    }

    public void getDataFromShopList(final Activity activity, final int page) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        notifyData(setList(page), page == 1);
                        zRecyclerView.setPullLoadMoreCompleted();
                        if (page == 2) {
                            zRecyclerView.setNoMore(true);
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

    public void notifyData(ArrayList<String> list, boolean isClear) {
        if (mRecyclerViewAdapter == null) {
            mRecyclerViewAdapter = new ZRecyclerAdapter();
            mRecyclerViewAdapter.addDatas(list);
            zRecyclerView.setAdapter(mRecyclerViewAdapter);
        } else {
            if (isClear) {
                mRecyclerViewAdapter.setDatas(list);
            } else {
                mRecyclerViewAdapter.addDatas(list);
            }
            mRecyclerViewAdapter.notifyDataSetChanged();
        }
    }

    class PullLoadMoreListener implements ZRecyclerView.PullLoadMoreListener {
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
