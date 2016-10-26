/***********************************************************
 * author   colin
 * company  fosung
 * email    wanglin2046@126.com
 * date     16-7-18 下午5:24
 **********************************************************/
package com.fosung.usedemo.amodule.demo_view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.fosung.frame.jsbridge.BridgeHandler;
import com.fosung.frame.jsbridge.CallBackFunction;
import com.fosung.frame.jsbridge.DefaultHandler;
import com.fosung.frame.utils.GsonUtil;
import com.fosung.gui.ZAlert;
import com.fosung.gui.ZDlg;
import com.fosung.gui.base.BaseSecondLevelActivity;
import com.fosung.gui.webview.ZWebChooseFileChromeClient;
import com.fosung.gui.webview.ZWebView;
import com.fosung.usedemo.R;

/**
 * 带JsBridge的webview的Demo
 */
public class WebViewActivity extends BaseSecondLevelActivity implements OnClickListener {
    private ZWebView                   webView;
    private Button                     button;
    private ZWebChooseFileChromeClient webChromeClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        webView = (ZWebView) getView(R.id.webView);
        button = (Button) getView(R.id.button);
        button.setOnClickListener(this);

        initWebView();
        loadUrl();

        getUserDataFrom_xx();
    }

    public void initWebView() {
        webChromeClient = new ZWebChooseFileChromeClient(this);
        webView.setWebChromeClient(webChromeClient);

        webView.setDefaultHandler(new DefaultHandler());//如果JS调用send方法，会走到DefaultHandler里
        webView.registerHandler("submitFromWeb", new BridgeHandler() {
            @Override
            public void handler(String data, final CallBackFunction function) {
                new ZAlert(mActivity).setMessage("监听到网页传入数据：" + data)
                                     .addSubmitListener(new ZDlg.ZDialogSubmitInterface() {
                                         @Override
                                         public boolean submit() {
                                             function.onCallBack("java 返回数据！！！");
                                             return true;
                                         }
                                     })
                                     .show();
            }
        });
        webView.registerStartActivity(mActivity);
        webView.registerFinishActivity(mActivity);
    }

    public void loadUrl() {
        webView.loadUrl("file:///android_asset/demo.html");
    }

    public void callJsFunc(String funcName, String strParam) {
        webView.callHandler(funcName, strParam, new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                new ZAlert(mActivity).setMessage("网页返回数据：" + data)
                                     .show();
            }
        });
        //webView.send("hello");
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        webChromeClient.processResult(requestCode, resultCode, intent);
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
