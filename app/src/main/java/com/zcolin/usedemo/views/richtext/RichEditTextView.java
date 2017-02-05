package com.zcolin.usedemo.views.richtext;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;

/**
 * 支持包含图片的富文本显示
 */
public class RichEditTextView extends EditText implements Drawable.Callback, View.OnAttachStateChangeListener {

    private RichView richView = new RichView();

    public RichEditTextView(Context context) {
        this(context, null);
    }

    public RichEditTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RichEditTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 设置图片点击监听
     */
    public void setOnImageClickListener(RichView.OnImageClickListener onImageClickListener) {
        richView.setOnImageClickListener(onImageClickListener);
    }

    /**
     * 设置富文本
     *
     * @param text 富文本
     */
    public void setRichText(String text) {
        super.setText(richView.getRichText(text, getContext(), this));
    }

    @Override
    public void onViewAttachedToWindow(View v) {

    }

    @Override
    public void onViewDetachedFromWindow(View v) {
        richView.recycle();
    }

    @Override
    public void invalidateDrawable(@NonNull Drawable who) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            invalidateOutline();
        }
    }

    @Override
    public void scheduleDrawable(@NonNull Drawable who, @NonNull Runnable what, long when) {
    }

    @Override
    public void unscheduleDrawable(@NonNull Drawable who, @NonNull Runnable what) {
    }
}