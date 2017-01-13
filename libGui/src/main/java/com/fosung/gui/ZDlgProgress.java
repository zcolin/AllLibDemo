/*
 * **********************************************************
 *   author   colin
 *   company  fosung
 *   email    wanglin2046@126.com
 *   date     16-10-21 上午11:38
 * *********************************************************
 */

package com.zcolin.gui;

import android.app.ProgressDialog;
import android.content.Context;



/**
 * 进度条封装类
 * TODO 先使用系统样式，如果需要自定义，则需要放开注释
 */
public class ZDlgProgress extends ProgressDialog {

//    private TextView     tvMessage;
//    private CharSequence strMessage;

    public ZDlgProgress(Context context) {
        super(context);
        setCancelable(false);
        getWindow().setBackgroundDrawableResource(R.color.gui_transparent);
    }

    @Override
    public void show() {
        super.show();
        setContentView(R.layout.gui_view_progress);
//        tvMessage = (TextView) findViewById(R.id.progressBar_tv);
//        tvMessage.setText(strMessage);
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }

    @Override
    public void setMessage(CharSequence message) {
//        if (isShowing()) {
//            if (tvMessage != null)
//                tvMessage.setText(message);
//        }
//        strMessage = message;
    }
}
