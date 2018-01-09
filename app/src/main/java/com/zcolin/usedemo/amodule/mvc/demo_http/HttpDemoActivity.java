/*
 * *********************************************************
 *   author   colin
 *   company  telchina
 *   email    wanglin2046@126.com
 *   date     18-1-9 下午5:02
 * ********************************************************
 */

package com.zcolin.usedemo.amodule.mvc.demo_http;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zcolin.frame.http.ZHttp;
import com.zcolin.frame.http.response.ZResponse;
import com.zcolin.frame.http.response.ZStringResponse;
import com.zcolin.frame.util.LogUtil;
import com.zcolin.usedemo.R;
import com.zcolin.usedemo.amodule.base.BaseActivity;
import com.zcolin.usedemo.biz.ApiMgr;
import com.zcolin.usedemo.entity.BaiduWeather;
import com.zcolin.usedemo.http.entity.HttpCommonReply;

import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Response;


/**
 * HttpDemo
 */
public class HttpDemoActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout llContent;
    private TextView     textView;
    private ArrayList<Button> listButton = new ArrayList<>();
    private String[]          arrayTag   = new String[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_db);
        setToolbarTitle("HttpDemo");

        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ZHttp.cancelRequest(arrayTag);
    }

    protected void initView() {
        llContent = getView(R.id.ll_content);
        textView = getView(R.id.textview);
        textView.setMovementMethod(new ScrollingMovementMethod());
        listButton.add(addButton("获取百度数据"));
        listButton.add(addButton("获取对象"));
        listButton.add(addButton("Post数据(请看代码，无效果)"));
        listButton.add(addButton("上传文件(请看代码，无效果)"));

        for (Button btn : listButton) {
            btn.setOnClickListener(this);
        }
    }


    private Button addButton(String text) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        Button button = new Button(mActivity);
        button.setText(text);
        button.setGravity(Gravity.CENTER);
        button.setAllCaps(false);
        llContent.addView(button, params);
        return button;
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
                textView.setText(resObj);
            }

            @Override
            public void onError(int code, Call call, Exception e) {
                textView.setText(LogUtil.ExceptionToString(e));
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
                    textView.setText("city:" + bean.currentCity + " pm25:" + bean.pm25);
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
                textView.setText(String.valueOf(httpBaseBean));
            }

            @Override
            public void onError(int code, String error) {
                textView.setText(error);
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
                textView.setText("上传成功");
            }

            @Override
            public void onError(int code, String error) {
                super.onError(code, error);
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v == listButton.get(0)) {
            getBaiduStringData(mActivity);
        } else if (v == listButton.get(1)) {
            getObject(mActivity);
        }
    }
}
