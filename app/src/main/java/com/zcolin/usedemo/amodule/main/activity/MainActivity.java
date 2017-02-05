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

import com.zcolin.frame.app.BaseFrameFrag;
import com.zcolin.frame.utils.DisplayUtil;
import com.zcolin.usedemo.R;
import com.zcolin.usedemo.amodule.base.BaseFullScreenActivity;
import com.zcolin.usedemo.amodule.base.BaseNoToolBarActivity;
import com.zcolin.usedemo.amodule.base.BaseSecondLevelActivity;
import com.zcolin.usedemo.amodule.base.BaseToolBarActivity;
import com.zcolin.usedemo.amodule.main.adapter.MainPagerAdapter;
import com.zcolin.usedemo.amodule.main.fragment.HttpDBFragment;
import com.zcolin.usedemo.amodule.main.fragment.MineFragment;
import com.zcolin.usedemo.amodule.main.fragment.VideoImageFragment;
import com.zcolin.usedemo.amodule.main.fragment.ViewFragment;
import com.zcolin.gui.ZTabView;
import com.zcolin.gui.ZViewPager;

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
    public static final int[] TAB_POSITION = new int[]{0, 1, 2};

    private BaseFrameFrag[] arrTabFrag = new BaseFrameFrag[TAB_POSITION.length];
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
        MainPagerAdapter mainPagerAdapter = new MainPagerAdapter(this, getSupportFragmentManager());
        mViewPager.setAdapter(mainPagerAdapter);
        setUpTab();
    }

    public void setUpTab() {
        tabView.initAsTabIcon(mViewPager);
        tabView.setOnPageChangeListener(new MainPagerListener());

        tabView.addZTab(getNewTab("View"));
        tabView.addZTab(getNewTab("Db_Http"));
        tabView.addZTab(getNewTab("Video_Image"));
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

    /**
     * 根据位置获取Frag
     *
     * @param pos frag在viewpager中的位置
     */
    public BaseFrameFrag getFragByPosition(int pos) {
        if (arrTabFrag[pos] == null) {
            arrTabFrag[pos] = getNewFragByPos(pos);
        }
        return arrTabFrag[pos];
    }

    /*
     * 根据传入的位置创建新的Frag
     */
    private BaseFrameFrag getNewFragByPos(int i) {
        BaseFrameFrag frag = null;
        if (i == TAB_POSITION[0]) {
            frag = ViewFragment.newInstance();
        } else if (i == TAB_POSITION[1]) {
            frag = HttpDBFragment.newInstance();
        } else if (i == TAB_POSITION[2]) {
            frag = VideoImageFragment.newInstance();
        } else if (i == TAB_POSITION[3]) {
            frag = MineFragment.newInstance();
        }
        return frag;
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
            if (arg0 == TAB_POSITION[1]) {

            } else {

            }
        }
    }
}
