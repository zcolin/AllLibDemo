/*
 * *********************************************************
 *   author   colin
 *   company  fosung
 *   email    wanglin2046@126.com
 *   date     17-5-22 下午3:28
 * ********************************************************
 */

package com.zcolin.usedemo.amodule.mvc.demo_video.ijkplayer.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zcolin.frame.utils.ToastUtil;
import com.zcolin.usedemo.R;
import com.zcolin.usedemo.amodule.mvc.demo_video.ijkplayer.base.BaseVideoPlayFragment;
import com.zplayer.library.ZPlayer;

/**
 * 类描述：视频详情页
 * <p/>
 * Super南仔
 * <p/>
 * update by colin on 2016-10-18
 */
public class VideoPlayFragment extends BaseVideoPlayFragment implements ZPlayer.OnNetChangeListener {

    /**
     * 测试地址
     */
    private String  url;
    private boolean isLive;

    public static VideoPlayFragment newInstance(boolean isLive, String url) {
        Bundle args = new Bundle();
        args.putString("url", url);
        args.putBoolean("is_live", isLive);
        VideoPlayFragment fragment = new VideoPlayFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void createView(@Nullable Bundle savedInstanceState) {
        super.createView(savedInstanceState);
        url = getArguments().getString("url");
        isLive = getArguments().getBoolean("is_live", false);

        player.setTitle(url)//设置视频的titleName
              .play(url);//开始播放视频
    }

    @Override
    protected int getRootViewLayId() {
        return R.layout.fragment_ijk_play;
    }

    @Override
    protected ZPlayer initPlayer() {
        ZPlayer player = getView(R.id.view_super_player);
        player.setLive(isLive)//设置该地址是直播的地址
              .setNetChangeListener(true)//设置监听手机网络的变化,这个参数是内部是否处理网络监听，和setOnNetChangeListener没有关系
              .setOnNetChangeListener(this)//实现网络变化的回调
              .setScaleType(ZPlayer.SCALETYPE_FITXY)
              .setPlayerWH(0, player.getMeasuredHeight())//设置竖屏的时候屏幕的高度，如果不设置会切换后按照16:9的高度重置
              .setFullScreenOnly(true)  //只全屏播放
              .setAlwaysShowControl()
              .onPrepared(new ZPlayer.OnPreparedListener() {
                  @Override
                  public void onPrepared() {
                      //TODO 监听视频是否已经准备完成开始播放。（可以在这里处理视频封面的显示跟隐藏）
                  }
              })
              .onComplete(new Runnable() {
                  @Override
                  public void run() {
                      //TODO 监听视频是否已经播放完成了。（可以在这里处理视频播放完成进行的操作）
                  }
              })
              .onInfo(new ZPlayer.OnInfoListener() {
                  @Override
                  public void onInfo(int what, int extra) {
                      //TODO 监听视频的相关信息。
                  }
              })
              .onError(new ZPlayer.OnErrorListener() {
                  @Override
                  public void onError(int what, int extra) {
                      //TODO 监听视频播放失败的回调
                  }
              });
        return player;
    }


    /**
     * 网络链接监听类
     */
    @Override
    public void onWifi() {
        ToastUtil.toastShort("当前网络环境是WIFI");
    }

    @Override
    public void onMobile() {
        ToastUtil.toastShort("当前网络环境是手机网络");
    }

    @Override
    public void onDisConnect() {
        ToastUtil.toastShort("网络链接断开");
    }

    @Override
    public void onNoAvailable() {
        ToastUtil.toastShort("无网络链接");
    }
}
