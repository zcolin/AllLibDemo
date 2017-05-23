/*
 * *********************************************************
 *   author   colin
 *   company  fosung
 *   email    wanglin2046@126.com
 *   date     17-5-22 下午12:05
 * ********************************************************
 */

package com.zcolin.usedemo.amodule.mvp.demo_http.actiivty;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zcolin.usedemo.R;
import com.zcolin.usedemo.amodule.base.mvp.BaseMVPActivity;
import com.zcolin.usedemo.amodule.base.mvp.Presenter;
import com.zcolin.usedemo.amodule.mvp.demo_http.presenter.HttpDemoPresenter;
import com.zcolin.usedemo.amodule.mvp.demo_http.view.IHttpDemoView;

import java.util.ArrayList;


/**
 * HttpDemo
 */
@Presenter(HttpDemoPresenter.class)
public class HttpDemoActivity extends BaseMVPActivity<HttpDemoPresenter> implements View.OnClickListener, IHttpDemoView {
    private LinearLayout llContent;
    private TextView     textView;
    private ArrayList<Button> listButton = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_db);
        setToolbarTitle("HttpDemo");

        initView();
    }

    protected void initView() {
        llContent = getView(R.id.ll_content);
        textView = getView(R.id.textview);
        textView.setMovementMethod(new ScrollingMovementMethod());
        listButton.add(addButton("获取百度数据"));
        listButton.add(addButton("获取对象"));
        listButton.add(addButton("Post数据(请看代码，无效果)"));
        listButton.add(addButton("上传文件(请看代码，无效果)"));

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
    public void showResult(String data) {
        textView.setText(data);
    }

    @Override
    public void showError(String error) {
        textView.setText(error);
    }

    @Override
    public void onClick(View v) {
        if (v == listButton.get(0)) {
            mPresenter.getBaiduStringData(mActivity);
        } else if (v == listButton.get(1)) {
            mPresenter.getObject(mActivity);
        }
    }
}
