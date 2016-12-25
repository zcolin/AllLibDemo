/***********************************************************
 * author   colin
 * company  fosung
 * email    wanglin2046@126.com
 * date     16-7-18 下午5:24
 **********************************************************/
package com.fosung.usedemo.amodule.demo_view.activity;

import android.os.Bundle;
import android.view.KeyEvent;

import com.fosung.gui.base.BaseSecondLevelActivity;
import com.fosung.gui.webview.ZWebView;
import com.fosung.usedemo.R;

/**
 * 带JsBridge的webview的Demo
 */
public class WebViewVideoActivity extends BaseSecondLevelActivity {
    private ZWebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_video);

        webView = getView(R.id.webView);
        webView.setSupportVideoFullScreen(this).setSupportProgressBar();
        
        webView.loadUrl("http://dt.85ido.com:8080/r/cms/qilu/qilu/jty/gbxxpt/video-new.html");
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
