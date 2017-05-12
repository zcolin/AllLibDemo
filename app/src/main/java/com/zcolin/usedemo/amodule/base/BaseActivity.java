/*
 * *********************************************************
 *   author   colin
 *   company  fosung
 *   email    wanglin2046@126.com
 *   date     17-5-4 下午3:45
 * ********************************************************
 */

package com.zcolin.usedemo.amodule.base;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zcolin.frame.app.BaseFrameActivity;
import com.zcolin.frame.utils.ScreenUtil;
import com.zcolin.frame.utils.StringUtil;
import com.zcolin.usedemo.R;

/**
 * 客户端Activity的基类
 * <p>
 * 是否需要沉浸式的  {@link #isImmerse()}   default true}
 * <p>
 * 是否需要带ToolBar的 {@link #isShowToolBar()}  default true}
 * <p/>
 * 是否需要ToolBar带返回按钮并且实现了返回的 {@link #isSecondLevelAcitivty()}   default false}
 * <p/>
 * 是否需要全屏的 {@link #isFullScreen()}   default false}
 */
public abstract class BaseActivity extends BaseFrameActivity {
    private Toolbar  toolbar;
    private View     toolBarView;           //自定义的toolBar的布局
    private TextView toolbarTitleView;       //标题 居中
    private TextView toolbarLeftBtn;        //最左侧预制按钮，一般防止返回
    private TextView toolbarRightBtn;        //最右侧预制按钮
    private boolean isCallContenView = false;//防止客户端调用两次setContentView


    /**
     * 返回此Actiivty使用的布局xml文件Id
     * ex: R.layout.main
     */
    protected abstract int getRootViewLayId();

    /**
     * 初始化View操作
     */
    protected abstract void initView();

    /**
     * 是否显示toolbar
     */
    public boolean isShowToolBar() {
        return true;
    }

    /**
     * 是否全屏
     */
    public boolean isFullScreen() {
        return false;
    }

    /**
     * 如果是沉浸式状态栏，是否空出顶部距离
     */
    protected boolean isImmersePaddingTop() {
        return false;
    }

    /**
     * 是否为二级页面，默认实现了带ToolBar，带返回按钮，带返回操作
     */
    protected boolean isSecondLevelAcitivty() {
        return false;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //如果用户需要自己设置RootViewLay,此处返回0，然后自己调用setContentView即可
        if (getRootViewLayId() != 0) {
            setContentView(getRootViewLayId());
            isCallContenView = true;
        }

        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //activity异常重启是仍需要调用setContentView
        isCallContenView = false;
    }

    @Override
    public void setContentView(int layoutResID) {
        if (!isCallContenView) {
            if (isShowToolBar() && !isFullScreen()) {
                super.setContentView(initToolBar(layoutResID));
                setSupportActionBar(toolbar);
            } else {
                super.setContentView(layoutResID);
            }
            init();
        }
    }

    @Override
    public void setContentView(View view) {
        if (!isCallContenView) {
            if (isShowToolBar() && !isFullScreen()) {
                super.setContentView(initToolBar(view));
                setSupportActionBar(toolbar);
            } else {
                super.setContentView(view);
            }
            init();
        }
    }

    /**
     * 根据用户设定初始化ToolBar
     */
    private void init() {
        if (isFullScreen()) {
            if (Build.VERSION.SDK_INT >= 19) {
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            }
        } else if (isImmerse() && isImmersePaddingTop() && !isShowToolBar()) {
            ViewGroup viewGroup = (ViewGroup) ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0);
            viewGroup.setPadding(0, ScreenUtil.getStatusBarHeight(this), 0, 0);
        }

