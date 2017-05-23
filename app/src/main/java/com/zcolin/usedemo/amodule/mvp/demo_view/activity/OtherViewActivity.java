/*
 * *********************************************************
 *   author   colin
 *   company  fosung
 *   email    wanglin2046@126.com
 *   date     17-5-22 下午3:33
 * ********************************************************
 */

package com.zcolin.usedemo.amodule.mvp.demo_view.activity;


import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;

import com.zcolin.frame.utils.ToastUtil;
import com.zcolin.gui.ZBanner;
import com.zcolin.gui.ZDialogAsyncProgress;
import com.zcolin.gui.ZDialogProgress;
import com.zcolin.gui.ZTextSwitcher;
import com.zcolin.usedemo.R;
import com.zcolin.usedemo.amodule.base.mvp.BaseMVPActivity;
import com.zcolin.usedemo.amodule.base.mvp.Presenter;
import com.zcolin.usedemo.amodule.mvp.demo_view.presenter.OtherViewPresenter;
import com.zcolin.usedemo.amodule.mvp.demo_view.view.OtherViewView;

import java.util.ArrayList;

/**
 * 其他的一些View的示例
 */
@Presenter(OtherViewPresenter.class)
public class OtherViewActivity extends BaseMVPActivity<OtherViewPresenter> implements OtherViewView {
    private ZTextSwitcher textSwitcher;
    private ZBanner       banner;
    private Button        btn1;
    private Button        btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otherview);

        initView();
    }


    @Override
    public void onResume() {
        super.onResume();
        if (!textSwitcher.isStart() && textSwitcher.isInit()) {
            textSwitcher.startSwitcher();
        }

        if (!banner.isStart() && banner.isInit()) {
            banner.startAutoPlay();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        textSwitcher.stopSwitcher();
        banner.stopAutoPlay();
    }


    protected void initView() {
        textSwitcher = getView(R.id.view_textswitcher);
        banner = getView(R.id.view_banner);
        btn1 = getView(R.id.btn_1);
        btn2 = getView(R.id.btn_2);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDlgAsyncProgress();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDlgProgress();
            }
        });
    }

    @Override
    public void startTextSwitcher(String str) {
        textSwitcher.setTextColor(Color.BLACK)
                    .setSwitchInterval(4000)
                    .setTextSize(20)
                    .setText(str)
                    .setOutAnima(mActivity, R.anim.textswitcher_slide_out)
                    .setInAnima(mActivity, R.anim.textswitcher_slide_in)
                    .startSwitcher();

    }

    @Override
    public void startBanner(ArrayList<String> listUrl) {
        banner.setBannerStyle(ZBanner.CIRCLE_INDICATOR_TITLE)
              .setIndicatorGravity(ZBanner.CENTER)
              .setBannerTitle(listUrl)
              .setDelayTime(4000)
              .setOnBannerClickListener(new ZBanner.OnBannerClickListener() {
                  @Override
                  public void OnBannerClick(View view, int position) {
                      ToastUtil.toastShort("点击了第" + (position + 1) + "张图片");
                  }
              })
              .setImages(listUrl)
              .startAutoPlay();
    }


    /**
     * 异步执行任务，显示ProgressDialog
     */
    private void showDlgAsyncProgress() {
        final ZDialogAsyncProgress dlg = new ZDialogAsyncProgress(mActivity);
        dlg.setDoInterface(new ZDialogAsyncProgress.DoInterface() {
            @Override
            public ZDialogAsyncProgress.ProcessInfo onDoInback() {
                mPresenter.mockBackGroundThreadTask(1000);
                dlg.setMessage("执行第二步");
                mPresenter.mockBackGroundThreadTask(2000);

                ZDialogAsyncProgress.ProcessInfo info = new ZDialogAsyncProgress.ProcessInfo();
                info.msg = "执行结果：success";
                return info;
            }

            @Override
            public void onPostExecute(ZDialogAsyncProgress.ProcessInfo info) {
                ToastUtil.toastShort(info.msg);
            }
        });
        dlg.execute(0);
    }

    /**
     * 异步执行任务，显示ProgressDialog
     */
    private void showDlgProgress() {
        final ZDialogProgress progress = new ZDialogProgress(mActivity);
        new AsyncTask<Integer, Integer, Integer>() {
            @Override
            protected Integer doInBackground(Integer... params) {
                mPresenter.mockBackGroundThreadTask(2000);
                return null;
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progress.show();
            }

            @Override
            protected void onPostExecute(Integer integer) {
                super.onPostExecute(integer);
                progress.dismiss();
            }
        }.execute(0);
    }
}
