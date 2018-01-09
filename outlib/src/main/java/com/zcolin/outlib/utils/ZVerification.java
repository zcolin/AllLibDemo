/*
 * *********************************************************
 *   author   colin
 *   company  telchina
 *   email    wanglin2046@126.com
 *   date     18-1-9 下午5:03
 * ********************************************************
 */

package com.zcolin.outlib.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.ColorInt;

import com.zcolin.frame.app.BaseApp;
import com.zcolin.frame.util.DisplayUtil;

import java.util.Random;

/**
 * 随机验证码的生成
 */
public class ZVerification {
    //随机数数组
    private static final char[] CHARS = {'2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'm', 'n', 'p', 'q', 'r', 
            's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 
            'W', 'X', 'Y', 'Z'};

    //生成bitmap宽高
    private int width;
    private int height;

    //基准padding值
    private int base_padding_left;
    private int base_padding_top;

    //随机padding值的上限
    private int range_padding_left;
    private int range_padding_top;

    //验证码随机数的个数
    private int codeLength;
    //线条的条数
    private int line_number;
    //字体大小
    private int font_size;
    //背景颜色
    private int backgroundColor;

    private String code;
    private int    padding_left;
    private int    padding_top;
    private Random random = new Random();

    public ZVerification() {
        width = DisplayUtil.dip2px(BaseApp.APP_CONTEXT, 50);
        height = DisplayUtil.dip2px(BaseApp.APP_CONTEXT, 20);

        base_padding_left = 10;
        range_padding_left = 15;
        base_padding_top = 15;
        range_padding_top = 20;

        codeLength = 4;
        line_number = 5;
        font_size = DisplayUtil.dip2px(BaseApp.APP_CONTEXT, 13);
        backgroundColor = Color.WHITE;
    }

    /**
     * 设置生成Code的长度
     */
    public ZVerification setCodeLength(int length) {
        this.codeLength = length;
        return this;
    }

    /**
     * 设置生成干扰线的个数
     */
    public ZVerification setLineNumber(int number) {
        this.line_number = number;
        return this;
    }

    /**
     * 设置基准paddingLeft值
     */
    public ZVerification setBasePaddingLeft(int padding) {
        this.base_padding_left = DisplayUtil.dip2px(BaseApp.APP_CONTEXT, padding);
        return this;
    }

    /**
     * 设置基准paddingLeft值
     */
    public ZVerification setBasePaddingTop(int padding) {
        this.base_padding_top = DisplayUtil.dip2px(BaseApp.APP_CONTEXT, padding);
        return this;
    }

    /**
     * 设置随机paddingLeft上限
     */
    public ZVerification setRangePaddingLeft(int padding) {
        this.range_padding_left = DisplayUtil.dip2px(BaseApp.APP_CONTEXT, padding);
        return this;
    }

    /**
     * 设置随机paddingLeft上限
     */
    public ZVerification setRangePaddingTop(int padding) {
        this.range_padding_top = DisplayUtil.dip2px(BaseApp.APP_CONTEXT, padding);
        return this;
    }

    /**
     * 设置验证码字体大小
     *
     * @param fontSize 单位dp
     */
    public ZVerification setFontSize(int fontSize) {
        this.font_size = DisplayUtil.dip2px(BaseApp.APP_CONTEXT, fontSize);
        return this;
    }

    /**
     * 设置bitmap长度
     *
     * @param width 单位dp
     */
    public ZVerification setWidth(int width) {
        this.width = DisplayUtil.dip2px(BaseApp.APP_CONTEXT, width);
        return this;
    }

    /**
     * 设置bitmap高度
     *
     * @param height 单位dp
     */
    public ZVerification setHeight(int height) {
        this.height = DisplayUtil.dip2px(BaseApp.APP_CONTEXT, height);
        return this;
    }

    /**
     * 设置bitmap高度
     *
     * @param color 单位dp
     */
    public ZVerification setBackgroundColor(@ColorInt int color) {
        this.backgroundColor = color;
        return this;
    }


    //验证码图片
    public Bitmap createBitmap() {
        padding_left = 0;
        code = createCode();

        Bitmap bp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bp);
        c.drawColor(backgroundColor);

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextSize(font_size);
        //画验证码
        for (int i = 0; i < code.length(); i++) {
            randomTextStyle(paint);
            randomPadding();
            c.drawText(code.charAt(i) + "", padding_left, padding_top, paint);
        }
        //画线条
        for (int i = 0; i < line_number; i++) {
            drawLine(c, paint);
        }

        c.save(Canvas.ALL_SAVE_FLAG);//保存
        c.restore();
        return bp;
    }

    public String getCode() {
        return code;
    }

    //生成验证码
    private String createCode() {
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < codeLength; i++) {
            buffer.append(CHARS[random.nextInt(CHARS.length)]);
        }
        return buffer.toString();
    }

    //画干扰线
    private void drawLine(Canvas canvas, Paint paint) {
        int color = randomColor();
        int startX = random.nextInt(width);
        int startY = random.nextInt(height);
        int stopX = random.nextInt(width);
        int stopY = random.nextInt(height);
        paint.setStrokeWidth(1);
        paint.setColor(color);
        canvas.drawLine(startX, startY, stopX, stopY, paint);
    }

    //生成随机颜色
    private int randomColor() {
        return randomColor(1);
    }

    private int randomColor(int rate) {
        int red = random.nextInt(256) / rate;
        int green = random.nextInt(256) / rate;
        int blue = random.nextInt(256) / rate;
        return Color.rgb(red, green, blue);
    }

    //随机生成文字样式，颜色，粗细，倾斜度
    private void randomTextStyle(Paint paint) {
        int color = randomColor();
        paint.setColor(color);
        paint.setFakeBoldText(random.nextBoolean());  //true为粗体，false为非粗体
        float skewX = random.nextInt(11) / 10;
        skewX = random.nextBoolean() ? skewX : -skewX;
        paint.setTextSkewX(skewX); //float类型参数，负数表示右斜，整数左斜
        //paint.setUnderlineText(true); //true为下划线，false为非下划线
        //paint.setStrikeThruText(true); //true为删除线，false为非删除线
    }

    //随机生成padding值
    private void randomPadding() {
        padding_left += base_padding_left + random.nextInt(range_padding_left);
        padding_top = base_padding_top + random.nextInt(range_padding_top);
    }
}
