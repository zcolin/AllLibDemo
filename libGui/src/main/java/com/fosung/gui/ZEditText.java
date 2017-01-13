/***********************************************************
 * author   colin
 * company  fosung
 * email    wanglin2046@126.com
 * date     16-7-22 下午2:09
 **********************************************************/
package com.zcolin.gui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;



/**
 * 普通的EditText，预先做了公共属性设置
 */
public class ZEditText extends EditText {
    public ZEditText(Context context) {
        this(context, null);
    }

    public ZEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        commonSet(context);
    }

    public ZEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        commonSet(context);
    }

    private void commonSet(Context context) {
        this.setMinimumHeight((int) context.getResources()
                                           .getDimension(R.dimen.gui_dimens_item_height_small));
        setTextAppearance(context, R.style.Gui_TextStyle_BlackLight_Normal);
        setSingleLine(true);
    }
}
