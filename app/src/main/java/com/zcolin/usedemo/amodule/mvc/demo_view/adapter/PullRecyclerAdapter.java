/***********************************************************
 * author   colin
 * company  fosung
 * email    wanglin2046@126.com
 * date     16-7-18 下午5:25
 **********************************************************/
package com.zcolin.usedemo.amodule.mvc.demo_view.adapter;


import android.widget.ImageView;
import android.widget.TextView;

import com.zcolin.frame.imageloader.ImageLoaderUtils;
import com.zcolin.usedemo.R;
import com.zcolin.gui.pullrecyclerview.BaseRecyclerAdapter;
import com.zcolin.usedemo.entity.ListItemData;


/**
 * PullRecyclerAdapter示例
 * <p>
 * pullrecyclerView的Adapter
 */
public class PullRecyclerAdapter extends BaseRecyclerAdapter<ListItemData> {

    @Override
    public int getItemLayoutId(int viewType) {
        return R.layout.view_recycler_item_1;
    }

    @Override
    public void setUpData(CommonHolder holder, int position, int viewType, ListItemData data) {
        TextView textView = getView(holder, R.id.textView);
        ImageView imageView = getView(holder, R.id.imageView);
        ImageLoaderUtils.displayCircleImage(holder.itemView.getContext(), "http://img1.imgtn.bdimg.com/it/u=1480985633,1206349730&fm=21&gp=0.jpg", imageView);
        textView.setText(data.title);
    }
}