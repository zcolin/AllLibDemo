/***********************************************************
 * author   colin
 * company  fosung
 * email    wanglin2046@126.com
 * date     16-7-18 下午5:24
 **********************************************************/

package com.zcolin.usedemo.amodule.demo_image;


import android.os.Bundle;
import android.widget.ImageView;

import com.fosung.frame.imageloader.ImageLoaderUtils;
import com.zcolin.usedemo.R;
import com.zcolin.usedemo.amodule.base.BaseSecondLevelActivity;


/**
 * 图片加载Demo
 */
public class ImageUtilActivity extends BaseSecondLevelActivity {
    private ImageView iv1;
    private ImageView iv2;
    private ImageView iv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imageutil);

        iv1 = getView(R.id.iv_1);
        iv2 = getView(R.id.iv_2);
        iv3 = getView(R.id.iv_3);

        displayImage();
    }

    public void displayImage() {
        ImageLoaderUtils.displayImage(this, "http://img1.imgtn.bdimg.com/it/u=1480985633,1206349730&fm=21&gp=0.jpg", iv1);
        // ImageLoaderUtils.displayImageWithPlaceholder(this, "http://img1.imgtn.bdimg.com/it/u=1480985633,1206349730&fm=21&gp=0.jpg", iv1, R.drawable.ic_launcher);
        ImageLoaderUtils.displayCircleImage(this, "http://img1.imgtn.bdimg.com/it/u=1480985633,1206349730&fm=21&gp=0.jpg", iv2);
        ImageLoaderUtils.displayRoundCornersImage(this, "http://img1.imgtn.bdimg.com/it/u=1480985633,1206349730&fm=21&gp=0.jpg", iv3, 15, R.drawable.ic_launcher);
    }
}
