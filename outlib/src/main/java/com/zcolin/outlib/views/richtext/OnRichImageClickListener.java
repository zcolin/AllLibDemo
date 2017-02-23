/*
 * *********************************************************
 *   author   colin
 *   company  fosung
 *   email    wanglin2046@126.com
 *   date     17-2-23 下午3:51
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