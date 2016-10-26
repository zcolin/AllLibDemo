/***********************************************************
 * author   colin
 * company  fosung
 * email    wanglin2046@126.com
 * date     16-7-15 上午9:54
 **********************************************************/

package com.fosung.usedemo.amodule.init;

import android.os.Bundle;

import com.fosung.frame.utils.ActivityUtil;
import com.fosung.gui.base.BaseFullScreenActivity;
import com.fosung.usedemo.R;
import com.fosung.usedemo.amodule.main.activity.MainActivity;


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
