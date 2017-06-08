/***********************************************************
 * author   colin
 * company  fosung
 * email    wanglin2046@126.com
 * date     16-7-18 下午1:47
 **********************************************************/

package com.zcolin.usedemo.amodule.mvc.main.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.zcolin.frame.app.BaseFrameFrag;
import com.zcolin.frame.util.ActivityUtil;
import com.zcolin.usedemo.R;
import com.zcolin.usedemo.amodule.mvc.demo_http.HttpDemoActivity;
import com.zcolin.usedemo.amodule.mvvm.demo_db.activity.DbDemoActivity;

import java.util.ArrayList;


/**
 * 数据库、网络演示
 */
public class HttpDBFragment extends BaseFrameFrag implements View.OnClickListener {
    private LinearLayout llContent;
    private ArrayList<Button> listButton = new ArrayList<>();

    public static HttpDBFragment newInstance() {
        Bundle args = new Bundle();
        HttpDBFragment fragment = new HttpDBFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getRootViewLayId() {
        return R.layout.activity_common;
    }

    @Override
    protected void lazyLoad(@Nullable Bundle savedInstanceState) {
        init();
    }

    private void init() {
        llContent = getView(R.id.ll_content);
        listButton.add(addButton("Http演示"));
        listButton.add(addButton("Db演示"));

        for (Button btn : listButton) {
            btn.setOnClickListener(this);
        }
    }

    private Button addButton(String text) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        Button button = new Button(mActivity);
        button.setText(text);
        button.setGravity(Gravity.CENTER);
        button.setAllCaps(false);
        llContent.addView(button, params);
        return button;
    }

    @Override
    public void onClick(View v) {
        if (v == listButton.get(0)) {
            ActivityUtil.startActivity(mActivity, HttpDemoActivity.class);
        } else if (v == listButton.get(1)) {
            ActivityUtil.startActivity(mActivity, DbDemoActivity.class);
        }
    }
}
