/*
 * *********************************************************
 *   author   colin
 *   company  telchina
 *   email    wanglin2046@126.com
 *   date     18-1-9 下午5:03
 * ********************************************************
 */
package com.zcolin.usedemo.amodule.mvvm.demo_view.adapter;


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