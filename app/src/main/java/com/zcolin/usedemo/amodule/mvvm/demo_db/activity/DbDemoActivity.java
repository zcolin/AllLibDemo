/*
 * *********************************************************
 *   author   colin
 *   company  fosung
 *   email    wanglin2046@126.com
 *   date     17-5-27 下午1:40
 * ********************************************************
 */

package com.zcolin.usedemo.amodule.mvvm.demo_db.activity;

import android.os.Bundle;

import com.zcolin.usedemo.BR;
import com.zcolin.usedemo.R;
import com.zcolin.usedemo.amodule.mvp.base.Presenter;
import com.zcolin.usedemo.amodule.mvvm.base.BaseMVVMActivity;
import com.zcolin.usedemo.amodule.mvvm.demo_db.viewmodel.DbDemoViewModel;
import com.zcolin.usedemo.databinding.BindingActivityHttpDbBinding;

/**
 * DBDemo
 */
@Presenter(DbDemoViewModel.class)
public class DbDemoActivity extends BaseMVVMActivity<BindingActivityHttpDbBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.binding_activity_http_db);
        setToolbarTitle("DBDemo");
        
        DbDemoViewModel viewModel = new DbDemoViewModel();
        mBinding.setVariable(BR.viewModel, viewModel);
        viewModel.onLoad(mBundle);
    }
}
