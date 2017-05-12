/*
 * *********************************************************
 *   author   colin
 *   company  fosung
 *   email    wanglin2046@126.com
 *   date     17-5-4 上午10:48
 * ********************************************************
 */

package com.zcolin.usedemo.amodule.base.mvp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.zcolin.usedemo.amodule.base.BaseFragment;

/**
 * MVP模式
 */
public abstract class BaseMVPFragment<T extends BaseMVPPresenter> extends BaseFragment {
    protected T mPresenter;

    protected abstract Class<T> getPresenterClass();

    protected abstract boolean isLazyLoad();
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createPresenter();

        mPresenter.onAttach(this);
    }

    @Override
    protected void createView(@Nullable Bundle savedInstanceState) {
        super.createView(savedInstanceState);
        
        if (!isLazyLoad()) {
            //如果是数据恢复的，则传入数据回复的数据，否则传入其他页面传递过来的数据
            mPresenter.onLoad(savedInstanceState != null ? savedInstanceState : getArguments());
        }
    }

    @Override
    protected void lazyLoad(@Nullable Bundle savedInstanceState) {
        super.lazyLoad(savedInstanceState);

        if (isLazyLoad()) {
            //如果是数据恢复的，则传入数据回复的数据，否则传入其他页面传递过来的数据
            mPresenter.onLoad(savedInstanceState != null ? savedInstanceState : getArguments());
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mPresenter.onSave(outState);
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.onPause();
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
        super.onStop();
    }

    @Override
    public void onDestroy() {
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
            } catch (IllegalAccessException e) {
                throw new RuntimeException("create IDelegate error");
            } catch (java.lang.InstantiationException e) {
                e.printStackTrace();
            }
        }
    }
}
