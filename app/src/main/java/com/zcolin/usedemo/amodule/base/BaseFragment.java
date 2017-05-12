/*
 * *********************************************************
 *   author   colin
 *   company  fosung
 *   email    wanglin2046@126.com
 *   date     17-5-6 下午3:24
 * ********************************************************
 */

package com.zcolin.usedemo.amodule.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zcolin.frame.app.BaseFrameFrag;

public abstract class BaseFragment extends BaseFrameFrag {

    protected abstract void initView();

    @Override
    protected void createView(@Nullable Bundle savedInstanceState) {
        super.createView(savedInstanceState);
        initView();
    }
}
