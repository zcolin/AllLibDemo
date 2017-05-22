/***********************************************************
 * author   colin
 * company  fosung
 * email    wanglin2046@126.com
 * date     16-7-18 下午5:24
 **********************************************************/
package com.zcolin.usedemo.amodule.demo_view.activity;

import android.os.Bundle;
import android.view.KeyEvent;

import com.zcolin.gui.webview.ZWebView;
import com.zcolin.usedemo.R;
import com.zcolin.usedemo.amodule.base.BaseActivity;

/**
 * 带JsBridge的webview的Demo
 */
public class WebViewVideoActivity extends BaseActivity {
    private ZWebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_video);
        
        initView();
        webView.loadUrl("http://dt.85ido.com:8080/r/cms/qilu/qilu/jty/gbxxpt/video-new.html");
    }


    protected void initView() {
        webView = getView(R.id.webView);
        webView.setSupportVideoFullScreen(this)
               .setSupportProgressBar();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webView.hideCustomView()) {
                System.out.println("---------hideCustomView");
                return true;
            } else if (webView.canGoBack()) {
                webView.goBack();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
