package com.fosung.gui.webview;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.fosung.frame.jsbridge.BridgeHandler;
import com.fosung.frame.jsbridge.CallBackFunction;
import com.fosung.gui.R;

import java.util.Map;

/**
 * 带有加载进度条的Webview的控件
 */
public class ZProgressBarWebView extends RelativeLayout {

    private ZWebView    webView;
    private ProgressBar proBar;            //加载進度条
    private Context     context;

    public ZProgressBarWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initRes();
    }

    private void initRes() {
        LayoutInflater.from(context)
                      .inflate(R.layout.gui_view_progressbar_webview, this);
        webView = (ZWebView) findViewById(R.id.webview);
        proBar = (ProgressBar) findViewById(R.id.progressBar);

        webView.setWebViewClient(new OWWebViewClient());
        webView.setWebChromeClient(new OWWebChromeClient());
    }


    public void loadUrl(String url) {
        webView.loadUrl(url);
    }

    public void loadData(String url, Map<String, String> additionalHttpHeaders) {
        webView.loadUrl(url, additionalHttpHeaders);
    }

    public void loadData(String data, String mimeType, String encoding) {
        webView.loadData(data, mimeType, encoding);
    }

    public void loadDataWithBaseURL(String baseUrl, String data, String mimeType, String encoding, String failUrl) {
        webView.loadDataWithBaseURL(baseUrl, data, mimeType, encoding, failUrl);
    }

    public boolean canGoBack() {
        return webView.canGoBack();
    }

    public void goBack() {
        webView.goBack();
    }

    public ZWebView getWebView() {
        return webView;
    }

    public WebViewClient getWebViewClient() {
        return webView.getWebViewClient();
    }

    public WebChromeClient getWebChromeClient() {
        return webView.getWebChromeClient();
    }

    public void setProgressBarVisibility(int visibility) {
        proBar.setVisibility(visibility);
    }

    public void registerHandler(String handlerName, BridgeHandler handler) {
        webView.registerHandler(handlerName, handler);
    }

    public void callHandler(String handlerName, String data, CallBackFunction callBack) {
        webView.callHandler(handlerName, data, callBack);
    }


    /**
     * WebViewClient主要帮助WebView处理各种通知、请求事件的.
     */
    public class OWWebViewClient extends ZWebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            proBar.setVisibility(View.VISIBLE);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            proBar.setVisibility(View.GONE);
        }
    }

    public class OWWebChromeClient extends ZWebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            proBar.setProgress(newProgress);
        }
    }
}
