/*
 * *********************************************************
 *   author   colin
 *   company  telchina
 *   email    wanglin2046@126.com
 *   date     18-1-9 下午5:03
 * ********************************************************
 */

package com.zcolin.usedemo.amodule.mvp.demo_video;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.fosung.libeasypr.view.EasyPRPreView;
import com.zcolin.frame.util.ToastUtil;
import com.zcolin.usedemo.R;
import com.zcolin.usedemo.amodule.base.BaseActivity;

/**
 * 车牌识别页面
 */
public class EasyPRActivity extends BaseActivity {
    private EasyPRPreView easyPRPreView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easypr);

        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (easyPRPreView != null) {
            easyPRPreView.onStart();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (easyPRPreView != null) {
            easyPRPreView.onStop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (easyPRPreView != null) {
            easyPRPreView.onDestroy();
        }
    }

    private void initView() {
        easyPRPreView = getView(R.id.preSurfaceView);
        final Button btnCamera = getView(R.id.btnShutter);
        final TextView tvRecogResult = getView(R.id.lblRecogResult);
        final ImageView iv = getView(R.id.iv);

        easyPRPreView.setRecognizedListener(text -> {
            if (text == null || text.equals("0")) {
                ToastUtil.toastShort("换个姿势试试");
            } else {
                tvRecogResult.setText(text);
            }
        }).setPictureTakenListener(files -> {
            //                              Bitmap map = BitmapUtil.decodeBitmap(path);
            //                              iv.setImageBitmap(map);
        });

        btnCamera.setOnClickListener(v -> easyPRPreView.recognize());
    }
}
