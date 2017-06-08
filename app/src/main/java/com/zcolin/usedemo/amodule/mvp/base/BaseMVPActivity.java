/*
 * *********************************************************
 *   author   colin
 *   company  fosung
 *   email    wanglin2046@126.com
 *   date     17-5-17 上午10:00
 * ********************************************************
 */

package com.zcolin.usedemo.amodule.mvp.base;

import android.os.Bundle;

import com.zcolin.frame.util.ToastUtil;
import com.zcolin.usedemo.amodule.base.BaseActivity;


/**
 * MVP模式Activity基类
 */
public abstract class BaseMVPActivity<T extends BaseMVPPresenter> extends BaseActivity {
    protected T mPresenter;

    private boolean isCreate;//标记是否为create进入start，而不是从restart进入start

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isCreate = true;
        createPresenter();
        mPresenter.onAttach(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (isCreate) {
            isCreate = false;
            mPresenter.onLoad(mBundle);
        }
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
                mPresenter = (T) getPresenterClass().newInstance();
            } catch (InstantiationException e) {
                throw new RuntimeException("create Presenter error");
            } catch (IllegalAccessException e) {
                throw new RuntimeException("create Presenter error");
            }
        }
    }

    private Class getPresenterClass() {
        Presenter requestParamsUrl = getClass().getAnnotation(Presenter.class);
        Class aClass = null;
        if (requestParamsUrl != null) {
            aClass = requestParamsUrl.value();
        } else {
            throw new RuntimeException("can't find @Presenter");
        }
        return aClass;
    }

    public void toastShort(String error) {
        ToastUtil.toastShort(error);
    }

    public void toastLong(String error) {
        ToastUtil.toastLong(error);
    }
}
