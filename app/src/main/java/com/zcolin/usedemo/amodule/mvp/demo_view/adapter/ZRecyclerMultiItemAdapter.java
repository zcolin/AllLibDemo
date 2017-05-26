/*
 * *********************************************************
 *   author   colin
 *   company  fosung
 *   email    wanglin2046@126.com
 *   date     17-5-25 下午4:13
 * ********************************************************
 */
package com.zcolin.usedemo.amodule.mvp.demo_view.adapter;


import android.graphics.Color;
import android.widget.TextView;

import com.zcolin.gui.zrecyclerview.BaseRecyclerAdapter;
import com.zcolin.usedemo.R;
import com.zcolin.usedemo.entity.ListItemData;


/**
 * PullRecyclerAdapter示例
 * <p>
 * pullrecyclerView的Adapter
 */
public class ZRecyclerMultiItemAdapter extends BaseRecyclerAdapter<ListItemData> {

    public static final int TYPE_1 = 1;
    public static final int TYPE_2 = 2;

    @Override
    public int getItemLayoutId(int type) {
        return R.layout.view_recycler_item;
    }


    @Override
    public int getItemViewType(int position) {
        if (position % 2 == 0) {
            return TYPE_1;
        } else {
            return TYPE_2;
        }
    }

    @Override
    public void setUpData(CommonHolder holder, int position, int viewType, ListItemData data) {
        if (viewType == TYPE_1) {
            TextView tvTitle = getView(holder, R.id.title);
            tvTitle.setTextColor(Color.RED);
            tvTitle.setText(data.title + "…………TYPE_1");
        } else {
            TextView tvTitle = getView(holder, R.id.title);
            tvTitle.setTextColor(Color.BLACK);
            tvTitle.setText(data.title + "—————TYPE_2");
        }
    }
}