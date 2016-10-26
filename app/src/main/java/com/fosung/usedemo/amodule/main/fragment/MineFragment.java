/***********************************************************
 * author   colin
 * company  fosung
 * email    wanglin2046@126.com
 * date     16-7-18 下午1:47
 **********************************************************/

package com.fosung.usedemo.amodule.main.fragment;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import com.fosung.frame.app.BaseFrameLazyLoadFrag;
import com.fosung.usedemo.R;

import java.util.ArrayList;


/**
 * 个人演示页
 */
public class MineFragment extends BaseFrameLazyLoadFrag {
    private LinearLayout llContent;
    private ArrayList<Button> listButton = new ArrayList<>();

    public static MineFragment newInstance() {
        Bundle args = new Bundle();
        MineFragment fragment = new MineFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getRootViewLayId() {
        return R.layout.activity_common;
    }

    @Override
    protected void lazyLoad() {
    }

}
