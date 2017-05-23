/***********************************************************
 * author   colin
 * company  fosung
 * email    wanglin2046@126.com
 * date     16-7-19 上午9:15
 **********************************************************/

/*    
 * 
 * @author		: WangLin  
 * @Company: 	：FCBN
 * @date		: 2015年5月13日 
 * @version 	: V1.0
 */
package com.zcolin.usedemo.amodule.mvc.main.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zcolin.frame.app.BaseFrameFrag;
import com.zcolin.usedemo.amodule.mvc.main.fragment.ApiFragment;
import com.zcolin.usedemo.amodule.mvc.main.fragment.HttpDBFragment;
import com.zcolin.usedemo.amodule.mvc.main.fragment.VideoImageFragment;
import com.zcolin.usedemo.amodule.mvc.main.fragment.ViewFragment;


/**
 * 主页面 ViewPager适配器
 */
public class MainPagerAdapter extends FragmentPagerAdapter {
    private BaseFrameFrag[] arrTabFrag = new BaseFrameFrag[4];

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return arrTabFrag.length;
    }

    @Override
    public Fragment getItem(int arg0) {
        return getFragByPosition(arg0);
    }

    /**
     * 根据位置获取Frag
     *
     * @param pos frag在viewpager中的位置
     */
    public BaseFrameFrag getFragByPosition(int pos) {
        if (arrTabFrag[pos] == null) {
            if (pos == 0) {
                arrTabFrag[pos] = ViewFragment.newInstance();
            } else if (pos == 1) {
                arrTabFrag[pos] = HttpDBFragment.newInstance();
            } else if (pos == 2) {
                arrTabFrag[pos] = VideoImageFragment.newInstance();
            } else if (pos == 3) {
                arrTabFrag[pos] = ApiFragment.newInstance();
            }
        }
        return arrTabFrag[pos];
    }
}
