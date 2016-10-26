package com.fosung.usedemo.amodule.demo_view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.fosung.usedemo.amodule.demo_view.fragment.PullRecyclerFragment;
import com.fosung.usedemo.amodule.demo_view.fragment.SuperRecyclerFragment;

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
            return SuperRecyclerFragment.newInstance();
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
