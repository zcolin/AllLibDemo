/*
 * *********************************************************
 *   author   colin
 *   company  fosung
 *   email    wanglin2046@126.com
 *   date     17-5-26 下午2:44
 * ********************************************************
 */

package com.zcolin.usedemo.amodule.mvvm.bindingadapter;

import android.databinding.BindingAdapter;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.zcolin.frame.app.BaseApp;
import com.zcolin.frame.imageloader.CircleTransform;
import com.zcolin.frame.imageloader.RoundedCornersTransformation;

/**
 * ImageLoader的BindingAdapter
 */
public final class ImageBindingAdapter {
    @BindingAdapter(value = {"uri", "placeholder", "corner", "thumb_multiper", "is_circle", "is_gif"}, requireAll = false)
    public static void loadImage(ImageView imageView, String uri, @DrawableRes int placeholder, int corner, int thumbMultiper, boolean isCircle, boolean isGif) {
        DrawableTypeRequest<String> drawableTypeRequest = Glide.with(imageView.getContext())
                                                               .load(uri);
        if (placeholder != 0) {
            drawableTypeRequest.placeholder(placeholder);
        }
        
        if (isGif) {
            drawableTypeRequest.asGif();
        }

        if (isCircle) {
            drawableTypeRequest.asBitmap()
                               .transform(new CircleTransform(BaseApp.APP_CONTEXT));
        } else if (corner > 0) {
            drawableTypeRequest.asBitmap()
                               .transform(new RoundedCornersTransformation(BaseApp.APP_CONTEXT, corner));
        }

        if (thumbMultiper > 0) {
            drawableTypeRequest.thumbnail(thumbMultiper);
        }

        drawableTypeRequest.into(imageView);
    }

}