/*
 * *********************************************************
 *   author   colin
 *   company  telchina
 *   email    wanglin2046@126.com
 *   date     18-1-9 下午5:02
 * ********************************************************
 */
package com.zcolin.usedemo.amodule.mvc.demo_video.ijkplayer.base;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.RelativeLayout;

import com.zcolin.frame.app.BaseFrameFrag;
import com.zcolin.usedemo.amodule.mvc.demo_video.ijkplayer.adapter.SuperVideoAdapter;
import com.zcolin.usedemo.amodule.mvc.demo_video.ijkplayer.bean.VideoListBean;
import com.zplayer.library.ZListPlayer;

/**
 * 视频列表播放基类
 */
public abstract class BaseVideoRecycleViewFragment extends BaseFrameFrag {
    protected ZListPlayer player;


    @Override
    protected void createView(@Nullable Bundle savedInstanceState) {
        player = initPlayer();
    }

    /**
     * 初始化Player操作
     */
    protected abstract ZListPlayer initPlayer();

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

    protected class AdapterOnPlayClick implements SuperVideoAdapter.PlayClickListener {
        @Override
        public void onPlayClick(int position, VideoListBean data, RelativeLayout image) {
            player.onPlayClick(position, data.videoUrl, image);
        }
    }
}
