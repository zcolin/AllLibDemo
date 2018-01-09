/*
 * *********************************************************
 *   author   colin
 *   company  telchina
 *   email    wanglin2046@126.com
 *   date     18-1-9 下午5:03
 * ********************************************************
 */

package com.zcolin.outlib.views.richtext;

import java.util.List;

public interface OnRichImageClickListener {
    /**
     * 图片被点击后的回调方法
     *
     * @param imageUrls 本篇富文本内容里的全部图片
     * @param position  点击处图片在imageUrls中的位置
     */
    void onImageClick(List<String> imageUrls, int position);
}