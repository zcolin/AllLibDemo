package com.zcolin.usedemo.amodule.demo_image;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.zcolin.frame.app.ResultActivityHelper;
import com.zcolin.frame.permission.PermissionHelper;
import com.zcolin.frame.permission.PermissionsResultAction;
import com.zcolin.frame.utils.ToastUtil;
import com.zcolin.usedemo.R;
import com.zcolin.usedemo.amodule.base.BaseSecondLevelActivity;

import java.util.ArrayList;

import me.nereo.multi_image_selector.MultiImageSelector;


/**
 * 图片选择示例
 */
public class ImageSelectorActivity extends BaseSecondLevelActivity {

    private TextView   mResultText;
    private RadioGroup mChoiceMode, mShowCamera;
    private EditText mRequestNum;

    private ArrayList<String> mSelectPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imageselector);

        mResultText = (TextView) findViewById(R.id.result);
        mChoiceMode = (RadioGroup) findViewById(R.id.choice_mode);
        mShowCamera = (RadioGroup) findViewById(R.id.show_camera);
        mRequestNum = (EditText) findViewById(R.id.request_num);

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

        View button = findViewById(R.id.button);
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
        int maxNum = 9;

        if (!TextUtils.isEmpty(mRequestNum.getText())) {
            try {
                maxNum = Integer.valueOf(mRequestNum.getText()
                                                    .toString());
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

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
                    StringBuilder sb = new StringBuilder();
                    for (String p : mSelectPath) {
                        sb.append(p);
                        sb.append("\n");
                    }
                    mResultText.setText(sb.toString());
                }
            }
        });
    }
}
