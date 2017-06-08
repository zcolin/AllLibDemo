/***********************************************************
 * author   colin
 * company  fosung
 * email    wanglin2046@126.com
 * date     16-7-15 上午9:54
 **********************************************************/

package com.zcolin.usedemo.amodule.mvc.init;

import android.os.Bundle;

import com.zcolin.frame.util.ActivityUtil;
import com.zcolin.usedemo.R;
import com.zcolin.usedemo.amodule.base.BaseActivity;
import com.zcolin.usedemo.amodule.mvc.main.activity.MainActivity;

public class InitActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);

        load();
        ActivityUtil.startActivity(this, MainActivity.class);
        this.finish();
    }


    private void load() {
        //TODO 加载数据
    }
}
