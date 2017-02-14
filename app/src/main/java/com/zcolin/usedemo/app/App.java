/***********************************************************
 * author   colin
 * company  fosung
 * email    wanglin2046@126.com
 * date     16-7-15 上午9:54
 **********************************************************/

package com.zcolin.usedemo.app;

import android.content.Context;

import com.zcolin.frame.app.BaseApp;
import com.zcolin.frame.http.okhttp.OkHttpUtils;
import com.zcolin.frame.http.okhttp.cookie.CookieJarImpl;
import com.zcolin.frame.http.okhttp.cookie.store.MemoryCookieStore;
import com.zcolin.frame.http.okhttp.https.HttpsUtils;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.OkHttpClient;

/**
 * 程序入口
 */
public class App extends BaseApp {

    public static Context APP_CONTEXT;

    @Override
    public void onCreate() {
        super.onCreate();
        //if (LeakCanary.isInAnalyzerProcess(this)) {
        //            // This process is dedicated to LeakCanary for heap analysis.
        //            // You should not init your app in this process.
        //            return;
        //        }
        //        LeakCanary.install(this);
        // Normal app init code...

        //facebook调试工具
        //if (BuildConfig.DEBUG) {
        //    Stetho.initializeWithDefaults(this);
        //}
        
        APP_CONTEXT = BaseApp.APP_CONTEXT;
        initHttpOptions();
    }

    private void initHttpOptions() {
        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, null);
        CookieJarImpl cookieJar1 = new CookieJarImpl(new MemoryCookieStore());
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //facebook调试工具
        //        if (BuildConfig.DEBUG) {
        //            builder.addNetworkInterceptor(new StethoInterceptor());
        //        }
        OkHttpClient okHttpClient = builder.connectTimeout(10000L, TimeUnit.MILLISECONDS)
                                           .readTimeout(10000L, TimeUnit.MILLISECONDS)
                                           .cookieJar(cookieJar1)
                                           .hostnameVerifier(new HostnameVerifier() {
                                               @Override
                                               public boolean verify(String hostname, SSLSession session) {
                                                   return true;
                                               }
                                           })
                                           .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                                           .build();
        OkHttpUtils.initClient(okHttpClient);
    }
}
