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
import com.zcolin.usedemo.entity.BaiduWeather;
import com.zcolin.usedemo.http.HttpUrl;
import com.zcolin.usedemo.http.entity.HttpCommonReply;

import java.io.File;
import java.util.HashMap;

/**
 * 网络请求管理
 */
public class ApiMgr {

    /**
     * 获取为经解析的字符串
     */
    public static String getBaiduStringData(ZStringResponse response) {
        return ZHttp.get(HttpUrl.URL_BAIDU_TEST, response);
    }

    /**
     * 获取对象
     * 实际使用时一般使用此方法，而不是直接解析字符串
     */
    public static String getObject(ZResponse<BaiduWeather> response) {
        return ZHttp.get(HttpUrl.URL_BAIDU_TEST, response);
    }

    /**
     * POST请求， 返回Gson序列化的对象
     * <p>
     */
    public static String postResponse(ZResponse<HttpCommonReply> response) {
        HashMap<String, String> params = new HashMap<>();
        params.put("param1", "sss");
        return ZHttp.post(HttpUrl.URL_BAIDU_TEST, params, response);
    }

    /**
     * 上传文件，可以和其他参数一起上传，也可以单独上传
     */
    public static String uploadFile(ZResponse<HttpCommonReply> response) {
        HashMap<String, String> params = new HashMap<>();
        params.put("param1", "sss");
        HashMap<String, File> fileParams = new HashMap<>();
        fileParams.put("key", new File(""));
        return ZHttp.uploadFile(HttpUrl.URL_BAIDU_TEST, params, fileParams, response);
    }
}
