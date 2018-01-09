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
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.TextUtils;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.zcolin.frame.util.DisplayUtil;
import com.zcolin.frame.util.ScreenUtil;
import com.zcolin.outlib.R;

import java.util.HashSet;

public class GlideImageGeter implements Html.ImageGetter {
    private       HashSet<GifDrawable> gifDrawables;
    private final TextView             mTextView;
    private final Context              mContext;

    public void recycle() {
        for (GifDrawable gifDrawable : gifDrawables) {
            gifDrawable.setCallback(null);
            gifDrawable.recycle();
        }
        gifDrawables.clear();
    }

    public GlideImageGeter(Context context, TextView textView) {
        this.mContext = context;
        this.mTextView = textView;
        gifDrawables = new HashSet<>();
        mTextView.setTag(R.id.img_tag, this);
    }

    @Override
    public Drawable getDrawable(String url) {
        if (TextUtils.isEmpty(url)) {
            return null;
        }

        if (isGif(url)) {
            GifTarget target = new GifTarget();
            Glide.with(mContext).load(url).asGif().into(target);
            return target.getUrlDrawable();
        } else {
            BitmapTarget target = new BitmapTarget();
            Glide.with(mContext).load(url).asBitmap().into(target);
            return target.getUrlDrawable();
        }
    }

    private static boolean isGif(String path) {
        int index = path.lastIndexOf('.');
        return index > 0 && "gif".toUpperCase().equals(path.substring(index + 1).toUpperCase());
    }

    private class GifTarget extends SimpleTarget<GifDrawable> {
        private final UrlDrawable urlDrawable = new UrlDrawable();
        private       int         padding     = DisplayUtil.dip2px(mContext, 0);

        @Override
        public void onResourceReady(GifDrawable resource, GlideAnimation<? super GifDrawable> glideAnimation) {
            int w = ScreenUtil.getScreenWidth(mContext);
            int hh = resource.getIntrinsicHeight();
            int ww = resource.getIntrinsicWidth();
            int high = hh * (w - padding * 2) / ww;
            Rect rect = new Rect(padding, padding, w - padding, high);
            resource.setBounds(rect);
            urlDrawable.setBounds(rect);
            urlDrawable.setDrawable(resource);
            gifDrawables.add(resource);
            resource.setCallback(mTextView);
            resource.start();
            resource.setLoopCount(GlideDrawable.LOOP_FOREVER);
            mTextView.setText(mTextView.getText());
            mTextView.invalidate();
        }

        public UrlDrawable getUrlDrawable() {
            return urlDrawable;
        }
    }

    private class BitmapTarget extends SimpleTarget<Bitmap> {
        private final UrlDrawable urlDrawable = new UrlDrawable();
        private       int         padding     = DisplayUtil.dip2px(mContext, 0);

        @Override
        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
            Drawable drawable = new BitmapDrawable(mContext.getResources(), resource);
            int w = ScreenUtil.getScreenWidth(mContext);
            int hh = drawable.getIntrinsicHeight();
            int ww = drawable.getIntrinsicWidth();
            int high = hh * (w - padding * 2) / ww;
            Rect rect = new Rect(padding, padding, w - padding, high);
            drawable.setBounds(rect);
            urlDrawable.setBounds(rect);
            urlDrawable.setDrawable(drawable);
            mTextView.setText(mTextView.getText());
            mTextView.invalidate();
        }

        public UrlDrawable getUrlDrawable() {
            return urlDrawable;
        }
    }
}