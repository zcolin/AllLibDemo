/***********************************************************
 * author   colin
 * company  fosung
 * email    wanglin2046@126.com
 * date     16-7-18 下午5:24
 **********************************************************/
package com.zcolin.usedemo.amodule.demo_view.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.zcolin.frame.app.BaseFrameLazyLoadFrag;
import com.zcolin.frame.utils.ToastUtil;
import com.zcolin.usedemo.R;
import com.zcolin.usedemo.amodule.demo_view.adapter.PullRecyclerAdapter;
import com.zcolin.gui.ZBanner;
import com.zcolin.gui.pullrecyclerview.BaseRecyclerAdapter;
import com.zcolin.gui.pullrecyclerview.PullRecyclerView;
import com.zcolin.gui.pullrecyclerview.progressindicator.ProgressStyle;

import java.util.ArrayList;

/**
 * PullRecyclerView Demo
 */
public class PullRecyclerFragment extends BaseFrameLazyLoadFrag {

    private PullRecyclerView    recyclerView;
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

        recyclerView = getView(R.id.pullLoadMoreRecyclerView);
        // recyclerView.setGridLayout(false);//默认为LinearLayoutManager
        recyclerView.setOnPullLoadMoreListener(new PullLoadMoreListener());

        //设置数据为空时的EmptyView，DataObserver是注册在adapter之上的，也就是setAdapter是注册上，notifyDataSetChanged的时候才会生效
        recyclerView.setEmptyView(mActivity, R.layout.view_pullrecycler_empty);

        //设置HeaderView和footerView
        recyclerView.setHeaderView(mActivity, R.layout.view_recyclerheader);
        recyclerView.setFooterView(mActivity, R.layout.view_recyclerfooter);

        //下拉和到底加载的进度条样式，默认为 ProgressStyle.BallSpinFadeLoaderIndicator
        recyclerView.setRefreshProgressStyle(ProgressStyle.LineScaleIndicator);
        recyclerView.setLoadMoreProgressStyle(ProgressStyle.LineScaleIndicator);

        recyclerView.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<String>() {
            @Override
            public void onItemClick(View covertView, int position, String data) {
                Toast.makeText(mActivity, data, Toast.LENGTH_SHORT)
                     .show();
            }
        });

        // recyclerView.setIsShowNoMore(false);//不显示《已加载全部》
        // recyclerView.setIsLoadMoreEnabled(false);//到底加载是否可用
        // recyclerView.setIsRefreshEnabled(false);//下拉刷新是否可用

        //下拉刷新的文字显示
        recyclerView.setRefreshHeaderText("下拉刷新", "释放立即刷新", "正在刷新", "刷新完成");

        //绑定Adapter
        notifyData(new ArrayList<String>(), false);

        recyclerView.refreshWithPull();     //有下拉效果的数据刷新
        // recyclerView.refresh();          //没有下拉刷新效果，直接刷新数据
        // recyclerView.setRefreshing(true);//只有下拉刷新效果，不刷新数据
    }

    public void getDataFromShopList(final Activity activity, final int page) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                notifyData(setList(page), page == 1);
                recyclerView.setPullLoadMoreCompleted();
                if (page == 2) {
                    recyclerView.setNoMore(true);
                }
            }
        }, 1000);
    }

    public void notifyData(ArrayList<String> list, boolean isClear) {
        if (mRecyclerViewAdapter == null) {
            mRecyclerViewAdapter = new PullRecyclerAdapter();
            mRecyclerViewAdapter.addDatas(list);
            recyclerView.setAdapter(mRecyclerViewAdapter);
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
