/*
 * *********************************************************
 *   author   colin
 *   company  fosung
 *   email    wanglin2046@126.com
 *   date     17-5-22 下午3:28
 * ********************************************************
 */
package com.zcolin.usedemo.amodule.mvc.demo_video.ijkplayer.base;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.zcolin.usedemo.amodule.base.BaseActivity;
import com.zcolin.usedemo.amodule.mvc.demo_video.ijkplayer.adapter.SuperVideoAdapter;
import com.zcolin.usedemo.amodule.mvc.demo_video.ijkplayer.bean.VideoListBean;
import com.zplayer.library.ZListPlayer;

/**
 * 视频列表播放基类
 */
public abstract class BaseVideoRecycleViewActivity extends BaseActivity {
    protected ZListPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        player = initPlayer();
    }

    @Override
    public void setContentView(View layout) {
        super.setContentView(layout);
        player = initPlayer();
    }

    /**
     * 初始化Player操作
     */
    protected abstract ZListPlayer initPlayer();

    @Override
    protected void onResume() {
        super.onResume();
        if (player != null) {
            player.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (player != null) {
            player.onPause();
        }
    }

    @Override
    protected void onDestroy() {
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

    @Override
    public void onBackPressed() {
        if (player != null && player.onBackPressed()) {
            return;
        }
        super.onBackPressed();
    }

    protected class AdapterOnPlayClick implements SuperVideoAdapter.PlayClickListener {
        @Override
        public void onPlayClick(int position, VideoListBean data, RelativeLayout image) {
            player.onPlayClick(position, data.videoUrl, image);
        }
    }
}
