/*
 * *********************************************************
 *   author   colin
 *   company  telchina
 *   email    wanglin2046@126.com
 *   date     18-1-9 下午5:03
 * ********************************************************
 */

package com.zcolin.usedemo.amodule.mvvm.demo_http.presenter;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zcolin.frame.http.ZHttp;
import com.zcolin.frame.http.response.ZResponse;
import com.zcolin.frame.http.response.ZStringResponse;
import com.zcolin.frame.util.LogUtil;
import com.zcolin.usedemo.amodule.mvp.base.BaseMVPPresenter;
import com.zcolin.usedemo.amodule.mvvm.demo_http.view.IHttpDemoView;
import com.zcolin.usedemo.biz.ApiMgr;
import com.zcolin.usedemo.entity.BaiduWeather;
import com.zcolin.usedemo.http.entity.HttpCommonReply;

import okhttp3.Call;
import okhttp3.Response;

/**
 * HttpDemo
 */
public class HttpDemoPresenter extends BaseMVPPresenter<IHttpDemoView> {

    private String[] arrayTag = new String[4];

    @Override
    public void onLoad(@Nullable Bundle data) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ZHttp.cancelRequest(arrayTag);
    }

    /**
     * 获取为经解析的字符串
     * <p>
     * 此处为Demo，实际使用一般使用{@link #getObject(Activity)}
     */
    public void getBaiduStringData(Activity activity) {
        arrayTag[0] = ApiMgr.getBaiduStringData(new ZStringResponse(activity, "正在获取数据") {
            @Override
            public void onSuccess(Response response, String resObj) {
                mView.showResult(resObj);
            }

            @Override
            public void onError(int code, Call call, Exception e) {
                mView.showError(LogUtil.ExceptionToString(e));
            }
        });
    }


    /**
     * 获取对象
     * 实际使用时一般使用此方法，而不是直接解析字符窜
     * <p>
     * ZResponse的参数，第二个参数是显示旋转进度条则传入，第三个参数是进度条文字，如果不需要进度条则只需要传入第一个参数
     */
    public void getObject(Activity activity) {
        arrayTag[1] = ApiMgr.getObject(new ZResponse<BaiduWeather>(BaiduWeather.class, activity, "正在获取数据……") {
            @Override
            public void onSuccess(Response response, BaiduWeather resObj) {
                if (resObj.results.size() > 0) {
                    BaiduWeather.ResultsBean bean = resObj.results.get(0);
                    mView.showResult("city:" + bean.currentCity + " pm25:" + bean.pm25);
                }
            }
        });
    }

    /**
     * POST请求， 返回Gson序列化的对象
     * <p>
     * 带有progressBar
     */
    public void postResponse(Activity activity) {
        arrayTag[2] = ApiMgr.postResponse(new ZResponse<HttpCommonReply>(HttpCommonReply.class, activity, "正在加载……") {
            @Override
            public void onSuccess(Response response, HttpCommonReply httpBaseBean) {
                mView.showResult(String.valueOf(httpBaseBean));
            }

            @Override
            public void onError(int code, String error) {
                mView.showError(error);
            }
        });
    }

    /**
     * 上传文件，可以和其他参数一起上传，也可以单独上传
     */
    public void uploadFile(Activity activity) {
        arrayTag[3] = ApiMgr.uploadFile(new ZResponse<HttpCommonReply>(HttpCommonReply.class, activity, "正在加载……") {
            @Override
            public void onSuccess(Response response, HttpCommonReply httpBaseBean) {
                mView.showResult("上传成功");
            }

            @Override
            public void onError(int code, String error) {
                mView.showError(error);
            }
        });
    }

}
