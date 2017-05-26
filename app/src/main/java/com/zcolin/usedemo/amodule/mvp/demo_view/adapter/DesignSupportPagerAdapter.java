/*
 * *********************************************************
 *   author   colin
 *   company  fosung
 *   email    wanglin2046@126.com
 *   date     17-5-25 下午4:13
 * ********************************************************
 */

package com.zcolin.usedemo.amodule.mvp.demo_view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zcolin.usedemo.amodule.mvc.demo_view.fragment.PullRecyclerFragment;
import com.zcolin.usedemo.amodule.mvc.demo_view.fragment.ZRecyclerFragment;

/**
 * 
 */
public class DesignSupportPagerAdapter extends FragmentPagerAdapter {
    public DesignSupportPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return PullRecyclerFragment.newInstance();
        } else {
            return ZRecyclerFragment.newInstance();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "PullRecyclerView";
        } else {
            return "SuperRecyclerView";
        }
    }
}
