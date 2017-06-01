/*
 * *********************************************************
 *   author   colin
 *   company  fosung
 *   email    wanglin2046@126.com
 *   date     17-5-26 下午1:26
 * ********************************************************
 */

package com.zcolin.usedemo.amodule.mvvm.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.view.View;

import com.zcolin.usedemo.amodule.base.BaseActivity;


/**
 * MVP模式Activity基类
 */
public class BaseMVVMActivity<T extends ViewDataBinding> extends BaseActivity {
    protected T mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {
        View view = getLayoutInflater().inflate(layoutResID, null);
        mBinding = DataBindingUtil.bind(view);
        super.setContentView(view);
    }

    @Override
    public void setContentView(View view) {
        mBinding = DataBindingUtil.bind(view);
        super.setContentView(view);
    }
}
