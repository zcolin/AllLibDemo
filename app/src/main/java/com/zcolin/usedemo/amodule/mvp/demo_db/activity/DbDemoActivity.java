/*
 * *********************************************************
 *   author   colin
 *   company  telchina
 *   email    wanglin2046@126.com
 *   date     18-1-9 下午5:02
 * ********************************************************
 */

package com.zcolin.usedemo.amodule.mvp.demo_db.activity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zcolin.usedemo.R;
import com.zcolin.usedemo.amodule.mvp.base.BaseMVPActivity;
import com.zcolin.usedemo.amodule.mvp.base.Presenter;
import com.zcolin.usedemo.amodule.mvp.demo_db.presenter.DbDemoPresenter;
import com.zcolin.usedemo.amodule.mvp.demo_db.view.IDBDemoView;

import java.util.ArrayList;

/**
 * DBDemo
 */
@Presenter(DbDemoPresenter.class)
public class DbDemoActivity extends BaseMVPActivity<DbDemoPresenter> implements View.OnClickListener, IDBDemoView {
    private LinearLayout llContent;
    private TextView     textView;
    private ArrayList<Button> listButton = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_db);
        setToolbarTitle("DBDemo");

        initView();
    }

    protected void initView() {
        llContent = getView(R.id.ll_content);
        textView = getView(R.id.textview);
        textView.setMovementMethod(new ScrollingMovementMethod());
        listButton.add(addButton("插入数据"));
        listButton.add(addButton("替换或插入数据"));
        listButton.add(addButton("获取数据列表"));
        listButton.add(addButton("条件查询降序"));
        listButton.add(addButton("清空数据库"));

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

    }

    @Override
    public void onClick(View v) {
        if (v == listButton.get(0)) {
            mPresenter.insertObject();
        } else if (v == listButton.get(1)) {
            mPresenter.insertOrReplaceObject();
        } else if (v == listButton.get(2)) {
            mPresenter.queryAllObject();
        } else if (v == listButton.get(3)) {
            mPresenter.queryObjectWithCondition();
        } else if (v == listButton.get(4)) {
            mPresenter.deleteAllObject();
        }
    }
}
