/*
 * *********************************************************
 *   author   colin
 *   company  fosung
 *   email    wanglin2046@126.com
 *   date     17-5-17 上午9:58
 * ********************************************************
 */

package com.zcolin.usedemo.amodule.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.zcolin.frame.app.BaseFrameFrag;

import java.lang.reflect.Method;


public abstract class BaseFragment extends BaseFrameFrag {

    @Override
    protected void createView(@Nullable Bundle savedInstanceState) {
        super.createView(savedInstanceState);

        injectZClick();
    }

    private void injectZClick() {
        Method[] methods = getClass().getDeclaredMethods();
        for (final Method method : methods) {
            ZClick clickArray = method.getAnnotation(ZClick.class);//通过反射api获取方法上面的注解
            if (clickArray != null && clickArray.value().length > 0) {
                method.setAccessible(true);
                final boolean isHasParam = method.getParameterTypes() != null && method.getParameterTypes().length > 0;
                for (int click : clickArray.value()) {
                    final View view = getView(click);//通过注解的值获取View控件
                    if (view == null)
                        return;
                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {
                                if (isHasParam) {
                                    method.invoke(BaseFragment.this, view);
                                } else {
                                    method.invoke(BaseFragment.this);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        }
    }
}
