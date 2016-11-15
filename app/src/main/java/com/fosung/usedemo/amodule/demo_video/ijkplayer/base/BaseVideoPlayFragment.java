/*
 * *********************************************************
 *   author   colin
 *   company  fosung
 *   email    wanglin2046@126.com
 *   date     16-11-15 下午1:56
 * ********************************************************
 */
package com.fosung.usedemo.amodule.demo_video.ijkplayer.base;

import android.content.res.Configuration;

import com.fosung.frame.app.BaseFrameFrag;
import com.superplayer.library.SuperPlayer;

/**
 * 视频播放页面基类
 */
public abstract class BaseVideoPlayFragment extends BaseFrameFrag {

    protected SuperPlayer player;

    @Override
    protected void createView() {
        player = initPlayer();
    }

    /**
     * 初始化Player操作
     */
    protected abstract SuperPlayer initPlayer();

    @Override
    public void onResume() {
        super.onResume();
        if (player != null) {
            player.onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (player != null) {
            player.onPause();
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (player != null) {
            player.onDestroy();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (player != null) {
            player.onConfigurationChanged(newConfig);
        }
    }

    public boolean onBackPressed() {
        return player != null && player.onBackPressed();
    }
}
