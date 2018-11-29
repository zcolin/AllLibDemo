/*
 * *********************************************************
 *   author   colin
 *   company  telchina
 *   email    wanglin2046@126.com
 *   date     18-1-9 下午5:03
 * ********************************************************
 */
package com.zcolin.usedemo.amodule.mvvm.demo_view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.zcolin.frame.util.GsonUtil;
import com.zcolin.gui.ZAlert;
import com.zcolin.zwebview.ZWebView;
import com.zcolin.zwebview.jsbridge.DefaultHandler;
import com.zcolin.usedemo.R;
import com.zcolin.usedemo.amodule.base.BaseActivity;

/**
 * 带JsBridge的webview的Demo
 */
public class WebViewActivity extends BaseActivity implements OnClickListener {
    private ZWebView webView;
    private Button   button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);


        initView();
        loadUrl();
        getUserDataFrom_xx();

    }


    protected void initView() {
        webView = getView(R.id.webView);
        button = getView(R.id.button);
        button.setOnClickListener(this);
        initWebView();
    }

    public void initWebView() {
        webView.setDefaultHandler(new DefaultHandler());//如果JS调用send方法，会走到DefaultHandler里
        webView.registerHandler("submitFromWeb", (data, function) -> new ZAlert(mActivity).setMessage("监听到网页传入数据：" + data).addSubmitListener(() -> {
            function.onCallBack("java 返回数据！！！");
            return true;
        }).show());
        webView.setSupportChooseFile(this);
        webView.setSupportHorizontalProgressBar();
        webView.setSupportCircleProgressBar();
    }

    public void loadUrl() {
        webView.loadUrl("http://www.sina.com");
    }

    public void callJsFunc(String funcName, String strParam) {
        webView.callHandler(funcName, strParam, data -> new ZAlert(mActivity).setMessage("网页返回数据：" + data).show());
        //webView.send("hello");
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        webView.processResult(requestCode, resultCode, intent);
    }

    @Override
    public void onClick(View v) {
        if (button.equals(v)) {
            callJsFunc("functionInJs", "java 调用传入数据");
        }
    }

    public void getUserDataFrom_xx() {
        User user = new User();
        Location location = new Location();
        location.address = "SDU";
        user.location = location;
        user.name = "大头鬼";

        callJsFunc("functionInJs", GsonUtil.beanToString(user));
    }


    static class Location {
        String address;
    }

    static class User {
        String   name;
        Location location;
        String   testStr;
    }
}