        if (isSecondLevelAcitivty() && isShowToolBar()) {
            setToolbarLeftBtnText("返回");
            setToolbarLeftBtnBackground(R.drawable.gui_btn_actionbar_back_sel);
            setToolbarLeftBtnCompoundDrawableLeft(R.drawable.gui_icon_arrow_back);
        }
    }

    private ViewGroup initToolBar(int layoutResID) {
        View userView = LayoutInflater.from(this)
                                      .inflate(layoutResID, null);
        return initToolBar(userView);
    }

    private ViewGroup initToolBar(View userView) {
                 /*获取主题中定义的悬浮标志*/
        TypedArray typedArray = getTheme().obtainStyledAttributes(R.styleable.ToolBarTheme);
        boolean overly = typedArray.getBoolean(R.styleable.ToolBarTheme_android_windowActionBarOverlay, false);
        typedArray.recycle();

        	/*将toolbar引入到父容器中*/
        View toolbarLay = LayoutInflater.from(this)
                                        .inflate(R.layout.gui_toolbar, null);
        ViewGroup.LayoutParams layParam = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        //不明原因导致布局向右移动了一些，移动回来
        //((ViewGroup.MarginLayoutParams) toolbarLay.getLayoutParams()).leftMargin = -40;
        toolbar = (Toolbar) toolbarLay.findViewById(R.id.id_tool_bar);
        if (isImmerse()) {
            int statusBarHeight = ScreenUtil.getStatusBarHeight(this);
            toolbar.setPadding(0, statusBarHeight, 0, 0);
            toolbar.getLayoutParams().height += statusBarHeight;
        }
        toolBarView = getLayoutInflater().inflate(R.layout.gui_toolbar_baseview, toolbar);
        toolbarTitleView = (TextView) toolBarView.findViewById(R.id.toolbar_title);
        toolbarLeftBtn = (TextView) toolBarView.findViewById(R.id.toolbar_btn_left);
        toolbarRightBtn = (TextView) toolBarView.findViewById(R.id.toolbar_btn_right);
        toolbarTitleView.setVisibility(View.GONE);
        toolbarLeftBtn.setVisibility(View.GONE);
        toolbarRightBtn.setVisibility(View.GONE);
        BaseClickListener clickListener = new BaseClickListener();
        toolbarLeftBtn.setOnClickListener(clickListener);
        toolbarRightBtn.setOnClickListener(clickListener);

        /*直接创建一个布局，作为视图容器的父容器*/
        ViewGroup contentView;
        if (overly) {
            contentView = new FrameLayout(this);
            contentView.addView(userView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            contentView.addView(toolbarLay, layParam);
        } else {
            contentView = new LinearLayout(this);
            ((LinearLayout) contentView).setOrientation(LinearLayout.VERTICAL);
            contentView.addView(toolbarLay, layParam);
            contentView.addView(userView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }

        return contentView;
    }

    /**
     * 设置ToolBar的标题
     *
     * @param title ：ToolBar的标题
     */
    public void setToolbarTitle(String title) {
        if (StringUtil.isNotEmpty(title)) {
            toolbarTitleView.setText(title);
            toolbarTitleView.setVisibility(View.VISIBLE);
        } else {
            toolbarTitleView.setText(null);
            toolbarTitleView.setVisibility(View.GONE);
        }
    }

    /**
     * 设置ToolBar的预置按钮长按可用
     */
    public void setLongClickEnable() {
        BaseLongClickListener longClickListener = new BaseLongClickListener();
        toolbarLeftBtn.setOnLongClickListener(longClickListener);
        toolbarRightBtn.setOnLongClickListener(longClickListener);
    }

    /**
     * 设置ToolBar的预置按钮长按不可用
     */
    public void setLongClickDisable() {
        toolbarLeftBtn.setOnLongClickListener(null);
        toolbarRightBtn.setOnLongClickListener(null);
    }

    /**
     * 设置ToolBar预制按钮一的文字
     *
     * @param extra : 显示的文字
     */
    public void setToolbarLeftBtnText(String extra) {
        if (StringUtil.isNotEmpty(extra)) {
            toolbarLeftBtn.setText(extra);
            toolbarLeftBtn.setVisibility(View.VISIBLE);
        } else {
            toolbarLeftBtn.setText(null);
            toolbarLeftBtn.setVisibility(View.GONE);
        }
    }

    public void setToolbarLeftBtnCompoundDrawableLeft(int res) {
        toolbarLeftBtn.setCompoundDrawablesWithIntrinsicBounds(res, 0, 0, 0);
        toolbarLeftBtn.setVisibility(View.VISIBLE);
    }

    public void setToolbarLeftBtnCompoundDrawableLeft(Drawable able) {
        toolbarLeftBtn.setCompoundDrawablesWithIntrinsicBounds(able, null, null, null);
        toolbarLeftBtn.setVisibility(View.VISIBLE);
    }

    /**
     * 设置ToolBar预制按钮一的图片
     *
     * @param res : 按钮显示图片资源ID
     */
    public void setToolbarLeftBtnBackground(int res) {
        toolbarLeftBtn.setBackgroundResource(res);
        toolbarLeftBtn.setVisibility(View.VISIBLE);
    }

    /**
     * 设置ToolBar预制按钮一的图片
     *
     * @param able : 按钮显示图片
     */
    @SuppressWarnings("deprecation")
    public void setToolbarLeftBtnBackground(Drawable able) {
        if (able != null) {
            toolbarLeftBtn.setBackgroundDrawable(able);
            toolbarLeftBtn.setVisibility(View.VISIBLE);
        } else {
            toolbarLeftBtn.setBackgroundDrawable(null);
            toolbarLeftBtn.setVisibility(View.GONE);
        }
    }

    /**
     * 设置ToolBar预制按钮二的文字
     *
     * @param extra : 按钮显示的文字
     */
    public void setToolbarRightBtnText(String extra) {
        if (StringUtil.isNotEmpty(extra)) {
            toolbarRightBtn.setText(extra);
            toolbarRightBtn.setVisibility(View.VISIBLE);
        } else {
            toolbarRightBtn.setText(null);
            toolbarRightBtn.setVisibility(View.GONE);
        }
    }

    /**
     * 设置ToolBar预制按钮二的图片
     *
     * @param res 显示的资源ID
     */
    public void setToolBarRightBtnBackground(int res) {
        toolbarRightBtn.setBackgroundResource(res);
        toolbarRightBtn.setVisibility(View.VISIBLE);
    }

    /**
     * 设置ToolBar预制按钮二的图片
     *
     * @param able 显示的Drawable对象
     */
    @SuppressWarnings("deprecation")
    public void setToolBarRightBtnBackground(Drawable able) {
        if (able != null) {
            toolbarRightBtn.setBackgroundDrawable(able);
            toolbarRightBtn.setVisibility(View.VISIBLE);
        } else {
            toolbarRightBtn.setBackgroundDrawable(null);
            toolbarRightBtn.setVisibility(View.GONE);
        }
    }

    /**
     * 设置ToolBar的背景颜色
     *
     * @param color 颜色值
     */
    public void setToolBarBackBroundColor(int color) {
        toolBarView.setBackgroundColor(color);
    }

    /**
     * 设置ToolBar的背景资源
     *
     * @param res 资源ID
     */
    public void setToolBarBackBroundRes(int res) {
        toolBarView.setBackgroundResource(res);
    }

    /**
     * 获取ToolBar的标题控件
     *
     * @return 标题控件
     */
    public TextView getToolBarTitleView() {
        return toolbarTitleView;
    }

    /**
     * 获取ToolBar的预制按钮一
     *
     * @return 预制按钮一控件
     */
    public TextView getToolBarExtraView() {
        return toolbarLeftBtn;
    }

    /**
     * 获取ToolBar的预制按钮二
     *
     * @return 预制按钮二控件
     */
    public TextView getToolBarExtra2View() {
        return toolbarRightBtn;
    }

    /**
     * 预制按钮一点击回调，子类如需要处理点击事件，重写此方法
     */
    protected void onToolBarLeftBtnClick() {
        if (isSecondLevelAcitivty()) {
            this.finish();
        }
    }

    /**
     * 预制按钮二点击回调，子类如需要处理点击事件，重写此方法
     */
    protected void onToolBarRightBtnClick() {
    }

    /**
     * 预制按钮一长按回调，子类如需要处理点击事件，重写此方法
     */
    protected void onToolBarLeftBtnLongClick() {
    }

    /**
     * 预制按钮二长按回调，子类如需要处理点击事件，重写此方法
     */
    protected void onToolBarRightBtnLongClick() {
    }

    /*
     * 预置按钮的点击事件类 
     */
    private class BaseClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.toolbar_btn_left) {
                onToolBarLeftBtnClick();
            } else if (v.getId() == R.id.toolbar_btn_right) {
                onToolBarRightBtnClick();
            }
        }
    }

    /*
     * 预制按钮的 长按事件类
     */
    private class BaseLongClickListener implements View.OnLongClickListener {

        @Override
        public boolean onLongClick(View v) {
            if (v.getId() == R.id.toolbar_btn_left) {
                onToolBarLeftBtnLongClick();
            } else if (v.getId() == R.id.toolbar_btn_right) {
                onToolBarRightBtnLongClick();
            }
            return true;
        }
    }
}
