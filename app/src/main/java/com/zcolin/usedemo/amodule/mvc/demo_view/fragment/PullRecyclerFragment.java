/*
 * *********************************************************
 *   author   colin
 *   company  telchina
 *   email    wanglin2046@126.com
 *   date     18-1-9 下午5:02
 * ********************************************************
 */
package com.zcolin.usedemo.amodule.mvc.demo_view.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.zcolin.frame.app.BaseFrameFrag;
import com.zcolin.frame.util.ToastUtil;
import com.zcolin.gui.ZBanner;
import com.zcolin.gui.pullrecyclerview.BaseRecyclerAdapter;
import com.zcolin.gui.pullrecyclerview.PullRecyclerView;
import com.zcolin.gui.pullrecyclerview.progressindicator.ProgressStyle;
import com.zcolin.usedemo.R;
import com.zcolin.usedemo.amodule.mvc.demo_view.adapter.PullRecyclerAdapter;
import com.zcolin.usedemo.entity.ListItemData;

import java.util.ArrayList;

/**
 * PullRecyclerView Demo
 */
public class PullRecyclerFragment extends BaseFrameFrag {

    private PullRecyclerView    recyclerView;
    private PullRecyclerAdapter recyclerViewAdapter;
    private ZBanner             banner;
    private int mPage = 1;

    public static PullRecyclerFragment newInstance() {
        Bundle args = new Bundle();
        PullRecyclerFragment fragment = new PullRecyclerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (banner != null && !banner.isStart() && banner.isInit()) {
            banner.startAutoPlay();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (banner != null) {
            banner.stopAutoPlay();
        }
    }

    @Override
    protected int getRootViewLayId() {
        return R.layout.fragment_pullrecycler;
    }

    @Override
    protected void lazyLoad(@Nullable Bundle savedInstanceState) {
        recyclerView = getView(R.id.pullLoadMoreRecyclerView);
        // recyclerView.setGridLayout(false);//默认为LinearLayoutManager
        //设置数据为空时的EmptyView，DataObserver是注册在adapter之上的，也就是setAdapter是注册上，notifyDataSetChanged的时候才会生效
        recyclerView.setEmptyView(mActivity, R.layout.view_pullrecycler_empty);
        //设置HeaderView和footerView
        View view = mActivity.getLayoutInflater().inflate(R.layout.view_recycler_header_banner, null);
        banner = view.findViewById(R.id.view_banner);
        recyclerView.addHeaderView(view);
        startBanner();
        recyclerView.addFooterView(mActivity, R.layout.view_recyclerfooter);
        //下拉和到底加载的进度条样式，默认为 ProgressStyle.BallSpinFadeLoaderIndicator
        recyclerView.setRefreshProgressStyle(ProgressStyle.LineScaleIndicator);
        recyclerView.setLoadMoreProgressStyle(ProgressStyle.LineScaleIndicator);
        recyclerView.setOnItemClickListener((BaseRecyclerAdapter.OnItemClickListener<String>) (covertView, position, data) -> Toast.makeText(mActivity, data,
                Toast.LENGTH_SHORT)
                                                                                                                                   .show());
        recyclerView.setOnPullLoadMoreListener(new PullRecyclerView.PullLoadMoreListener() {
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

        // recyclerView.setIsShowNoMore(false);//不显示《已加载全部》
        // recyclerView.setIsLoadMoreEnabled(false);//到底加载是否可用
        // recyclerView.setIsRefreshEnabled(false);//下拉刷新是否可用

        //下拉刷新的文字显示
        recyclerView.setRefreshHeaderText("下拉刷新", "释放立即刷新", "正在刷新", "刷新完成");
        recyclerView.refreshWithPull();     //有下拉效果的数据刷新
        // recyclerView.refresh();          //没有下拉刷新效果，直接刷新数据
        // recyclerView.setRefreshing(true);//只有下拉刷新效果，不刷新数据
    }

    public void requestData(final int page) {
        new Handler().postDelayed(() -> {
            setDataToRecyclerView(setList(page), page == 1);
            recyclerView.setPullLoadMoreCompleted();
            if (page == 2) {
                recyclerView.setNoMore(true);
            }
        }, 1000);
    }

    public void setDataToRecyclerView(ArrayList<ListItemData> list, boolean isClear) {
        if (recyclerViewAdapter == null) {
            recyclerViewAdapter = new PullRecyclerAdapter();
            recyclerView.setAdapter(recyclerViewAdapter);
        }

        if (isClear) {
            recyclerViewAdapter.setDatas(list);
        } else {
            recyclerViewAdapter.addDatas(list);
        }
    }

    public void startBanner() {
        ArrayList<String> listUrl = getListUrl();
        banner.setBannerStyle(ZBanner.NUM_INDICATOR_TITLE)
              .setIndicatorGravity(ZBanner.CENTER)
              .setBannerTitle(listUrl)
              .setDelayTime(4000)
              .setOnBannerClickListener((view, position) -> ToastUtil.toastShort("点击了第" + (position + 1) + "张图片"))
              .setImages(listUrl)
              .startAutoPlay();
    }

    private ArrayList<String> getListUrl() {
        String url_1 = "http://img.ycwb.com/news/attachement/jpg/site2/20110226/90fba60155890ed3082500.jpg";
        String url_2 = "http://cdn.duitang.com/uploads/item/201112/04/20111204012148_wkT88.jpg";
        String url_3 = "http://img6.faloo.com/Picture/680x580/0/449/449476.jpg";
        ArrayList<String> listUrl = new ArrayList<>();
        listUrl.add(url_1);
        listUrl.add(url_2);
        listUrl.add(url_3);
        return listUrl;
    }

    //制造假数据
    private ArrayList<ListItemData> setList(int page) {
        ArrayList<ListItemData> dataList = new ArrayList<>();
        int start = 10 * (page - 1);
        for (int i = start; i < 10 * page; i++) {
            ListItemData data = new ListItemData();
            data.title = "Frist" + i;
            dataList.add(data);
        }
        return dataList;
    }

}
