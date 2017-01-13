/***********************************************************
 * author   colin
 * company  fosung
 * email    wanglin2046@126.com
 * date     16-7-18 下午1:47
 **********************************************************/

package com.zcolin.usedemo.amodule.main.fragment;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.zcolin.usedemo.amodule.demo_video.EasyPRActivity;
import com.fosung.frame.app.BaseFrameLazyLoadFrag;
import com.fosung.frame.permission.PermissionHelper;
import com.fosung.frame.permission.PermissionsResultAction;
import com.fosung.frame.utils.ActivityUtil;
import com.fosung.frame.utils.ToastUtil;
import com.zcolin.usedemo.R;
import com.zcolin.usedemo.amodule.demo_image.ImageSelectorActivity;
import com.zcolin.usedemo.amodule.demo_image.ImageUtilActivity;
import com.zcolin.usedemo.amodule.demo_video.QrCodeScanActivity;
import com.zcolin.usedemo.amodule.demo_video.RecordVideoActivity;
import com.zcolin.usedemo.amodule.demo_video.ijkplayer.NavigationActivity;

import java.util.ArrayList;


/**
 * 视频图片Demo展示
 */
public class VideoImageFragment extends BaseFrameLazyLoadFrag implements View.OnClickListener {
    private LinearLayout llContent;
    private ArrayList<Button> listButton = new ArrayList<>();

    public static VideoImageFragment newInstance() {
        Bundle args = new Bundle();
        VideoImageFragment fragment = new VideoImageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getRootViewLayId() {
        return R.layout.activity_common;
    }

    @Override
    protected void lazyLoad() {
        init();
    }

    private void init() {
        llContent = getView(R.id.ll_content);
        listButton.add(addButton("ijk视频播放"));
        listButton.add(addButton("视频录制Demo"));
        listButton.add(addButton("扫码"));
        listButton.add(addButton("图片选择"));
        listButton.add(addButton("图片加载"));
        listButton.add(addButton("车牌识别"));

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

    @Override
    public void onClick(View v) {
        if(v == listButton.get(0)){
            ActivityUtil.startActivity(mActivity, NavigationActivity.class);
        }else if (v == listButton.get(1)) {
            PermissionHelper.requestPermission(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO}, new PermissionsResultAction() {
                @Override
                public void onGranted() {
                    ActivityUtil.startActivity(mActivity, RecordVideoActivity.class);
                }

                @Override
                public void onDenied(String permission) {
                    ToastUtil.toastShort("请授予本程序拍照和录音权限!");
                }
            });
        } else if (v == listButton.get(2)) {
            PermissionHelper.requestPermission(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.VIBRATE}, new PermissionsResultAction() {
                @Override
                public void onGranted() {
                    ActivityUtil.startActivity(mActivity, QrCodeScanActivity.class);
                }

                @Override
                public void onDenied(String permission) {
                    ToastUtil.toastShort("请授予本程序拍照和震动权限!");
                }
            });
        } else if (v == listButton.get(3)) {
            ActivityUtil.startActivity(mActivity, ImageSelectorActivity.class);
        }else if (v == listButton.get(4)) {
            ActivityUtil.startActivity(mActivity, ImageUtilActivity.class);
        }else if (v == listButton.get(5)) {
            PermissionHelper.requestCameraPermission(this, new PermissionsResultAction() {
                @Override
                public void onGranted() {
                    Intent intent = new Intent(mActivity, EasyPRActivity.class);
                    startActivity(intent);
                }

                @Override
                public void onDenied(String permission) {
                    ToastUtil.toastShort("请赋予本程序拍照权限！");
                }
            });
        }
    }
}
