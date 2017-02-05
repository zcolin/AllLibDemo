/*
 * *********************************************************
 *   author   colin
 *   company  fosung
 *   email    wanglin2046@126.com
 *   date     16-11-15 下午1:53
 * ********************************************************
 */

package com.zcolin.usedemo.amodule.demo_video.ijkplayer;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zcolin.frame.app.BaseFrameFrag;
import com.zcolin.usedemo.R;
import com.zcolin.usedemo.amodule.base.BaseSecondLevelActivity;
import com.zcolin.usedemo.amodule.demo_video.ijkplayer.base.BaseVideoPlayFragment;
import com.zcolin.usedemo.amodule.demo_video.ijkplayer.base.BaseVideoRecycleViewFragment;
import com.zcolin.usedemo.amodule.demo_video.ijkplayer.fragment.VideoPlayFragment;
import com.zcolin.usedemo.amodule.demo_video.ijkplayer.fragment.VideoRecyclerViewFragment;

/**
 * 视频在Fragment中的播放
 */
public class VideoFragmentPlayActivity extends BaseSecondLevelActivity {

    private BaseFrameFrag fragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ijk_fragmentplay);

        String strData = getIntent().getStringExtra("data");
        if ("single".equals(strData)) {
            setToolbarTitle("Fragment单视频播放");
            fragment = VideoPlayFragment.newInstance(false, "http://baobab.wandoujia.com/api/v1/playUrl?vid=2614&editionType=normal");
            getSupportFragmentManager().beginTransaction()
                                       .replace(R.id.content, fragment)
                                       .commitAllowingStateLoss();
        } else {
            setToolbarTitle("Fragment列表视频播放");
            fragment = VideoRecyclerViewFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                                       .replace(R.id.content, fragment)
                                       .commitAllowingStateLoss();
        }
    }

    @Override
    public void onBackPressed() {
        if (fragment != null) {
            if (fragment instanceof BaseVideoPlayFragment) {
                if (((BaseVideoPlayFragment) fragment).onBackPressed()) {
                    return;
                }
            } else if (fragment instanceof BaseVideoRecycleViewFragment) {
                if (((BaseVideoRecycleViewFragment) fragment).onBackPressed()) {
                    return;
                }
            }
        }
        super.onBackPressed();
    }
}
