/***********************************************************
 * author   colin
 * company  fosung
 * email    wanglin2046@126.com
 * date     16-7-15 上午9:54
 **********************************************************/

package com.zcolin.usedemo.amodule.init;

import android.os.Bundle;

import com.zcolin.frame.utils.ActivityUtil;
import com.zcolin.usedemo.R;
import com.zcolin.usedemo.amodule.base.BaseActivity;
import com.zcolin.usedemo.amodule.main.activity.MainActivity;

public class InitActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        load();
        ActivityUtil.startActivity(this, MainActivity.class);
        this.finish();
    }

    @Override
    protected int getRootViewLayId() {
        return R.layout.activity_init;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected boolean isSecondLevelAcitivty() {
        return true;
    }

    private void load() {
        //TODO 加载数据
    }
}
