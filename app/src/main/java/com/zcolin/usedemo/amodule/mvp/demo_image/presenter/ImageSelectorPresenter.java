/*
 * *********************************************************
 *   author   colin
 *   company  telchina
 *   email    wanglin2046@126.com
 *   date     18-1-9 下午5:03
 * ********************************************************
 */

package com.zcolin.usedemo.amodule.mvp.demo_image.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zcolin.frame.util.StringUtil;
import com.zcolin.usedemo.amodule.mvp.base.BaseMVPPresenter;
import com.zcolin.usedemo.amodule.mvp.demo_image.view.IImageSelectorView;

import java.util.ArrayList;

/**
 * HttpDemo
 */
public class ImageSelectorPresenter extends BaseMVPPresenter<IImageSelectorView> {

    @Override
    public void onLoad(@Nullable Bundle data) {

    }

    public int getMaxNumber(String maxNumber) {
        int maxNum = 9;
        if (!StringUtil.isEmpty(maxNumber)) {
            try {
                maxNum = Integer.valueOf(maxNumber);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return maxNum;
    }

    public String getSelectedPathes(ArrayList<String> selectedPath) {
        StringBuilder sb = new StringBuilder();
        if (selectedPath != null) {
            for (String p : selectedPath) {
                sb.append(p);
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
