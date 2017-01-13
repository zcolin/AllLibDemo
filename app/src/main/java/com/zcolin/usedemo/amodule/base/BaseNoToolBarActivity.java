/*
 * *********************************************************
 *   author   colin
 *   company  fosung
 *   email    wanglin2046@126.com
 *   date     17-1-13 上午11:48
 * ********************************************************
 */

package com.zcolin.usedemo.amodule.base;


import android.os.Bundle;
import android.view.ViewGroup;

import com.fosung.frame.app.BaseFrameActivity;
import com.fosung.frame.utils.ScreenUtil;


/**
 * 没有ToolBar的Activity, 不需要Toolbar可以继承此类（会空出顶部状态栏区域）
 */
public class BaseNoToolBarActivity extends BaseFrameActivity {
    private boolean isPaddingTop = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);

        if (isImmerse() && isPaddingTop) {
            setImmersePaddingTop();
        }
    }


    /**
     * 设定子View距离顶部的距离，空出状态栏的距离
     */
    protected void setImmersePaddingTop() {
        ViewGroup viewGroup = (ViewGroup) ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0);
        viewGroup.setPadding(0, ScreenUtil.getStatusBarHeight(this), 0, 0);
    }

    /**
     * 是否空出顶部距离
     */
    protected void setIsPaddingTop(boolean isPaddingTop) {
        this.isPaddingTop = isPaddingTop;
    }
}
