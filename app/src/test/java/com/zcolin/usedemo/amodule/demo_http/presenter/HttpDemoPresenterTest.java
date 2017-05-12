/*
 * *********************************************************
 *   author   colin
 *   company  fosung
 *   email    wanglin2046@126.com
 *   date     17-5-7 下午4:05
 * ********************************************************
 */

package com.zcolin.usedemo.amodule.demo_http.presenter;

import com.zcolin.frame.http.response.ZResponse;
import com.zcolin.frame.http.response.ZStringResponse;
import com.zcolin.usedemo.amodule.demo_http.view.IHttpDemoView;
import com.zcolin.usedemo.amodule.main.entity.BaiduWeather;
import com.zcolin.usedemo.biz.ApiMgr;
import com.zcolin.usedemo.http.entity.HttpCommonReply;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doAnswer;

/**
 *
 */
public class HttpDemoPresenterTest {
    private HttpDemoPresenter presenter;
    @Mock
    private IHttpDemoView     view;
    @Mock
    private ApiMgr            apiMgr;

    @Mock
    private BaiduWeather baiduWeather;

    @Mock
    private HttpCommonReply commonReply;

    @Before
    public void setupMocksAndView() {
        // 使用Mock标签等需要先init初始化一下
        MockitoAnnotations.initMocks(this);

        // 用真实接口创建反馈Presenter
        presenter = new HttpDemoPresenter();
        presenter.setApiMgr(apiMgr);
        presenter.onAttach(view);//传入mock对象
    }

    @Test
    public void testGetBaiduStringData() throws Exception {
        //当调用getBaiduStringData中的api.getBaiduStringData(ZStringResponse)时返回mock对象
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                //这里可以获得传给performLogin的参数,callback是第0个参数
                ((ZStringResponse) invocation.getArguments()[0]).onSuccess(null, "success");
                return null;
            }
        })
                .when(apiMgr)
                .getBaiduStringData(any(ZStringResponse.class));

        // 模拟数据，测试函数
        presenter.getBaiduStringData(null);
        //测试是否调用了view层的showResult
        Mockito.verify(view)
               .showResult(anyString());
    }

    @Test
    public void testGetObject() throws Exception {
        baiduWeather.results = new ArrayList<>();
        baiduWeather.results.add(new BaiduWeather.ResultsBean());
        //当调用getBaiduStringData中的api.getBaiduStringData(ZStringResponse)时返回mock对象
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                //这里可以获得传给performLogin的参数,callback是第0个参数
                ((ZResponse) invocation.getArguments()[0]).onSuccess(null, baiduWeather);
                return null;
            }
        })
                .when(apiMgr)
                .getObject(any(ZResponse.class));

        // 模拟数据，测试函数
        presenter.getObject(null);
        //测试是否调用了view层的showResult
        Mockito.verify(view)
               .showResult(anyString());
    }

    @Test
    public void testUploadFile() throws Exception {
        //当调用getBaiduStringData中的api.getBaiduStringData(ZStringResponse)时返回mock对象
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                //这里可以获得传给performLogin的参数,callback是第0个参数
                ((ZResponse) invocation.getArguments()[0]).onSuccess(null, commonReply);
                return null;
            }
        })
                .when(apiMgr)
                .uploadFile(any(ZResponse.class));

        // 模拟数据，测试函数
        presenter.uploadFile(null);
        //测试是否调用了view层的showResult
        Mockito.verify(view)
               .showResult(anyString());
    }

}