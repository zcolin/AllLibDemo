/*
 * *********************************************************
 *   author   colin
 *   company  telchina
 *   email    wanglin2046@126.com
 *   date     18-1-9 下午5:03
 * ********************************************************
 */

package com.zcolin.usedemo.amodule.base;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ActivityParam {
    boolean ISIMMERSE_DEF_VALUE             = false;
    boolean ISFULLSCREEN_DEF_VALUE          = false;
    boolean ISIMMERSEPADDINGTOP_DEF_VALUE   = false;
    boolean ISSHOWTOOLBAR_DEF_VALUE         = true;
    boolean ISSECONDLEVELACTIVITY_DEF_VALUE = true;

    /**
     * 是否使用沉浸式状态栏
     */
    boolean isImmerse() default ISIMMERSE_DEF_VALUE;

    /**
     * 是否全屏
     */
    boolean isFullScreen() default ISFULLSCREEN_DEF_VALUE;

    /**
     * 如果是沉浸式状态栏，是否空出顶部距离
     */
    boolean isImmersePaddingTop() default ISIMMERSEPADDINGTOP_DEF_VALUE;

    /**
     * 是否显示toolbar
     */
    boolean isShowToolBar() default ISSHOWTOOLBAR_DEF_VALUE;

    /**
     * 是否为二级页面，默认实现了带ToolBar，带返回按钮，带返回操作
     */
    boolean isSecondLevelActivity() default ISSECONDLEVELACTIVITY_DEF_VALUE;
}