/*
 * *********************************************************
 *   author   colin
 *   company  telchina
 *   email    wanglin2046@126.com
 *   date     18-1-9 下午5:03
 * ********************************************************
 */

package com.zcolin.usedemo.amodule.mvvm.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zcolin.usedemo.amodule.base.BaseFragment;


public abstract class BaseMVPFragment<T extends ViewDataBinding> extends BaseFragment {
    protected T mBinding;

    @Override
    protected void createView(@Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.bind(rootView);
        super.createView(savedInstanceState);
    }
}
