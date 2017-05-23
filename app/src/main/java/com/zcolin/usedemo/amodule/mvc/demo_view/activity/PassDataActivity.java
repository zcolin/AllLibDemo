/***********************************************************
 * author   colin
 * company  fosung
 * email    wanglin2046@126.com
 * date     16-7-18 下午5:24
 **********************************************************/

package com.zcolin.usedemo.amodule.mvc.demo_view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.zcolin.frame.app.ResultActivityHelper;
import com.zcolin.frame.utils.ToastUtil;
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
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(PassDataActivity.this, PassDataActivity1.class);
                startActivityWithCallback(intent, new ResultActivityHelper.ResultActivityListener() {
                    @Override
                    public void onResult(int resultCode, Intent data) {
                        if (data != null) {
                            ToastUtil.toastShort("ActivityResult返回数据：" + data.getStringExtra("data"));
                        }
                    }
                });
            }
        });
    }

    @Override
    protected void onToolBarLeftBtnClick() {
        super.onToolBarLeftBtnClick();
    }
}
