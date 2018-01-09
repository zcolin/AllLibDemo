/*
 * *********************************************************
 *   author   colin
 *   company  telchina
 *   email    wanglin2046@126.com
 *   date     18-1-9 下午5:03
 * ********************************************************
 */

package com.zcolin.outlib.views.richtext;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;

/**
 * 支持包含图片的富文本显示
 */
public class RichEditTextView extends EditText implements Drawable.Callback, View.OnAttachStateChangeListener {

    private RichViewUtil richView = new RichViewUtil();

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
    public void setOnImageClickListener(OnRichImageClickListener onImageClickListener) {
        setMovementMethod(LinkMovementMethod.getInstance());
        richView.setOnImageClickListener(onImageClickListener);
    }

    /**
     * 追加富文本
     *
     * @param text 富文本
     */
    public void appendRichText(String text) {
        if (getText() != null && getText() instanceof SpannableStringBuilder) {
            setText(((SpannableStringBuilder) getText()).append(richView.getRichText(text, getContext(), this)));
        } else {
            setText(richView.getRichText(text, getContext(), this));
        }
    }

    /**
     * 设置富文本
     *
     * @param text 富文本
     */
    public void setRichText(String text) {
        super.setText(richView.getRichText(text, getContext(), this));
    }

    /**
     * 追加富文本, 异步，显示进度框
     *
     * @param text 富文本
     */
    public void appendRichTextWithBar(final String text) {
        richView.getRichTextAsync(text, getContext(), this, spanned -> {
            if (getText() != null && getText() instanceof SpannableStringBuilder) {
                setText(((SpannableStringBuilder) getText()).append(spanned));
            } else {
                setText(spanned);
            }
        });

    }

    /**
     * 设置富文本, 异步，显示进度框
     *
     * @param text 富文本
     */
    public void setRichTextWithBar(String text) {
        richView.getRichTextAsync(text, getContext(), this, this::setText);
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