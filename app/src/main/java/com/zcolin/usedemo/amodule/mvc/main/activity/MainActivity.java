/*
 * *********************************************************
 *   author   colin
 *   company  telchina
 *   email    wanglin2046@126.com
 *   date     18-1-9 下午5:02
 * ********************************************************
 */

package com.zcolin.usedemo.amodule.mvc.main.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;

import com.zcolin.frame.util.DisplayUtil;
import com.zcolin.gui.ZTabView;
import com.zcolin.gui.ZViewPager;
import com.zcolin.usedemo.R;
import com.zcolin.usedemo.amodule.base.ActivityParam;
import com.zcolin.usedemo.amodule.base.BaseActivity;
import com.zcolin.usedemo.amodule.mvc.main.adapter.MainPagerAdapter;

/**
 * 程序主页面
 */
@ActivityParam(isSecondLevelActivity = false)
public class MainActivity extends BaseActivity {

    private ZViewPager mViewPager;
    private ZTabView   tabView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setToolbarTitle(getString(R.string.app_name));

        initView();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    protected void initView() {
        mViewPager = getView(R.id.view_pager);
        tabView = getView(R.id.view_tabview);

        mViewPager.setCanScroll(false);
    }

    private void initData() {
        MainPagerAdapter mainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mainPagerAdapter);
        setUpTab();
    }

    public void setUpTab() {
        tabView.initAsTabIcon(mViewPager);
        tabView.setOnPageChangeListener(new MainPagerListener());

        tabView.addZTab(getNewTab("View", R.drawable.icon_tab_mainpage, R.drawable.icon_tab_mainpage_s));
        tabView.addZTab(getNewTab("Db_Http", R.drawable.icon_tab_special, R.drawable.icon_tab_special_s));
        tabView.addZTab(getNewTab("视频图片", R.drawable.icon_tab_video, R.drawable.icon_tab_video_s));
        tabView.addZTab(getNewTab("三方接口", R.drawable.icon_tab_mine, R.drawable.icon_tab_mine_s));
    }

    /*
     * 创建ZTab
     */
    private ZTabView.ZTab getNewTab(String str, int drawable, int drawableS) {
        float textSize = getResources().getDimension(R.dimen.textsize_small);
        ZTabView.ZTab tab = tabView.getNewIconTab(drawable, drawableS, str);
        tab.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        int padding = DisplayUtil.dip2px(this, 5);
        tab.setPadding(padding, padding, padding, padding);
        tab.setCompoundDrawablePadding(padding);
        tab.setTextColor(getResources().getColorStateList(R.color.main_text_color_selector));
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
