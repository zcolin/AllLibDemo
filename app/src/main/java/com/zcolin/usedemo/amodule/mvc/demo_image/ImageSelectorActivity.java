/*
 * *********************************************************
 *   author   colin
 *   company  telchina
 *   email    wanglin2046@126.com
 *   date     18-1-9 下午5:02
 * ********************************************************
 */

package com.zcolin.usedemo.amodule.mvc.demo_image;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.zcolin.frame.permission.PermissionHelper;
import com.zcolin.frame.permission.PermissionsResultAction;
import com.zcolin.frame.util.ToastUtil;
import com.zcolin.usedemo.R;
import com.zcolin.usedemo.amodule.base.BaseActivity;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.SelectionCreator;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.util.List;

/**
 * 图片选择示例
 */
public class ImageSelectorActivity extends BaseActivity {

    private TextView   mResultText;
    private RadioGroup mChoiceMode, mShowCamera;
    private EditText mRequestNum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imageselector);

        initView();
    }


    protected void initView() {
        mResultText = getView(R.id.result);
        mChoiceMode = getView(R.id.choice_mode);
        mShowCamera = getView(R.id.show_camera);
        mRequestNum = getView(R.id.request_num);
        View button = getView(R.id.button);

        mChoiceMode.setOnCheckedChangeListener((radioGroup, checkedId) -> {
            if (checkedId == R.id.multi) {
                mRequestNum.setEnabled(true);
            } else {
                mRequestNum.setEnabled(false);
                mRequestNum.setText("");
            }
        });

        button.setOnClickListener(view -> PermissionHelper.requestPermission(mActivity, new String[]{Manifest.permission.CAMERA, Manifest.permission
                .READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, new PermissionsResultAction() {
            @Override
            public void onGranted() {
                pickImage();
            }

            @Override
            public void onDenied(String permission) {
                ToastUtil.toastShort("请授予本程序拍照和读取文件权限!");
            }
        }));
    }


    private void pickImage() {
        boolean showCamera = mShowCamera.getCheckedRadioButtonId() == R.id.show;
        int maxNum = 9;

        if (!TextUtils.isEmpty(mRequestNum.getText())) {
            try {
                maxNum = Integer.valueOf(mRequestNum.getText().toString());
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        SelectionCreator selCreator;
        if (mChoiceMode.getCheckedRadioButtonId() == R.id.single) {
            selCreator = Matisse.from(mActivity)
                                .choose(MimeType.ofImage(), true)
                                .showSingleMediaType(true)
                                .countable(true)
                                .maxSelectable(1)
                                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                                .thumbnailScale(0.85F)
                                .imageEngine(new GlideEngine())
                                .theme(com.zhihu.matisse.R.style.Matisse_Dracula)
                                .countable(false);
        } else {
            selCreator = Matisse.from(mActivity)
                                .choose(MimeType.ofImage(), true)
                                .showSingleMediaType(true)
                                .countable(true)
                                .maxSelectable(maxNum)
                                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                                .thumbnailScale(0.85F)
                                .imageEngine(new GlideEngine())
                                .theme(com.zhihu.matisse.R.style.Matisse_Dracula)
                                .countable(false);
        }

        if (showCamera) {
            selCreator.capture(true).captureStrategy(new CaptureStrategy(true, getPackageName() + ".matisse_fileprovider"));
        }
        Intent intent = selCreator.createIntent();

        startActivityWithCallback(intent, (resultCode, data) -> {
            if (resultCode == RESULT_OK && data != null) {
                List<String> mSelectPath = Matisse.obtainPathResult(data);
                StringBuilder sb = new StringBuilder();
                for (String p : mSelectPath) {
                    sb.append(p);
                    sb.append("\n");
                }
                mResultText.setText(sb.toString());
            }
        });
    }
}
