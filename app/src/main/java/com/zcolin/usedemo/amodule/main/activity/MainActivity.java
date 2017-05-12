/***********************************************************
 * author   colin
 * company  fosung
 * email    wanglin2046@126.com
 * date     16-7-19 上午9:15
 **********************************************************/

package com.zcolin.usedemo.amodule.main.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;

import com.zcolin.frame.utils.DisplayUtil;
import com.zcolin.gui.ZTabView;
import com.zcolin.gui.ZViewPager;
import com.zcolin.usedemo.R;
import com.zcolin.usedemo.amodule.base.BaseActivity;
import com.zcolin.usedemo.amodule.main.adapter.MainPagerAdapter;

/**
 * 程序主页面
 */
public class MainActivity extends BaseActivity {

    private ZViewPager mViewPager;
    private ZTabView   tabView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected int getRootViewLayId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mViewPager = (ZViewPager) findViewById(R.id.view_pager);
        tabView = (ZTabView) findViewById(R.id.view_tabview);
    }

    @Override
    public boolean isShowToolBar() {
        return false;
    }

    private void initData() {
        MainPagerAdapter mainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mainPagerAdapter);
        setUpTab();
    }

    public void setUpTab() {
        tabView.initAsTabIcon(mViewPager);
        tabView.setOnPageChangeListener(new MainPagerListener());

        tabView.addZTab(getNewTab("View"));
        tabView.addZTab(getNewTab("Db_Http"));
        tabView.addZTab(getNewTab("Video_Image"));
        tabView.addZTab(getNewTab("Api"));
    }

    /*
     * 创建ZTab
     */
    private ZTabView.ZTab getNewTab(String str) {
        float textSize = getResources().getDimension(R.dimen.textsize_small);
        ZTabView.ZTab tab = tabView.getNewTextTab(str);
        tab.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        tab.setTextColor(getResources().getColorStateList(R.color.main_text_color_selector));
        int padding = DisplayUtil.dip2px(this, 10);
        tab.setPadding(padding, padding, padding, padding);
        tab.setBackgroundColor(getResources().getColor(R.color.blue_mid));
        return tab;
    }


    /*
    * ViewPager监听类 
    */
    private class MainPagerListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(int arg0) {
            if (arg0 == 0) {

            } else {

            }
        }
    }
}
