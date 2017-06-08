/*
 * *********************************************************
 *   author   colin
 *   company  fosung
 *   email    wanglin2046@126.com
 *   date     16-11-9 下午3:41
 * ********************************************************
 */

package com.zcolin.usedemo.utils;

import android.content.Intent;

import com.zcolin.frame.app.BaseFrameActivity;
import com.zcolin.frame.app.BaseFrameFrag;
import com.zcolin.frame.app.ResultActivityHelper;
import com.zcolin.frame.permission.PermissionHelper;
import com.zcolin.frame.permission.PermissionsResultAction;
import com.zcolin.frame.util.ToastUtil;

import me.nereo.multi_image_selector.MultiImageSelector;

/**
 * 选取图片的工具类，因为此类使用了MutilImageSelector，所以在客户端实现
 * {@link com.zcolin.frame.utils.SystemIntentUtil#selectPhoto(Object, SystemIntentUtil.OnCompleteLisenter)} 是使用的系统选择
 */
public class PhotoSelectedUtil {

    /**
     * 选取一张图片
     *
     * @param context 只能为BaseFrameFrag或者BaseActivity的子类
     */
    public static void selectPhoto(final Object context, final ResultActivityHelper.ResultActivityListener resultListener) {
        PermissionHelper.requestReadSdCardPermission(context, new PermissionsResultAction() {
            @Override
            public void onGranted() {
                if (context instanceof BaseFrameActivity) {
                    Intent intent = MultiImageSelector.create()
                                                      .single()
                                                      .createIntent((BaseFrameActivity) context);
                    ((BaseFrameActivity) context).startActivityWithCallback(intent, resultListener);
                } else if (context instanceof BaseFrameFrag) {
                    Intent intent = MultiImageSelector.create()
                                                      .single()
                                                      .createIntent(((BaseFrameFrag) context).getActivity());
                    ((BaseFrameFrag) context).startActivityWithCallback(intent, resultListener);
                }
            }

            @Override
            public void onDenied(String permission) {
                ToastUtil.toastShort("请授予本程序读取SD卡权限");
            }
        });
    }

    /**
     * 选取多张图片
     *
     * @param context           只能为BaseFrameFrag或者BaseActivity的子类
     * @param isCotainTakePhoto 是否包含拍照按钮
     * @param photoNumber       选取的最大图片数量
     */
    public static void selectPhoto(final Object context, final boolean isCotainTakePhoto, final int photoNumber, final ResultActivityHelper.ResultActivityListener resultListener) {
        PermissionHelper.requestReadSdCardPermission(context, new PermissionsResultAction() {
            @Override
            public void onGranted() {
                if (context instanceof BaseFrameActivity) {
                    Intent intent = MultiImageSelector.create()
                                                      .multi()
                                                      .showCamera(isCotainTakePhoto)
                                                      .count(photoNumber)
                                                      .createIntent((BaseFrameActivity) context);
                    ((BaseFrameActivity) context).startActivityWithCallback(intent, resultListener);
                } else if (context instanceof BaseFrameFrag) {
                    Intent intent = MultiImageSelector.create()
                                                      .multi()
                                                      .showCamera(isCotainTakePhoto)
                                                      .count(photoNumber)
                                                      .createIntent(((BaseFrameFrag) context).getActivity());
                    ((BaseFrameFrag) context).startActivityWithCallback(intent, resultListener);
                }
            }

            @Override
            public void onDenied(String permission) {
                ToastUtil.toastShort("请授予本程序读取SD卡权限");
            }
        });
    }
}
