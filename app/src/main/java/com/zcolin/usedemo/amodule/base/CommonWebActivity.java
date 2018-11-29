/*
 * *********************************************************
 *   author   colin
 *   company  telchina
 *   email    wanglin2046@126.com
 *   date     18-1-9 下午5:03
 * ********************************************************
 */

package com.zcolin.usedemo.amodule.base;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zcolin.zwebview.ZWebView;
import com.zcolin.usedemo.R;


/**
 * 通用的WebViewActivity，通过传值控制
 */
@ActivityParam(isSecondLevelActivity = false)
public class CommonWebActivity extends BaseActivity {

    protected TextView tvClose;
    protected ZWebView zWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_common_webview);

        boolean isShowProgressBar = mBundle.getBoolean("isShowProgressBar");
        String title = mBundle.getString("title");
        String url = mBundle.getString("url");
        boolean isShowCloseBtn = mBundle.getBoolean("isShowCloseBtn");
        boolean isSupportVideo = mBundle.getBoolean("isSupportVideo");
        boolean isSupportChooseFile = mBundle.getBoolean("isSupportChooseFile");

        setToolbarLeftBtnCompoundDrawableLeft(R.drawable.gui_icon_arrow_back);
        setToolbarTitle(title);

        zWebView = getView(R.id.webView);
        tvClose = getView(R.id.toolbar_btn_colose);
        if (isShowCloseBtn) {
            tvClose.setVisibility(View.VISIBLE);
            tvClose.setOnClickListener(v -> mActivity.finish());
        } else {
            tvClose.setVisibility(View.GONE);
        }

        if (isShowProgressBar) {
            zWebView.setSupportHorizontalProgressBar();
            zWebView.setSupportCircleProgressBar();
        }

        if (isSupportVideo) {
            zWebView.setSupportVideoFullScreen(mActivity);
        }

        if (isSupportChooseFile) {
            zWebView.setSupportChooseFile(mActivity);
        }

        zWebView.loadUrl(url);
    }

    @Override
    public int getToolBarLayout() {
        return R.layout.toolbar_webbaseview;
    }

    @Override
    protected void onToolBarLeftBtnClick() {
        this.finish();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        zWebView.processResult(requestCode, resultCode, intent);
    }
}
