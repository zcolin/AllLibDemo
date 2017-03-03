/***********************************************************
 * author   colin
 * company  fosung
 * email    wanglin2046@126.com
 * date     16-7-18 下午5:24
 **********************************************************/
package com.zcolin.usedemo.amodule.demo_view.activity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.zcolin.frame.utils.ToastUtil;
import com.zcolin.gui.ZAlert;
import com.zcolin.gui.ZConfirm;
import com.zcolin.gui.ZDialog;
import com.zcolin.gui.ZDialogEdit;
import com.zcolin.gui.ZDialogMenu;
import com.zcolin.gui.ZDialogRadioGroup;
import com.zcolin.gui.ZDialogWheelDate;
import com.zcolin.gui.ZDialogWheelTime;
import com.zcolin.usedemo.R;
import com.zcolin.usedemo.amodule.base.BaseSecondLevelActivity;

import java.util.ArrayList;

/**
 * 带JsBridge的webview的Demo
 */
public class DialogActivity extends BaseSecondLevelActivity implements OnClickListener {
    private LinearLayout llContent;
    private ArrayList<Button> listButton = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);

        setToolbarTitle("Dialog Demo");
        setToolbarRightBtnText("保存");
        init();
    }

    @Override
    protected void onToolBarRightBtnClick() {
        ToastUtil.toastShort("保存");
    }

    private void init() {
        llContent = getView(R.id.ll_content);
        listButton.add(addButton("ZAlert"));
        listButton.add(addButton("ZConfirm"));
        listButton.add(addButton("ZDialogRadio"));
        listButton.add(addButton("ZDialogMenu"));
        listButton.add(addButton("ZDialogWheelDate"));
        listButton.add(addButton("ZDialogWheelTime"));
        listButton.add(addButton("ZDialogEdit"));

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
            new ZAlert(mActivity)
                    .setTitle("Alert")
                    .setMessage("这是一个Alert")
                    .show();
        } else if (v == listButton.get(1)) {
            new ZConfirm(mActivity)
                    .setTitle("DlgCommon")
                    .setMessage("这是一个通用对话框")
                    .addSubmitListener(new ZDialog.ZDialogSubmitInterface() {
                        @Override
                        public boolean submit() {
                            ToastUtil.toastShort("点击了确定");
                            return true;
                        }
                    })
                    .show();
        } else if (v == listButton.get(2)) {
            final String[] arrt = new String[]{"menu1", "menu2", "menu3", "menu4", "menu5"};
            new ZDialogRadioGroup(mActivity)
                    .setTitle("DlgRadioGroup")
                    .setDatas(arrt, "menu1")
                    .addSubmitListener(new ZDialog.ZDialogParamSubmitInterface<Integer>() {
                        @Override
                        public boolean submit(Integer integer) {
                            ToastUtil.toastShort("选择了" + arrt[integer]);
                            return true;
                        }
                    })
                    .show();
        } else if (v == listButton.get(3)) {
            final String[] arrt = new String[]{"menu1", "menu2", "menu3", "menu4", "menu5"};
            new ZDialogMenu(mActivity)
                    .setTitle("DlgMenu")
                    .setDatas(arrt)
                    .addSubmitListener(new ZDialog.ZDialogParamSubmitInterface<Integer>() {
                        @Override
                        public boolean submit(Integer integer) {
                            ToastUtil.toastShort("选择了" + arrt[integer]);
                            return true;
                        }
                    })
                    .show();
        } else if (v == listButton.get(4)) {
            new ZDialogWheelDate(mActivity)
                    .setTitle("DlgWheelDate")
                    .setDataSubmitListener(new ZDialogWheelDate.OnDateSubmitListener() {
                        @Override
                        public void onClick(int year, int month, int day) {
                            ToastUtil.toastShort(year + "-" + month + "-" + day);
                        }
                    })
                    .show();
        } else if (v == listButton.get(5)) {
            new ZDialogWheelTime(mActivity)
                    .setTitle("DlgWheelDate")
                    .setDataSubmitListener(new ZDialogWheelTime.OnTimeSubmitListener() {
                        @Override
                        public void onClick(int hour, int min) {
                            ToastUtil.toastShort(hour + ":" + min);
                        }
                    })
                    .show();
        } else if (v == listButton.get(6)) {
            new ZDialogEdit(mActivity)
                    .setTitle("ZDlgEdit")
                    .setEditText("回填数据")
                    .addSubmitStrListener(new ZDialog.ZDialogParamSubmitInterface<String>() {
                        @Override
                        public boolean submit(String s) {
                            ToastUtil.toastShort("输入框数据" + s);
                            return true;
                        }
                    })
                    .show();
        }
    }
}
