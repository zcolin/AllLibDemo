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
import android.widget.Button;

import com.zcolin.gui.ZEditTextWithClear;
import com.zcolin.usedemo.R;
import com.zcolin.usedemo.amodule.base.BaseActivity;

/**
 * 回传数据的Activity
 */
public class PassDataActivity1 extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passdata);
        setToolbarTitle(getClass().getSimpleName());
        initView();
    }

    protected void initView() {
        final ZEditTextWithClear et = getView(R.id.edittext);
        et.setHint("输入需要回传的数据");

        Button btn = getView(R.id.button);
        btn.setText("finishActivity");
        btn.setOnClickListener(v -> {
            PassDataActivity1.this.setResult(RESULT_OK, new Intent().putExtra("data", et.getText().toString()));
            PassDataActivity1.this.finish();
        });
    }

}
