/***********************************************************
 * author   colin
 * company  fosung
 * email    wanglin2046@126.com
 * date     16-7-18 下午5:24
 **********************************************************/

package com.fosung.usedemo.amodule.main.fragment;


import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.fosung.frame.app.BaseFrameLazyLoadFrag;
import com.fosung.frame.utils.ActivityUtil;
import com.fosung.usedemo.R;
import com.fosung.usedemo.amodule.demo_view.activity.DesignSupportActivity;
import com.fosung.usedemo.amodule.demo_view.activity.DesignSupportActivity1;
import com.fosung.usedemo.amodule.demo_view.activity.DialogActivity;
import com.fosung.usedemo.amodule.demo_view.activity.OtherViewActivity;
import com.fosung.usedemo.amodule.demo_view.activity.PassDataActivity;
import com.fosung.usedemo.amodule.demo_view.activity.WebViewActivity;

import java.util.ArrayList;


/**
 * View相关示例
 */
public class ViewFragment extends BaseFrameLazyLoadFrag implements View.OnClickListener {
    private LinearLayout llContent;
    private ArrayList<Button> listButton = new ArrayList<>();

    public static ViewFragment newInstance() {
        Bundle args = new Bundle();
        ViewFragment fragment = new ViewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void lazyLoad() {
        init();
    }

    @Override
    protected int getRootViewLayId() {
        return R.layout.activity_common;
    }


    private void init() {
        llContent = getView(R.id.ll_content);
        listButton.add(addButton("回传数据的Activity"));
        listButton.add(addButton("DemoDesignSupportActivity"));
        listButton.add(addButton("DemoDesignSupportActivity1"));
        listButton.add(addButton("WebViewDemo"));
        listButton.add(addButton("DialogDemo"));
        listButton.add(addButton("其他View示例"));
        
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
            ActivityUtil.startActivity(mActivity, PassDataActivity.class);
        } else if (v == listButton.get(1)) {
            ActivityUtil.startActivity(mActivity, DesignSupportActivity.class);
        } else if (v == listButton.get(2)) {
            ActivityUtil.startActivity(mActivity, DesignSupportActivity1.class);
        }else if (v == listButton.get(3)) {
            ActivityUtil.startActivity(mActivity, WebViewActivity.class);
        }else if (v == listButton.get(4)) {
            ActivityUtil.startActivity(mActivity, DialogActivity.class);
        }else if (v == listButton.get(5)) {
            ActivityUtil.startActivity(mActivity, OtherViewActivity.class);
        }
    }
}
