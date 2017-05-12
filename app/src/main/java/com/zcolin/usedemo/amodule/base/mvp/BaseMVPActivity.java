/*
 * *********************************************************
 *   author   colin
 *   company  fosung
 *   email    wanglin2046@126.com
 *   date     17-5-4 上午10:18
 * ********************************************************
 */

package com.zcolin.usedemo.amodule.base.mvp;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.zcolin.usedemo.amodule.base.BaseActivity;

/**
 * MVP模式Activity基类
 */
public abstract class BaseMVPActivity<T extends BaseMVPPresenter> extends BaseActivity {
    protected T mPresenter;

    protected abstract Class<T> getPresenterClass();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createPresenter();
        mPresenter.onAttach(this);
        mPresenter.onLoad(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mPresenter.onSave(outState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    protected void onPause() {
        mPresenter.onPause();
        super.onPause();
    }

    @Override
    protected void onStop() {
        mPresenter.onStop();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDestroy();
        mPresenter.onDetach();
        mPresenter = null;
        super.onDestroy();
    }

    /**
     * 根据class创建view代理实例
     */
    private void createPresenter() {
        if (mPresenter == null) {
            try {
                mPresenter = getPresenterClass().newInstance();
            } catch (InstantiationException e) {
                throw new RuntimeException("create IDelegate error");
            } catch (IllegalAccessException e) {
                throw new RuntimeException("create IDelegate error");
            }
        }
    }
}
