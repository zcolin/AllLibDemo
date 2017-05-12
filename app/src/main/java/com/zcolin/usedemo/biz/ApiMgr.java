/*
 * *********************************************************
 *   author   colin
 *   company  fosung
 *   email    wanglin2046@126.com
 *   date     17-5-8 上午9:13
 * ********************************************************
 */

package com.zcolin.usedemo.biz;

import com.zcolin.frame.http.ZHttp;
import com.zcolin.frame.http.response.ZResponse;
import com.zcolin.frame.http.response.ZStringResponse;
import com.zcolin.usedemo.amodule.main.entity.BaiduWeather;
import com.zcolin.usedemo.http.HttpUrl;
import com.zcolin.usedemo.http.entity.HttpCommonReply;

import java.io.File;
import java.util.HashMap;

/**
 * 网络请求管理
 */
public class ApiMgr {
    private static ApiMgr apiMgr = new ApiMgr();

    public static ApiMgr getInstance() {
        return apiMgr;
    }

    /**
     * 获取为经解析的字符串
     */
    public void getBaiduStringData(ZStringResponse response) {
        ZHttp.get(HttpUrl.URL_BAIDU_TEST, response);
    }


    /**
     * 获取对象
     * 实际使用时一般使用此方法，而不是直接解析字符窜
     */
    public void getObject(ZResponse<BaiduWeather> response) {
        ZHttp.get(HttpUrl.URL_BAIDU_TEST, response);
    }

    /**
     * POST请求， 返回Gson序列化的对象
     * <p>
     */
    public void postResponse(ZResponse<HttpCommonReply> response) {
        HashMap<String, String> params = new HashMap<>();
        params.put("param1", "sss");
        ZHttp.post(HttpUrl.URL_BAIDU_TEST, params, response);
    }

    /**
     * 上传文件，可以和其他参数一起上传，也可以单独上传
     */
    public void uploadFile(ZResponse<HttpCommonReply> response) {
        HashMap<String, String> params = new HashMap<>();
        params.put("param1", "sss");
        HashMap<String, File> fileParams = new HashMap<>();
        fileParams.put("key", new File(""));
        ZHttp.uploadFile(HttpUrl.URL_BAIDU_TEST, params, fileParams, response);
    }
}
