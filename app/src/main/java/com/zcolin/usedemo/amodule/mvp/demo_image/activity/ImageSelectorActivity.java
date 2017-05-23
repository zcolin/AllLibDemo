/*
 * *********************************************************
 *   author   colin
 *   company  fosung
 *   email    wanglin2046@126.com
 *   date     17-5-22 下午12:06
 * ********************************************************
 */

package com.zcolin.usedemo.amodule.mvp.demo_image.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.zcolin.frame.app.ResultActivityHelper;
import com.zcolin.frame.permission.PermissionHelper;
import com.zcolin.frame.permission.PermissionsResultAction;
import com.zcolin.frame.utils.ToastUtil;
import com.zcolin.usedemo.R;
import com.zcolin.usedemo.amodule.base.mvp.BaseMVPActivity;
import com.zcolin.usedemo.amodule.mvp.demo_image.presenter.ImageSelectorPresenter;
import com.zcolin.usedemo.amodule.mvp.demo_image.view.IImageSelectorView;

import java.util.ArrayList;

import me.nereo.multi_image_selector.MultiImageSelector;


/**
 * 图片选择示例
 */
public class ImageSelectorActivity extends BaseMVPActivity<ImageSelectorPresenter> implements IImageSelectorView {

    private TextView   mResultText;
    private RadioGroup mChoiceMode;
    private RadioGroup mShowCamera;
    private EditText   mRequestNum;

    private ArrayList<String> mSelectPath;

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

        mChoiceMode.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if (checkedId == R.id.multi) {
                    mRequestNum.setEnabled(true);
                } else {
                    mRequestNum.setEnabled(false);
                    mRequestNum.setText("");
                }
            }
        });

        View button = getView(R.id.button);
        if (button != null) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PermissionHelper.requestPermission(mActivity, new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE}, new PermissionsResultAction() {
                        @Override
                        public void onGranted() {
                            pickImage();
                        }

                        @Override
                        public void onDenied(String permission) {
                            ToastUtil.toastShort("请授予本程序拍照和读取文件权限!");
                        }
                    });
                }
            });
        }
    }


    private void pickImage() {
        boolean showCamera = mShowCamera.getCheckedRadioButtonId() == R.id.show;
        int maxNum = mPresenter.getMaxNumber(mRequestNum.getText()
                                                        .toString());
        Intent intent = null;
        if (mChoiceMode.getCheckedRadioButtonId() == R.id.single) {
            intent = MultiImageSelector.create()
                                       .showCamera(showCamera)
                                       .single()
                                       .origin(mSelectPath)
                                       .createIntent(mActivity);
        } else {
            intent = MultiImageSelector.create()
                                       .showCamera(showCamera)
                                       .count(maxNum)
                                       .multi()
                                       .origin(mSelectPath)
                                       .createIntent(mActivity);
        }

        startActivityWithCallback(intent, new ResultActivityHelper.ResultActivityListener() {
            @Override
            public void onResult(int resultCode, Intent data) {
                if (resultCode == RESULT_OK) {
                    mSelectPath = data.getStringArrayListExtra(MultiImageSelector.EXTRA_RESULT);
                    String str = mPresenter.getSelectedPathes(mSelectPath);
                    mResultText.setText(str);
                }
            }
        });
    }
}
