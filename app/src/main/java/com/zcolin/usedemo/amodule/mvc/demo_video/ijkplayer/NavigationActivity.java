/*
 * *********************************************************
 *   author   colin
 *   company  telchina
 *   email    wanglin2046@126.com
 *   date     18-1-9 下午5:02
 * ********************************************************
 */

package com.zcolin.usedemo.amodule.mvc.demo_video.ijkplayer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.zcolin.usedemo.R;
import com.zcolin.usedemo.amodule.base.BaseActivity;

/**
 * 导航路由
 */
public class NavigationActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ijk_navigation);
        setToolbarTitle("视频播放导航");
        initView();
    }

    protected void initView() {
        findViewById(R.id.tv_live_play).setOnClickListener(this);//直播
        findViewById(R.id.tv_demand_activity_play).setOnClickListener(this);//Activity点播
        findViewById(R.id.tv_recycleview_activity_player).setOnClickListener(this);//Activity列表播放
        findViewById(R.id.tv_demand_fragment_play).setOnClickListener(this);//Fragment点播
        findViewById(R.id.tv_recycleview_fragment_player).setOnClickListener(this);//Fragment列表播放
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tv_live_play) {
            Intent liveIntent = new Intent(this, VideoPlayActivity.class);
            liveIntent.putExtra("isLive", true);
            liveIntent.putExtra("url", "rtmp://live.hkstv.hk.lxdns.com/live/hks");
            startActivity(liveIntent);
        } else if (view.getId() == R.id.tv_demand_activity_play) {
            Intent demandIntent = new Intent(this, VideoPlayActivity.class);
            demandIntent.putExtra("isLive", false);
            demandIntent.putExtra("url", "http://baobab.wandoujia.com/api/v1/playUrl?vid=2614&editionType=normal");
            startActivity(demandIntent);
        } else if (view.getId() == R.id.tv_recycleview_activity_player) {
            Intent listViewIntent = new Intent(this, VideoRecyclerViewActivity.class);
            startActivity(listViewIntent);
        } else if (view.getId() == R.id.tv_demand_fragment_play) {
            Intent demandIntent = new Intent(this, VideoFragmentPlayActivity.class);
            demandIntent.putExtra("data", "single");
            startActivity(demandIntent);
        } else if (view.getId() == R.id.tv_recycleview_fragment_player) {
            Intent listViewIntent = new Intent(this, VideoFragmentPlayActivity.class);
            listViewIntent.putExtra("data", "list");
            startActivity(listViewIntent);
        }
    }
}
