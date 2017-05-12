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
        setToolbarTitle(getClass().getSimpleName());
    }

    @Override
    protected int getRootViewLayId() {
        return R.layout.activity_passdata;
    }

    @Override
    protected void initView() {
        final ZEditTextWithClear et = getView(R.id.edittext);
        et.setHint("输入需要回传的数据");

        Button btn = getView(R.id.button);
        btn.setText("finishActivity");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PassDataActivity1.this.setResult(RESULT_OK, new Intent().putExtra("data", et.getText()
                                                                                            .toString()));
                PassDataActivity1.this.finish();
            }
        });
    }

    @Override
    protected boolean isSecondLevelAcitivty() {
        return true;
    }
}
