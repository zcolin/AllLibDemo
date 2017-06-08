/*
 * *********************************************************
 *   author   colin
 *   company  fosung
 *   email    wanglin2046@126.com
 *   date     17-3-27 上午9:16
 * ********************************************************
 */

package com.zcolin.outlib.views.chart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.zcolin.frame.util.DisplayUtil;


/**
 * 仪表盘View
 */
public class PanelView extends View {
    private int width;
    private int height;

    private int arcAngel = 180;//需要仪表盘的度数
    private int   padding;//仪表盘padding距离
    private RectF arcRect;
    private RectF secArcRect;
    private Paint p           = new Paint();
    private Path  pathPointer = new Path();

    private int pointerWidth;//指针宽度
    private int progress;
    private int arcWidth;//弧的宽度
    private int arcBackgroundColor;//进度底色
    private int arcProgressColor;//进度颜色
    private int pointerCircleRadius; //指针圆的半径
    private int pointerColor;//指针圆和指针颜色
    private int bottomLineColor;//指针圆和指针颜色

    public PanelView(Context context) {
        this(context, null);
    }

    public PanelView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PanelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        pointerColor = Color.parseColor("#403f61");
        arcProgressColor = Color.parseColor("#fd454f");
        arcBackgroundColor = Color.parseColor("#01bbae");
        bottomLineColor = Color.parseColor("#ededed");
        arcWidth = DisplayUtil.dip2px(context, 15);
        padding = DisplayUtil.dip2px(context, 20);
        pointerCircleRadius = DisplayUtil.dip2px(context, 8);
        pointerWidth = DisplayUtil.dip2px(context, 10);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            width = DisplayUtil.dip2px(getContext(), DisplayUtil.dip2px(getContext(), 100));
        }


        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            height = DisplayUtil.dip2px(getContext(), DisplayUtil.dip2px(getContext(), 100));
        }

        if (arcRect == null) {
            arcRect = new RectF(padding, padding, width - padding, height - padding);
        }
        if (secArcRect == null) {
            int offSetWidth = (width - padding - padding) / 4;
            int offSetHeight = (height - padding - padding) / 4;
            secArcRect = new RectF(padding + offSetWidth, padding + offSetHeight, width - padding - offSetWidth, height - padding - offSetHeight);
        }
        setMeasuredDimension(width, height);
    }


    /**
     * 设置百分比
     */
    public PanelView setProgress(int progress) {
        this.progress = progress;
        invalidate();
        return this;
    }

    /**
     * 设置指针颜色
     */
    public PanelView setPointerColor(int color) {
        pointerColor = color;
        invalidate();
        return this;
    }

    /**
     * 设置圆弧颜色
     */
    public PanelView setArcColor(int arcProgressColor, int arcBackgroundColor) {
        this.arcProgressColor = arcProgressColor;
        this.arcBackgroundColor = arcBackgroundColor;
        invalidate();
        return this;
    }

    /**
     * 设置圆弧的宽度
     */
    public PanelView setArcWidth(int width) {
        arcWidth = width;
        invalidate();
        return this;
    }

    /**
     * 设置指针的宽度
     */
    public PanelView setPointerWidth(int width) {
        pointerWidth = width;
        invalidate();
        return this;
    }

    /**
     * 设置指针圆的半径
     */
    public PanelView setPointerCircleRadius(int radius) {
        pointerCircleRadius = radius;
        invalidate();
        return this;
    }

    /**
     * 设置指针圆的半径
     */
    public PanelView setBottomLineColor(int bottomLineColor) {
        this.bottomLineColor = bottomLineColor;
        invalidate();
        return this;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (arcRect == null || secArcRect == null) {
            return;
        }

        p.setAntiAlias(true);
        p.setStyle(Paint.Style.STROKE);

        float percent = progress / 100f;
        int startAngle = -90 - (arcAngel / 2); //保证仪表盘左右平衡
        float fill = arcAngel * percent;   //充满的圆弧的度数  

        //画粗弧底色
        p.setStrokeWidth(arcWidth);
        p.setColor(arcBackgroundColor);
        canvas.drawArc(arcRect, startAngle, arcAngel, false, p);

        //画粗弧覆盖色
        p.setColor(arcProgressColor);
        canvas.drawArc(arcRect, startAngle, fill, false, p);

        //画第二个圆弧
        p.setColor(bottomLineColor);
        p.setStrokeWidth(3);
        canvas.drawArc(secArcRect, startAngle, arcAngel, false, p);

        //画底部直线
        p.setColor(bottomLineColor);
        p.setStrokeWidth(3);
        canvas.drawLine(0, height / 2, width, height / 2, p);

        //绘制小圆
        p.setColor(pointerColor);
        p.setStrokeWidth(5);
        canvas.drawCircle(width / 2, height / 2, pointerCircleRadius, p);

        //绘制指针
        p.setColor(pointerColor);
        p.setStrokeWidth(4);

        //按照百分比绘制指针
        int offSet = (width - padding - padding) / 8;
        float secondRectHeight = height - padding - padding;
        //先旋转画布，使指针可以竖直画
        canvas.rotate((arcAngel * percent - arcAngel / 2), width / 2, height / 2);
        //使用path画三角形
        p.setStyle(Paint.Style.FILL);
        pathPointer.moveTo(width / 2, (height / 2 - secondRectHeight / 2) + arcWidth / 2 + offSet);// 此点为起点  
        pathPointer.lineTo(width / 2 - pointerWidth / 2, height / 2 - pointerCircleRadius);
        pathPointer.lineTo(width / 2 + pointerWidth / 2, height / 2 - pointerCircleRadius);
        pathPointer.close(); // 使这些点构成封闭的多边形  
        canvas.drawPath(pathPointer, p);
        p.reset();
        //画布还原
        canvas.rotate(-(arcAngel * percent - arcAngel / 2), width / 2, height / 2);

        super.onDraw(canvas);
    }
}