/*
 * *********************************************************
 *   author   colin
 *   company  fosung
 *   email    wanglin2046@126.com
 *   date     17-5-22 下午3:33
 * ********************************************************
 */
package com.zcolin.usedemo.amodule.mvp.demo_view.activity.itemarrange;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zcolin.frame.app.ResultActivityHelper;
import com.zcolin.outlib.views.itemarrange.AppsItemEntity;
import com.zcolin.usedemo.R;
import com.zcolin.usedemo.amodule.base.BaseActivity;

import java.util.ArrayList;


/**
 * MainActivity->应用
 */
public class ItemActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itemshow);
        setToolbarTitle("应用");
        initView();
    }


    protected void initView() {
        initZFBItem();
        initDragItem();
    }


    private void initZFBItem() {
        int columnCount = 4;//列数
        int fixedCount = 1;//固定图标的数量

        LinearLayout llOperateTop = getView(R.id.ll_operate_top);
        LinearLayout llOperateBottom = getView(R.id.ll_operate_bottom);

        ArrayList<AppsItemEntity> listMyItems = ItemZFBMgr.getMyItems();
        int size = listMyItems.size() >= columnCount * 2 - fixedCount ? columnCount * 2 - fixedCount : listMyItems.size();
        int dimensSmall = (int) mActivity.getResources()
                                         .getDimension(R.dimen.dimens_small);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1);
        params.rightMargin = dimensSmall;
        llOperateBottom.removeAllViews();
        llOperateTop.removeAllViews();
        for (int i = 0; i < size; i++) {
            final AppsItemEntity entity = listMyItems.get(i);
            TextView tv = getItemTextView(entity, dimensSmall);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ItemZFBMgr.onItemClick(mActivity, entity);
                }
            });
            if (i <= columnCount - 1) {
                llOperateTop.addView(tv, params);
            } else if (i >= columnCount && i <= columnCount * 2 - fixedCount - 1) {
                llOperateBottom.addView(tv, params);
            }
        }

        //增加全部view和 补位空白view
        TextView tvAll = getItemTextView(ItemZFBMgr.getFixedItem(), dimensSmall);
        tvAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityWithCallback(new Intent(mActivity, ItemArrangeZFBActivity.class), new ResultActivityHelper.ResultActivityListener() {
                    @Override
                    public void onResult(int resultCode, Intent data) {
                        if (resultCode == Activity.RESULT_OK) {
                            initZFBItem();
                        }
                    }
                });
            }
        });
        if (size <= columnCount - fixedCount) {
            llOperateBottom.setVisibility(View.GONE);
            llOperateTop.addView(tvAll, params);
            for (int j = 0; j < columnCount - size - fixedCount; j++) {
                TextView tv1 = getItemTextView(null, dimensSmall);
                llOperateTop.addView(tv1, params);
            }
        } else if (size <= columnCount * 2 - fixedCount) {
            llOperateBottom.setVisibility(View.VISIBLE);
            llOperateBottom.addView(tvAll, params);
            for (int j = 0; j < columnCount * 2 - size - fixedCount; j++) {
                TextView tv1 = getItemTextView(null, dimensSmall);
                llOperateBottom.addView(tv1, params);
            }
        }
    }

    private void initDragItem() {
        int columnCount = 4;//列数
        int fixedCount = 1;//固定图标的数量

        LinearLayout llOperateTop = getView(R.id.ll_operate_top_1);
        LinearLayout llOperateBottom = getView(R.id.ll_operate_bottom_1);

        ArrayList<AppsItemEntity> listMyItems = ItemDragMgr.getMyItems();
        int size = listMyItems.size() >= columnCount * 2 - fixedCount ? columnCount * 2 - fixedCount : listMyItems.size();
        int dimensSmall = (int) mActivity.getResources()
                                         .getDimension(R.dimen.dimens_small);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1);
        params.rightMargin = dimensSmall;
        llOperateBottom.removeAllViews();
        llOperateTop.removeAllViews();
        for (int i = 0; i < size; i++) {
            final AppsItemEntity entity = listMyItems.get(i);
            TextView tv = getItemTextView(entity, dimensSmall);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ItemDragMgr.onItemClick(mActivity, entity);
                }
            });
            if (i <= columnCount - 1) {
                llOperateTop.addView(tv, params);
            } else if (i >= columnCount && i <= columnCount * 2 - fixedCount - 1) {
                llOperateBottom.addView(tv, params);
            }
        }

        //增加全部view和 补位空白view
        TextView tvAll = getItemTextView(ItemDragMgr.getFixedItem(), dimensSmall);
        tvAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityWithCallback(new Intent(mActivity, ItemArrangeDragActivity.class), new ResultActivityHelper.ResultActivityListener() {
                    @Override
                    public void onResult(int resultCode, Intent data) {
                        if (resultCode == Activity.RESULT_OK) {
                            initDragItem();
                        }
                    }
                });
            }
        });
        if (size <= columnCount - fixedCount) {
            llOperateBottom.setVisibility(View.GONE);
            llOperateTop.addView(tvAll, params);
            for (int j = 0; j < columnCount - size - fixedCount; j++) {
                TextView tv1 = getItemTextView(null, dimensSmall);
                llOperateTop.addView(tv1, params);
            }
        } else if (size <= columnCount * 2 - fixedCount) {
            llOperateBottom.setVisibility(View.VISIBLE);
            llOperateBottom.addView(tvAll, params);
            for (int j = 0; j < columnCount * 2 - size - fixedCount; j++) {
                TextView tv1 = getItemTextView(null, dimensSmall);
                llOperateBottom.addView(tv1, params);
            }
        }
    }

    private TextView getItemTextView(AppsItemEntity entity, int dimensSmall) {
        TextView tv = new TextView(mActivity);
        if (entity != null) {
            tv.setCompoundDrawablesWithIntrinsicBounds(0, entity.iconDrawable, 0, 0);
            tv.setCompoundDrawablePadding(dimensSmall);
            tv.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
            tv.setMinLines(2);
            tv.setText(entity.name);
        }
        return tv;
    }
}
