/*
 * *********************************************************
 *   author   colin
 *   company  fosung
 *   email    wanglin2046@126.com
 *   date     17-5-22 下午12:06
 * ********************************************************
 */

package com.zcolin.usedemo.amodule.mvp.demo_image.activity;


import android.os.Bundle;
import android.widget.ImageView;

import com.zcolin.frame.imageloader.ImageLoaderUtils;
import com.zcolin.usedemo.R;
import com.zcolin.usedemo.amodule.base.BaseActivity;


/**
 * 图片加载Demo
 */
public class ImageUtilActivity extends BaseActivity {
    private ImageView iv1;
    private ImageView iv2;
    private ImageView iv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imageutil);

        initView();
        displayImage();
    }

    protected void initView() {
        iv1 = getView(R.id.iv_1);
        iv2 = getView(R.id.iv_2);
        iv3 = getView(R.id.iv_3);
    }

    public void displayImage() {
        ImageLoaderUtils.displayImage(this, "http://img1.imgtn.bdimg.com/it/u=1480985633,1206349730&fm=21&gp=0.jpg", iv1);
        // ImageLoaderUtils.displayImageWithPlaceholder(this, "http://img1.imgtn.bdimg.com/it/u=1480985633,1206349730&fm=21&gp=0.jpg", iv1, R.drawable.ic_launcher);
        ImageLoaderUtils.displayCircleImage(this, "http://img1.imgtn.bdimg.com/it/u=1480985633,1206349730&fm=21&gp=0.jpg", iv2);
        ImageLoaderUtils.displayRoundCornersImage(this, "http://img1.imgtn.bdimg.com/it/u=1480985633,1206349730&fm=21&gp=0.jpg", iv3, 15, R.drawable.ic_launcher);
    }
}
