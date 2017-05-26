/*
 * **********************************************************
 *   author   colin
 *   company  fosung
 *   email    wanglin2046@126.com
 *   date     16-10-11 下午4:00
 * *********************************************************
 */
package com.zcolin.usedemo.amodule.mvc.demo_view.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.zcolin.frame.app.BaseFrameFrag;
import com.zcolin.gui.zrecyclerview.BaseRecyclerAdapter;
import com.zcolin.gui.zrecyclerview.ZRecyclerView;
import com.zcolin.usedemo.R;
import com.zcolin.usedemo.amodule.mvc.demo_view.adapter.ZRecyclerAdapter;
import com.zcolin.usedemo.entity.ListItemData;

import java.util.ArrayList;


/**
 * SuperRecyclerView Demo
 */
public class ZRecyclerFragment extends BaseFrameFrag {

    private ZRecyclerView    recyclerView;
    private ZRecyclerAdapter recyclerViewAdapter;
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
    protected void lazyLoad(@Nullable Bundle savedInstanceState) {
        recyclerView = getView(R.id.pullLoadMoreRecyclerView);
        //recyclerView.setGridLayout(true, 2);  //默认为LinearLayoutManager
        //设置数据为空时的EmptyView，DataObserver是注册在adapter之上的，也就是setAdapter是注册上，notifyDataSetChanged的时候才会生效
        recyclerView.setEmptyView(mActivity, R.layout.view_pullrecycler_empty);
        //设置HeaderView和footerView
        recyclerView.addHeaderView(mActivity, R.layout.view_recyclerheader);
        recyclerView.addFooterView(mActivity, R.layout.view_recyclerfooter);
        //recyclerView.setLoadMoreProgressView(view);
        //recyclerView.setIsShowNoMore(false);      //不显示已加载全部
        // recyclerView.setIsLoadMoreEnabled(false);//到底加载是否可用
        // recyclerView.setIsRefreshEnabled(false);//下拉刷新是否可用
        //recyclerView.setIsProceeConflict(true);   //处理与子控件的冲突，如viewpager
        //recyclerView.setLoadMoreFooter(customview implements ILoadMoreFooter);   //设置自定义的加载Footer
        //recyclerView.setLoadMoreText("正在加载...", "正在加载...", "*****已加载全部*****");//设置加载文字
        //recyclerView.addDefaultItemDecoration();//增加默认分割线
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
                mPage = 1;
                requestData(mPage);
            }

            @Override
            public void onLoadMore() {
                mPage = mPage + 1;
                requestData(mPage);
            }
        });
        recyclerView.refreshWithPull();
        // recyclerView.refresh();//没有下拉刷新效果，直接刷新数据
        // recyclerView.setRefreshing(true);只有下拉刷新效果，不刷新数据
    }

    public void requestData(final int page) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                setDataToRecyclerView(setList(page), page == 1);
                recyclerView.setPullLoadMoreCompleted();
                if (page == 2) {
                    recyclerView.setNoMore(true);
                }
            }
        }, 1000);
    }

    //制造假数据
    private ArrayList<ListItemData> setList(int page) {
        ArrayList<ListItemData> dataList = new ArrayList<>();
        int start = 10 * (page - 1);
        for (int i = start; i < 10 * page; i++) {
            ListItemData data = new ListItemData();
            data.title = "Frist" + i;
            data.id = i;
            dataList.add(data);
        }
        return dataList;
    }

    public void setDataToRecyclerView(ArrayList<ListItemData> list, boolean isClear) {
        if (recyclerViewAdapter == null) {
            recyclerViewAdapter = new ZRecyclerAdapter();
            recyclerView.setAdapter(recyclerViewAdapter);
        }

        if (isClear) {
            recyclerViewAdapter.setDatas(list);
        } else {
            recyclerViewAdapter.addDatas(list);
        }
    }

}
