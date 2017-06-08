/*
 * *********************************************************
 *   author   colin
 *   company  fosung
 *   email    wanglin2046@126.com
 *   date     17-5-22 下午3:28
 * ********************************************************
 */

package com.zcolin.usedemo.amodule.mvc.demo_video;

import android.os.Bundle;
import android.view.KeyEvent;
import com.zcolin.frame.util.ToastUtil;
import com.zcolin.usedemo.R;
import com.zcolin.usedemo.amodule.base.BaseActivity;

import cn.hugo.android.scanner.view.BaseQrCodeScannerView;


public class QrCodeScanActivity extends BaseActivity {

    private BaseQrCodeScannerView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcodescan);
        setToolbarTitle("扫码");
        setToolBarRightBtnBackground(R.drawable.scan_flashlight);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        view.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        view.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        view.onDestroy();
    }

    protected void initView() {
        view = (BaseQrCodeScannerView) findViewById(R.id.scan_view);
        view.onCreate();
        view.setOnScanResultListener(new BaseQrCodeScannerView.OnScanResultListener() {
            @Override
            public boolean scanResult(String result) {
                ToastUtil.toastShort(result);
                QrCodeScanActivity.this.finish();
                return true;//false会提示扫描错误，并重新开始扫描
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return view.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onToolBarRightBtnClick() {
        view.toggleFlashLight();
    }
}
