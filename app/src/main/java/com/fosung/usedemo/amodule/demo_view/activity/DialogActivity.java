/***********************************************************
 * author   colin
 * company  fosung
 * email    wanglin2046@126.com
 * date     16-7-18 下午5:24
 **********************************************************/
package com.fosung.usedemo.amodule.demo_view.activity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.fosung.frame.utils.ToastUtil;
import com.fosung.gui.ZAlert;
import com.fosung.gui.ZDlg;
import com.fosung.gui.ZDlgComm;
import com.fosung.gui.ZDlgEdit;
import com.fosung.gui.ZDlgMenu;
import com.fosung.gui.ZDlgRadioGroup;
import com.fosung.gui.ZDlgWheelDate;
import com.fosung.gui.base.BaseSecondLevelActivity;
import com.fosung.usedemo.R;

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
        listButton.add(addButton("Alert"));
        listButton.add(addButton("DlgCommon"));
        listButton.add(addButton("DlgRadio"));
        listButton.add(addButton("DlgMenu"));
        listButton.add(addButton("DateSelect"));
        listButton.add(addButton("DlgEdit"));

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
            new ZDlgComm(mActivity)
                    .setTitle("DlgCommon")
                    .setMessage("这是一个通用对话框")
                    .addSubmitListener(new ZDlg.ZDialogSubmitInterface() {
                        @Override
                        public boolean submit() {
                            ToastUtil.toastShort("点击了确定");
                            return true;
                        }
                    })
                    .show();
        } else if (v == listButton.get(2)) {
            final String[] arrt = new String[]{"menu1", "menu2", "menu3", "menu4", "menu5"};
            new ZDlgRadioGroup(mActivity)
                    .setTitle("DlgRadioGroup")
                    .setDatas(arrt, "menu1")
                    .addSubmitListener(new ZDlg.ZDialogParamSubmitInterface<Integer>() {
                        @Override
                        public boolean submit(Integer integer) {
                            ToastUtil.toastShort("选择了" + arrt[integer]);
                            return true;
                        }
                    })
                    .show();
        } else if (v == listButton.get(3)) {
            final String[] arrt = new String[]{"menu1", "menu2", "menu3", "menu4", "menu5"};
            new ZDlgMenu(mActivity)
                    .setTitle("DlgMenu")
                    .setDatas(arrt)
                    .addSubmitListener(new ZDlg.ZDialogParamSubmitInterface<Integer>() {
                        @Override
                        public boolean submit(Integer integer) {
                            ToastUtil.toastShort("选择了" + arrt[integer]);
                            return true;
                        }
                    })
                    .show();
        } else if (v == listButton.get(4)) {
            new ZDlgWheelDate(mActivity)
                    .setTitle("DlgWheelDate")
                    .setDataSubmitListener(new ZDlgWheelDate.OnDateSubmitListener() {
                        @Override
                        public void onClick(int year, int month, int day) {
                            ToastUtil.toastShort(year + "-" + month + "-" + day);
                        }
                    })
                    .show();
        } else if (v == listButton.get(5)) {
            new ZDlgEdit(mActivity)
                    .setTitle("ZDlgEdit")
                    .setEditText("回填数据")
                    .addSubmitStrListener(new ZDlg.ZDialogParamSubmitInterface<String>() {
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
