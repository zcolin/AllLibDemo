/***********************************************************
 * author   colin
 * company  fosung
 * email    wanglin2046@126.com
 * date     16-7-15 上午11:26
 **********************************************************/
package com.fosung.gui.webview;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.fosung.frame.jsbridge.BridgeHandler;
import com.fosung.frame.jsbridge.BridgeWebView;
import com.fosung.frame.jsbridge.CallBackFunction;
import com.fosung.frame.utils.DisplayUtil;
import com.fosung.frame.utils.LogUtil;
import com.fosung.gui.R;


/**
 * 封装的Webview的控件
 */
public class ZBridgeWebView extends BridgeWebView {

    private ZWebViewClientWrapper   webViewClientWrapper;
    private ZWebChromeClientWrapper webChromeClientWrapper;
    private ProgressBar             proBar;            //加载進度条

    public ZBridgeWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initWebView();
    }

    //网页属性设置
    @SuppressLint({"SetJavaScriptEnabled"})
    private void initWebView() {
        setWebViewClient(new WebViewClient());
        setWebChromeClient(new WebChromeClient());

        WebSettings webSettings = getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        setHorizontalScrollBarEnabled(false);
        //webView.addJavascriptInterface(new JsInterUtil(), "javautil");
        setHorizontalScrollbarOverlay(true);
        setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
    }

    @Override
    public void setWebViewClient(@NonNull WebViewClient webViewClient) {
        this.webViewClientWrapper = new ZWebViewClientWrapper(webViewClient);
        webViewClientWrapper.setProgressBar(proBar);
        super.setWebViewClient(webViewClientWrapper);
    }

    @Override
    public void setWebChromeClient(@NonNull WebChromeClient webChromeClient) {
        if (webChromeClientWrapper == null) {
            this.webChromeClientWrapper = new ZWebChromeClientWrapper(webChromeClient);
        } else if (webChromeClientWrapper instanceof ZChooseFileWebChromeClientWrapper) {
            this.webChromeClientWrapper.setWebChromeClient(webChromeClient);
        } else {
            this.webChromeClientWrapper = new ZWebChromeClientWrapper(webChromeClient);
        }
        webChromeClientWrapper.setProgressBar(proBar);
        super.setWebChromeClient(webChromeClientWrapper);
    }

    /**
     * 支持文件选择
     */
    public ZBridgeWebView setSupportChooeFile(Activity activity) {
        webChromeClientWrapper = new ZChooseFileWebChromeClientWrapper(webChromeClientWrapper.getWebChromeClient(), activity);
        setWebChromeClient(webChromeClientWrapper.getWebChromeClient());
        return this;
    }

    /**
     * 支持文件选择
     */
    public ZBridgeWebView setSupportChooeFile(Fragment fragment) {
        webChromeClientWrapper = new ZChooseFileWebChromeClientWrapper(webChromeClientWrapper.getWebChromeClient(), fragment);
        setWebChromeClient(webChromeClientWrapper.getWebChromeClient());
        return this;
    }

    /**
     * 支持显示进度条
     */
    public ZBridgeWebView setSupportProgressBar() {
        ViewGroup group = (ViewGroup) this.getParent();
        FrameLayout container = new FrameLayout(this.getContext());
        int index = group.indexOfChild(this);
        group.removeView(this);
        group.addView(container, index, this.getLayoutParams());
        container.addView(this, new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        proBar = (ProgressBar) LayoutInflater.from(getContext())
                                             .inflate(R.layout.gui_view_webview_progressbar, null);
        container.addView(proBar, new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, DisplayUtil.dip2px(getContext(), 2)));
        webChromeClientWrapper.setProgressBar(proBar);
        webViewClientWrapper.setProgressBar(proBar);
        return this;
    }

    public WebViewClient getWebViewClient() {
        return webViewClientWrapper.getWebViewClient();
    }

    public WebChromeClient getWebChromeClient() {
        return webChromeClientWrapper.getWebChromeClient();
    }

    /**
     * 注册启动Activity的web交互
     */
    public ZBridgeWebView registerStartActivity(final Activity activity) {
        registerHandler("startActivity", new BridgeHandler() {
            @Override
            public void handler(String data, final CallBackFunction function) {
                try {
                    Intent intent = new Intent();
                    ComponentName componentName = new ComponentName(activity.getPackageName(), activity.getPackageName() + "build/intermediates/exploded-aar/com.android.support/support-v4/23.2.1/res" + data);
                    intent.setComponent(componentName);
                    activity.startActivity(intent);
                } catch (Exception e) {
                    LogUtil.e("ZWebView.startActivity.handler", e);
                }
            }
        });
        return this;
    }

    /**
     * 注册启动Activity的web交互
     */
    public ZBridgeWebView registerFinishActivity(final Activity activity) {
        registerHandler("finishActivity", new BridgeHandler() {
            @Override
            public void handler(String data, final CallBackFunction function) {
                activity.finish();
            }
        });
        return this;
    }

    /**
     * 支持文件选择的时候需要在onActivity中调用此函数
     */
    public boolean processResult(int requestCode, int resultCode, Intent intent) {
        if (webChromeClientWrapper instanceof ZChooseFileWebChromeClientWrapper) {
            return ((ZChooseFileWebChromeClientWrapper) webChromeClientWrapper).processResult(requestCode, resultCode, intent);
        }
        return false;
    }
}
