/*
 * *********************************************************
 *   author   colin
 *   company  telchina
 *   email    wanglin2046@126.com
 *   date     18-1-9 下午5:02
 * ********************************************************
 */

package com.zcolin.usedemo.amodule.mvc.demo_view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.zcolin.frame.util.ToastUtil;
import com.zcolin.usedemo.R;
import com.zcolin.usedemo.amodule.base.BaseActivity;

public class PassDataActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passdata);

        setToolbarTitle(getClass().getSimpleName());
        initView();
    }

    protected void initView() {
        findViewById(R.id.edittext).setVisibility(View.GONE);
        findViewById(R.id.button).setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setClass(PassDataActivity.this, PassDataActivity1.class);
            startActivityWithCallback(intent, (resultCode, data) -> {
                if (data != null) {
                    ToastUtil.toastShort("ActivityResult返回数据：" + data.getStringExtra("data"));
                }
            });
        });
    }

    @Override
    protected void onToolBarLeftBtnClick() {
        super.onToolBarLeftBtnClick();
    }
}
