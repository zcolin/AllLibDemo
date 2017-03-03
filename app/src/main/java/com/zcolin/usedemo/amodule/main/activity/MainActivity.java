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
import com.zcolin.usedemo.amodule.base.BaseFullScreenActivity;
import com.zcolin.usedemo.amodule.base.BaseNoToolBarActivity;
import com.zcolin.usedemo.amodule.base.BaseSecondLevelActivity;
import com.zcolin.usedemo.amodule.base.BaseToolBarActivity;
import com.zcolin.usedemo.amodule.main.adapter.MainPagerAdapter;

/**
 * 程序主页面
 * 需要带ToolBar的继承 {@link BaseToolBarActivity}
 * <p/>
 * 需要ToolBar带返回按钮的继承 {@link BaseSecondLevelActivity}
 * <p/>
 * 需要没有ToolBar的继承 {@link BaseNoToolBarActivity}
 * <p/>
 * 需要全屏的继承 {@link BaseFullScreenActivity}
 */
public class MainActivity extends BaseNoToolBarActivity {
   
    private ZViewPager mViewPager;
    private ZTabView   tabView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRes();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initRes() {
        mViewPager = (ZViewPager) findViewById(R.id.view_pager);
        tabView = (ZTabView) findViewById(R.id.view_tabview);
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
