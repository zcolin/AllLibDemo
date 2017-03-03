/*
 * *********************************************************
 *   author   colin
 *   company  fosung
 *   email    wanglin2046@126.com
 *   date     17-3-3 下午3:16
 * ********************************************************
 */

/*    
 * 
 * @author		: WangLin  
 * @Company: 	：FCBN
 * @date		: 2015年5月14日 
 * @version 	: V1.0
 */
package com.zcolin.libamaplocation;

import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationClientOption.AMapLocationMode;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.model.LatLng;

/**
 * 定位工具类
 */
public class LocationUtil implements AMapLocationListener, Runnable {

    /**
     * 定位超时时间
     */
    public static final int FAILURE_DELAYTIME = 12000;

    /**
     * 获取到的定位位置
     */
    public static AMapLocation LOCATION;

    /**
     * 定位缓存时间  15秒
     */
    public static final int LOCATION_CACHE_TIME = 15000;

    /*上次定位时间，默认如果上次定位时间小于15秒，不再定位防止频繁定位*/
    private static long LAST_LOCATION_TIME;

    public AMapLocationClient       mLocationClient = null;
    public AMapLocationClientOption mLocationOption = null;
    private AMapLocation aMapLocation;                            // 用于判断定位超时
    private Handler handler = new Handler();
    private OnGetLocation onGetLocation;
    private Context       context;

    public LocationUtil(Context context) {
        this.context = context;
    }

    @Override
    public void onLocationChanged(AMapLocation location) {
        if (location != null) {
            this.aMapLocation = location;// 判断超时机制
            if (onGetLocation != null) {
                if (location.getLatitude() == 0 && location.getLongitude() == 0) {
                    onGetLocation.locationFail();
                    LOCATION = null;
                } else {
                    onGetLocation.getLocation(location);
                    LOCATION = location;
                    LAST_LOCATION_TIME = SystemClock.elapsedRealtime();
                }
            }

            //得到地址后停止定位
            stop();
        }
    }

    @Override
    public void run() {
        if (aMapLocation == null) {
            //12秒内还没有定位成功，停止定位
            stopLocation();// 销毁掉定位
            if (onGetLocation != null) {
                onGetLocation.locationFail();
            }
        }
    }

    /**
     * 开始定位
     *
     * @param onGetLocation 定位完成回调接口
     */
    public void startLocation(OnGetLocation onGetLocation) {
        this.onGetLocation = onGetLocation;
        startLocation();
    }

    /**
     * 开始定位（如果在LOCATION_CACHE_TIME之内，则获取缓存定位数据，否则进行定位）
     *
     * @param onGetLocation 定位完成回调接口
     */
    public void startOrGetCacheLocation(OnGetLocation onGetLocation) {
        this.onGetLocation = onGetLocation;
        if (LOCATION != null && LAST_LOCATION_TIME > 0 && SystemClock.elapsedRealtime() - LAST_LOCATION_TIME < LOCATION_CACHE_TIME) {
            if (onGetLocation != null) {
                onGetLocation.getLocation(LOCATION);
            }
        } else {
            startLocation();
        }
    }

    /*
     * 开始定位
     */
    private void startLocation() {
        if (mLocationOption == null) {
            mLocationOption = new AMapLocationClientOption();
            //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
            mLocationOption.setLocationMode(AMapLocationMode.Hight_Accuracy);
            //设置是否返回地址信息（默认返回地址信息）
            mLocationOption.setNeedAddress(true);
            //设置是否只定位一次,默认为false
            mLocationOption.setOnceLocation(false);
            //设置是否强制刷新WIFI，默认为强制刷新
            mLocationOption.setWifiActiveScan(true);
            //设置是否允许模拟位置,默认为false，不允许模拟位置
            mLocationOption.setMockEnable(false);
            //设置定位间隔,单位毫秒,默认为2000ms
            mLocationOption.setInterval(2000);
        }

        mLocationClient = new AMapLocationClient(context);
        mLocationClient.setLocationListener(this);
        mLocationClient.setLocationOption(mLocationOption);
        mLocationClient.startLocation();
        handler.postDelayed(this, FAILURE_DELAYTIME);// 设置超过12秒还没有定位到就停止定位
    }

    /**
     * 结束定位，连本次的回调任务也取消
     */
    public void stop() {
        handler.removeCallbacks(this);
        stopLocation();
    }

    /**
     * 结束定位
     */
    private void stopLocation() {
        if (mLocationClient != null) {
            mLocationClient.stopLocation();
            mLocationClient.onDestroy();
        }
        mLocationClient = null;
    }

    /**
     * 获取两点之间的距离
     *
     * @param lat1  点1的纬度
     * @param long1 点1 经度
     * @param lat2  点2纬度
     * @param long2 点2经度
     * @return 计算的距离
     */
    public static float getDistance(double lat1, double long1, double lat2, double long2) {
        LatLng shoplocation = new LatLng(lat1, long1);
        return AMapUtils.calculateLineDistance(shoplocation, new LatLng(lat2, long2));
    }

    /**
     * 获取格式化好的距离
     *
     * @param lat1      纬度1
     * @param long1     经度1
     * @param lat2      纬度2
     * @param long2     经度2
     * @param t         级别系数     比如希望显示1km，则传入1000
     * @param precision 精度	 再除了级别系数之后保留的精度
     */
    public static String getFormatDistance(double lat1, double long1, double lat2, double long2, int t, int precision) {
        if (t <= 0) {
            throw new IllegalArgumentException("t不能为小于等于0数！");
        }

        LatLng shoplocation = new LatLng(lat1, long1);
        float m = AMapUtils.calculateLineDistance(shoplocation, new LatLng(lat2, long2));
        return String.format("%." + precision + "f", m / t);
    }

    /**
     * 获取位置后回调
     */
    public interface OnGetLocation {

        void getLocation(AMapLocation location);

        void locationFail();
    }
}
