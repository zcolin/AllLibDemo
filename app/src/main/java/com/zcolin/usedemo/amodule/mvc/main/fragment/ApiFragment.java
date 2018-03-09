/*
 * *********************************************************
 *   author   colin
 *   company  telchina
 *   email    wanglin2046@126.com
 *   date     18-1-9 下午5:02
 * ********************************************************
 */

package com.zcolin.usedemo.amodule.mvc.main.fragment;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.zcolin.frame.permission.PermissionHelper;
import com.zcolin.frame.permission.PermissionsResultAction;
import com.zcolin.frame.util.ToastUtil;
import com.zcolin.gui.ZConfirm;
import com.zcolin.libamaplocation.LocationUtil;
import com.zcolin.usedemo.R;
import com.zcolin.usedemo.amodule.base.BaseFragment;

import java.util.ArrayList;

import cn.sharesdk.util.ShareSocial;

/**
 * 个人演示页
 */
public class ApiFragment extends BaseFragment implements View.OnClickListener {
    private LinearLayout llContent;
    private ArrayList<Button> listButton = new ArrayList<>();
    private TextView tvMessage;

    public static ApiFragment newInstance() {
        Bundle args = new Bundle();
        ApiFragment fragment = new ApiFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void lazyLoad(@Nullable Bundle savedInstanceState) {

    }

    @Override
    protected int getRootViewLayId() {
        return R.layout.activity_common;
    }


    @Override
    protected void createView(@Nullable Bundle savedInstanceState) {
        super.createView(savedInstanceState);
        tvMessage = getView(R.id.tvMessage);
        llContent = getView(R.id.ll_content);
        listButton.add(addButton("定位"));
        listButton.add(addButton("分享"));

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

    private void location() {
        PermissionHelper.requestPermission(mActivity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE}, new
                PermissionsResultAction() {
            @Override
            public void onGranted() {
                final LocationUtil location = new LocationUtil(mActivity);
                location.startLocation(new LocationUtil.OnGetLocation() {
                    @Override
                    public void getLocation(AMapLocation location) {
                /*设置位置描述*/
                        String desc = null;
                        Bundle locBundle = location.getExtras();
                        if (locBundle != null) {
                            desc = locBundle.getString("desc");
                        }
                        tvMessage.setText(locBundle == null ? location.getCity() + location.getDistrict() : desc);
                    }

                    @Override
                    public void locationFail() {
                        new ZConfirm(mActivity).setTitle("定位失败, 是否尝试再次定位？").addSubmitListener(() -> {
                            location();
                            return true;
                        }).addCancelListener(() -> {
                            tvMessage.setText("定位失败");
                            return true;
                        }).show();
                    }
                });
            }

            @Override
            public void onDenied(String permission) {
                ToastUtil.toastShort("请授予本程序[定位]和[写SD卡权限]");
            }
        });
    }


    @Override
    public void onClick(View v) {
        if (v == listButton.get(0)) {
            location();
        }
        if (v == listButton.get(1)) {
            ShareSocial.instance()
                       .setTitle("分享")
                       .setContent("分享内容")
                       .setTargetUrl("http://www.baidu.com")
                       .setImgUrl("http://pic6.huitu.com/res/20130116/84481_20130116142820494200_1.jpg")
                       .share(mActivity);
        }
    }
}
