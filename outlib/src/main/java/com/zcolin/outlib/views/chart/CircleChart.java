/*
 * *********************************************************
 *   author   colin
 *   company  fosung
 *   email    wanglin2046@126.com
 *   date     17-3-27 上午9:16
 * ********************************************************
 */

package com.zcolin.outlib.views.chart;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.zcolin.frame.utils.DisplayUtil;

/**
 * 圆环进度条View
 */
public class CircleChart extends View {
    private Paint  paint;
    private RectF  oval;
    private int    roundColor;//圆环的颜色
    private float  roundWidth;// 圆环的宽度
    private int    roundProgressColor;//圆环进度的颜色
    private float  progress;
    private String progressText;
    private int    textSize;
    private int    textColor;

    public CircleChart(Context context) {
        this(context, null);
    }

    public CircleChart(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleChart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs);
        paint = new Paint();
        oval = new RectF();
        roundColor = Color.parseColor("#e7e7e7");
        roundProgressColor = Color.parseColor("#f94551");
        textColor = roundProgressColor;
        roundWidth = DisplayUtil.dip2px(getContext(), 2);
        textSize = 16;
    }

    public CircleChart setRoundColor(int roundColor) {
        this.roundColor = roundColor;
        return this;
    }

    public CircleChart setRoundWidth(float roundWidth) {
        this.roundWidth = roundWidth;
        return this;
    }

    public CircleChart setRoundProgressColor(int roundProgressColor) {
        this.roundProgressColor = roundProgressColor;
        return this;
    }

    public CircleChart setTextSize(int textSize) {
        this.textSize = textSize;
        return this;
    }

    public CircleChart setTextColor(int textColor) {
        this.textColor = textColor;
        return this;
    }

    public void setProgress(float progress) {
        this.progress = progress * 360 / 100;
        progressText = String.valueOf(progress);
        invalidate();
    }

    public void setProgressWithAnimation(float progress) {
        this.progress = progress;
        ObjectAnimator progressAnimation = ObjectAnimator.ofFloat(this, "progress", 0f, progress);
        progressAnimation.setDuration(700);// 动画执行时间  
        progressAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        progressAnimation.start();
    }

    public float getProgress() {
        return progress;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float centre = getWidth() / 2; // 获取圆心的x坐标  
        float radius = (centre - roundWidth / 2); // 圆环的半径  

        paint.setColor(roundColor); // 设置圆环的颜色  
        paint.setStyle(Paint.Style.STROKE); // 设置空心  
        paint.setStrokeWidth(roundWidth); // 设置圆环的宽度  
        paint.setAntiAlias(true); // 消除锯齿  
        paint.setStrokeCap(Paint.Cap.ROUND);// 圆角  
        canvas.drawCircle(centre, centre, radius, paint); // 画出圆环  

        // 用于定义的圆弧的形状和大小的界限.指定圆弧的外轮廓矩形区域  
        // 椭圆的上下左右四个点(View 左上角为0)  
        // 画圆弧
        paint.setColor(roundProgressColor);
        oval.set(centre - radius, centre - radius, centre + radius, centre + radius);
        canvas.drawArc(oval, -90, progress, false, paint);

        //画进度百分比的text 
        paint.setStrokeWidth(0);
        paint.setColor(textColor);
        paint.setTextSize(DisplayUtil.sp2px(getContext(), textSize));
        paint.setTypeface(Typeface.DEFAULT_BOLD); // 设置字体  
        float textWidth = paint.measureText(progressText + "%");
        canvas.drawText(progressText + "%", centre - textWidth / 2,
                centre + 14 / 2, paint); // 画出进度百分比  
    }


}  