/*
 * *********************************************************
 *   author   colin
 *   company  fosung
 *   email    wanglin2046@126.com
 *   date     16-12-20 下午4:31
 * ********************************************************
 */
package com.zcolin.usedemo.amodule.demo_view.adapter;


import android.widget.ImageView;
import android.widget.TextView;

import com.fosung.frame.imageloader.ImageLoaderUtils;
import com.zcolin.usedemo.R;
import com.zcolin.gui.zrecyclerview.BaseRecyclerAdapter;


/**
 * PullRecyclerAdapter示例
 * <p>
 * pullrecyclerView的Adapter
 */
public class ZRecyclerAdapter extends BaseRecyclerAdapter<String> {

    @Override
    public int getItemLayoutId(int viewType) {
        return R.layout.view_recycler_item_1;
    }

    @Override
    public void setUpData(CommonHolder holder, int position, int viewType, String data) {
        TextView textView = getView(holder, R.id.textView);
        ImageView imageView = getView(holder, R.id.imageView);
        ImageLoaderUtils.displayCircleImage(holder.itemView.getContext(), "http://img1.imgtn.bdimg.com/it/u=1480985633,1206349730&fm=21&gp=0.jpg", imageView);
        textView.setText(data);
    }
}