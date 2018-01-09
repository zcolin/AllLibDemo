/*
 * *********************************************************
 *   author   colin
 *   company  telchina
 *   email    wanglin2046@126.com
 *   date     18-1-9 下午5:03
 * ********************************************************
 */

package com.zcolin.usedemo.amodule.mvvm.demo_view.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.Toolbar;

import com.zcolin.frame.util.ToastUtil;
import com.zcolin.gui.ZViewPager;
import com.zcolin.usedemo.R;
import com.zcolin.usedemo.amodule.base.ActivityParam;
import com.zcolin.usedemo.amodule.base.BaseActivity;
import com.zcolin.usedemo.amodule.mvvm.demo_view.adapter.DesignSupportPagerAdapter;

/**
 * MD风格的RecyclerView，PullRecycler和SuperRecycler的Demo
 * 尽量使用PullRecyclerView，如果实在要下拉的，则使用SuperRecyclerView
 */
@ActivityParam(isShowToolBar = false)
public class DesignSupportActivity1 extends BaseActivity {

    private Toolbar              toolbar;
    private TabLayout            tabLayout;
    private ZViewPager           viewPager;
    private FloatingActionButton fabTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_designsupport1);

        initView();
    }

    private void initView() {
        toolbar = getView(R.id.toolbar);
        tabLayout = getView(R.id.tabs);
        viewPager = getView(R.id.viewpager);
        fabTest = getView(R.id.fab);

        toolbar.setTitle("DesignSupport");
        setSupportActionBar(toolbar);

        //设置TabLayout的模式  
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());

        FragmentPagerAdapter adapter = new DesignSupportPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        fabTest.setOnClickListener(v -> Snackbar.make(v, "FloatActionBar-click", Snackbar.LENGTH_LONG)
                                                .setAction("toast", v1 -> ToastUtil.toastShort("button-click"))
                                                .show());
    }
}
