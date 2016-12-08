package com.fosung.gui.pullrecyclerview;


import com.fosung.gui.swiperefreshlayout.SwipeRefreshLayout;

/**
 * Created by WuXiaolong on 2015/7/7.
 */
public class SwipeRefreshLayoutOnRefresh implements SwipeRefreshLayout.OnRefreshListener {
    private PullRecyclerView mPullRecyclerView;

    public SwipeRefreshLayoutOnRefresh(PullRecyclerView pullLoadMoreRecyclerView) {
        this.mPullRecyclerView = pullLoadMoreRecyclerView;
    }

    @Override
    public void onRefresh() {
        if (!mPullRecyclerView.isRefresh()) {
            mPullRecyclerView.setIsRefresh(true);
            mPullRecyclerView.refresh();
        }
    }
}
