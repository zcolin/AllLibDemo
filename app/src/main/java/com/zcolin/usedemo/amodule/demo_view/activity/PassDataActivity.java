/***********************************************************
 * author   colin
 * company  fosung
 * email    wanglin2046@126.com
 * date     16-7-18 下午5:24
 **********************************************************/

package com.zcolin.usedemo.amodule.demo_view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.fosung.frame.app.ResultActivityHelper;
import com.fosung.frame.utils.ToastUtil;
import com.zcolin.usedemo.R;
import com.zcolin.usedemo.amodule.base.BaseNoToolBarActivity;
import com.zcolin.usedemo.amodule.base.BaseSecondLevelActivity;
import com.zcolin.usedemo.amodule.base.BaseToolBarActivity;

/**
 * 需要带ToolBar的继承 {@link BaseToolBarActivity}
 * <p/>
 * 需要ToolBar带返回按钮的继承 {@link BaseSecondLevelActivity}
 * <p/>
 * 需要没有ToolBar的继承 {@link BaseNoToolBarActivity}
 */
public class PassDataActivity extends BaseToolBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passdata);

        setToolbarTitle(getClass().getSimpleName());
        findViewById(R.id.edittext).setVisibility(View.GONE);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(PassDataActivity.this, PassDataActivity1.class);
                startActivityWithCallback(intent, new ResultActivityHelper.ResultActivityListener() {
                    @Override
                    public void onResult(int requestCode, int resultCode, Intent data) {
                        if (data != null) {
                            ToastUtil.toastShort("ActivityResult返回数据：" + data.getStringExtra("data"));
                        }
                    }
                });
            }
        });
    }
}
