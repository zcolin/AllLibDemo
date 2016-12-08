/***********************************************************
 * author   colin
 * company  fosung
 * email    wanglin2046@126.com
 * date     16-7-18 下午5:24
 **********************************************************/
package com.fosung.usedemo.amodule.demo_view.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;

import com.fosung.frame.app.BaseFrameLazyLoadFrag;
import com.fosung.frame.utils.ToastUtil;
import com.fosung.gui.ZBanner;
import com.fosung.gui.pullrecyclerview.BaseRecyclerAdapter;
import com.fosung.gui.pullrecyclerview.PullRecyclerView;
import com.fosung.usedemo.R;
import com.fosung.usedemo.amodule.demo_view.adapter.PullRecyclerAdapter;

import java.util.ArrayList;


/**
 * PullRecyclerView Demo
 */
public class PullRecyclerFragment extends BaseFrameLazyLoadFrag {

    private PullRecyclerView    mPullRecyclerView;
    private PullRecyclerAdapter mRecyclerViewAdapter;
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
    protected void lazyLoad() {

        mPullRecyclerView = getView(R.id.pullLoadMoreRecyclerView);
        //设置下拉刷新是否可见
        //mPullRecyclerView.setRefreshing(true);
        //设置是否可以下拉刷新
        //mPullRecyclerView.setPullRefreshEnable(true);
        //设置是否可以上拉刷新
        //mPullRecyclerView.setPushRefreshEnable(false);
        //显示下拉刷新
        mPullRecyclerView.setLinearLayout();

//        mPullRecyclerView.setPullRefreshEnable(true);
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
                addDataToRecyclerView(setList(page), page == 1);
                mPullRecyclerView.setPullLoadMoreCompleted();
                if (page == 2) {
                    mPullRecyclerView.setNoMore(true);
                }
            }
        }, 1000);
    }

    public void addDataToRecyclerView(ArrayList<String> list, boolean isClear) {
        if (mRecyclerViewAdapter == null) {
            mRecyclerViewAdapter = new PullRecyclerAdapter();
            View bannerParent = LayoutInflater.from(mActivity)
                                              .inflate(R.layout.view_banner, null);
            banner = (ZBanner) bannerParent.findViewById(R.id.view_banner);
            mRecyclerViewAdapter.setHeaderView(bannerParent);
            mRecyclerViewAdapter.addDatas(list);
            mPullRecyclerView.setAdapter(mRecyclerViewAdapter);
            mRecyclerViewAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<String>() {
                @Override
                public void onItemClick(View covertView, int position, String data) {
                    ToastUtil.toastShort(position + ":" + data);
                }
            });
            startBanner();
        } else {
            if (isClear) {
                mRecyclerViewAdapter.setDatas(list);
            } else {
                mRecyclerViewAdapter.addDatas(list);
            }
        }
    }

    public void startBanner() {
        ArrayList<String> listUrl = getListUrl();
        banner.setBannerStyle(ZBanner.NUM_INDICATOR_TITLE)
              .setIndicatorGravity(ZBanner.CENTER)
              .setBannerTitle(listUrl)
              .setDelayTime(4000)
              .setOnBannerClickListener(new ZBanner.OnBannerClickListener() {
                  @Override
                  public void OnBannerClick(View view, int position) {
                      ToastUtil.toastShort("点击了第" + (position + 1) + "张图片");
                  }
              })
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
    private ArrayList<String> setList(int page) {
        ArrayList<String> dataList = new ArrayList<>();
        int start = 10 * (page - 1);
        for (int i = start; i < 10 * page; i++) {
            dataList.add("Frist" + i);
        }
        return dataList;
    }

    class PullLoadMoreListener implements PullRecyclerView.PullLoadMoreListener {
        @Override
        public void onRefresh() {
            mPage = 1;
            getDataFromShopList(mActivity, mPage);
        }

        @Override
        public void onLoadMore() {
            mPage = mPage + 1;
            getDataFromShopList(mActivity, mPage);
        }
    }
}
