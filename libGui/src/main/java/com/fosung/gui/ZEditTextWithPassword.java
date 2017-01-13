/***********************************************************
 * author   colin
 * company  fosung
 * email    wanglin2046@126.com
 * date     16-7-25 下午2:47
 **********************************************************/
package com.zcolin.gui;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;


/**
 * 带有密码显示按钮的edittext
 */
public class ZEditTextWithPassword extends EditText {
    private int resPasswordOffAble = R.drawable.gui_icon_edittext_password;
    private int resPasswordOnAble  = R.drawable.gui_icon_edittext_password_s;

    private int     ableWidth;
    private boolean isPossword;
    private Context mContext;

    public ZEditTextWithPassword(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public ZEditTextWithPassword(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        init();
    }

    public ZEditTextWithPassword(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    private void init() {
        setTextAppearance(getContext(), R.style.style_edittext_common);

        isPossword = true;
        setTransformationMethod(PasswordTransformationMethod.getInstance());

        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), resPasswordOffAble, opts);
        ableWidth = opts.outWidth;
        setDrawable();
    }

    //设置密码图片
    private void setDrawable() {
        if (isPossword)
            setCompoundDrawablesWithIntrinsicBounds(0, 0, resPasswordOffAble, 0);
        else
            setCompoundDrawablesWithIntrinsicBounds(0, 0, resPasswordOnAble, 0);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            int eventX = (int) event.getRawX();
            int eventY = (int) event.getRawY();
            Rect rect = new Rect();
            getGlobalVisibleRect(rect);
            rect.left = rect.right - ableWidth;
            if (rect.contains(eventX, eventY)) {
                if (!isPossword) {
                    // hide password, display "."
                    setTransformationMethod(PasswordTransformationMethod.getInstance());
                    isPossword = true;
                } else {
                    // display password text, for example "123456"
                    setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    isPossword = false;
                }
                setDrawable();
            }
        }
        return super.onTouchEvent(event);
    }
}
