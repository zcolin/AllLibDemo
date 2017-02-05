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
import com.zcolin.usedemo.amodule.base.BaseFullScreenActivity;
import com.zcolin.usedemo.amodule.main.activity.MainActivity;

public class InitActivity extends BaseFullScreenActivity {

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
