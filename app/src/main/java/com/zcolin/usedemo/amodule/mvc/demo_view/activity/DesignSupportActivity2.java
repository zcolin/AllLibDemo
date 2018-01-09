/*
 * *********************************************************
 *   author   colin
 *   company  telchina
 *   email    wanglin2046@126.com
 *   date     18-1-9 下午5:02
 * ********************************************************
 */

package com.zcolin.usedemo.amodule.mvc.demo_view.activity;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.zcolin.gui.ZViewPager;
import com.zcolin.usedemo.R;
import com.zcolin.usedemo.amodule.base.ActivityParam;
import com.zcolin.usedemo.amodule.base.BaseActivity;
import com.zcolin.usedemo.amodule.mvc.demo_view.adapter.DesignSupportPagerAdapter;


/**
 * MD风格的RecyclerView，PullRecycler和SuperRecycler的Demo
 * 尽量使用PullRecyclerView，如果实在要下拉的，则使用SuperRecyclerView
 */
@ActivityParam(isShowToolBar = false)
public class DesignSupportActivity2 extends BaseActivity {

    private Toolbar                 toolbar;
    private TabLayout               tabLayout;
    private ZViewPager              viewPager;
    private CollapsingToolbarLayout collapsingToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_designsupport2);

        init();
    }

    private void init() {
        toolbar = getView(R.id.toolbar);
        tabLayout = getView(R.id.tabs);
        viewPager = getView(R.id.viewpager);
        collapsingToolbar = getView(R.id.collapsing_toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        collapsingToolbar.setTitleEnabled(false);

        //设置TabLayout的模式  
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());

        FragmentPagerAdapter adapter = new DesignSupportPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        //Android.R.id.home对应应用程序图标的id
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
