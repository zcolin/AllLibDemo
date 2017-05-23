/*
 * *********************************************************
 *   author   colin
 *   company  fosung
 *   email    wanglin2046@126.com
 *   date     17-5-17 上午10:00
 * ********************************************************
 */

package com.zcolin.usedemo.amodule.base.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zcolin.frame.utils.ToastUtil;
import com.zcolin.usedemo.amodule.base.BaseFragment;


/**
 * MVP模式
 */
public abstract class BaseMVPFragment<T extends BaseMVPPresenter> extends BaseFragment {
    protected T mPresenter;

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
                mPresenter = (T) getPresenterClass().newInstance();
            } catch (IllegalAccessException e) {
                throw new RuntimeException("create IDelegate error");
            } catch (java.lang.InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

    private Class getPresenterClass() {
        Presenter requestParamsUrl = getClass().getAnnotation(Presenter.class);
        Class aClass = null;
        if (requestParamsUrl != null) {
            aClass = requestParamsUrl.value();
        } else{
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
