/*
 * *********************************************************
 *   author   colin
 *   company  fosung
 *   email    wanglin2046@126.com
 *   date     17-5-26 下午3:36
 * ********************************************************
 */

package com.zcolin.usedemo.amodule.mvvm.demo_view.activity.itemarrange;

import android.app.Activity;

import com.zcolin.frame.util.SPUtil;
import com.zcolin.frame.util.ToastUtil;
import com.zcolin.outlib.views.itemarrange.AppsItemEntity;
import com.zcolin.usedemo.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
public class ItemZFBMgr {

    /**
     * 获取固定的Item，此处为“全部”
     */
    public static AppsItemEntity getFixedItem() {
        AppsItemEntity entity = new AppsItemEntity();
        entity.appId = "QB";
        entity.name = "全部";
        entity.iconDrawable = R.drawable.icon_all_apps;
        return entity;
    }

    /**
     * 获取存储的 我的Item
     */
    public static ArrayList<AppsItemEntity> getMyItems() {
        return getMyItems(getAllItems());
    }

    /**
     * 获取存储的 我的Item
     */
    public static ArrayList<AppsItemEntity> getMyItems(ArrayList<AppsItemEntity> listAllItems) {
        String myAppsStr = "";
        if (SPUtil.contains("APP_ITEM_MY")) {
            myAppsStr = SPUtil.getString("APP_ITEM_MY", "");
        } else {
            myAppsStr = "1,2,3,4,5,6,7";
        }

        String[] myAppsArray = myAppsStr.split(",");
        List<String> listMyAppsOrder = Arrays.asList(myAppsArray);
        for (AppsItemEntity allItemEntity : listAllItems) {
            allItemEntity.isAdded = listMyAppsOrder.contains(allItemEntity.appId);
        }

        ArrayList<AppsItemEntity> listMyItems = new ArrayList<>();
        for (String s : listMyAppsOrder) {
            for (AppsItemEntity allItemEntity : listAllItems) {
                if (allItemEntity.appId.equals(s)) {
                    listMyItems.add(allItemEntity);
                    break;
                }
            }
        }
        return listMyItems;
    }

    /**
     * 保存我的应用
     */
    public static void saveMyItems(ArrayList<AppsItemEntity> listMyItems) {
        StringBuilder sBuilder = new StringBuilder();
        for (AppsItemEntity entity : listMyItems) {
            sBuilder.append(entity.appId)
                    .append(",");
        }

        if (sBuilder.length() > 0) {
            sBuilder.delete(sBuilder.length() - 1, sBuilder.length());
        }
        SPUtil.putString("APP_ITEM_MY", sBuilder.toString());
    }

    /**
     * Item 点击事件
     */
    public static void onItemClick(Activity activity, AppsItemEntity entity) {
        ToastUtil.toastShort("appId:" + entity.appId);
    }

    public static ArrayList<AppsItemEntity> getAllItems() {
        ArrayList<AppsItemEntity> listAllItems = new ArrayList<>();

        AppsItemEntity entity01 = new AppsItemEntity();
        entity01.appId = String.valueOf(0);
        entity01.name = "item" + 1;
        entity01.iconDrawable = R.drawable.icon_app_01;

        AppsItemEntity entity02 = new AppsItemEntity();
        entity02.appId = String.valueOf(1);
        entity02.name = "item" + 2;
        entity02.iconDrawable = R.drawable.icon_app_02;

        AppsItemEntity entity03 = new AppsItemEntity();
        entity03.appId = String.valueOf(2);
        entity03.name = "item" + 3;
        entity03.iconDrawable = R.drawable.icon_app_03;

        AppsItemEntity entity04 = new AppsItemEntity();
        entity04.appId = String.valueOf(3);
        entity04.name = "item" + 4;
        entity04.iconDrawable = R.drawable.icon_app_04;

        AppsItemEntity entity05 = new AppsItemEntity();
        entity05.appId = String.valueOf(4);
        entity05.name = "item" + 5;
        entity05.iconDrawable = R.drawable.icon_app_05;

        AppsItemEntity entity06 = new AppsItemEntity();
        entity06.appId = String.valueOf(5);
        entity06.name = "item" + 6;
        entity06.iconDrawable = R.drawable.icon_app_06;

        AppsItemEntity entity07 = new AppsItemEntity();
        entity07.appId = String.valueOf(6);
        entity07.name = "item" + 7;
        entity07.iconDrawable = R.drawable.icon_app_07;

        AppsItemEntity entity08 = new AppsItemEntity();
        entity08.appId = String.valueOf(7);
        entity08.name = "item" + 8;
        entity08.iconDrawable = R.drawable.icon_app_08;

        AppsItemEntity entity09 = new AppsItemEntity();
        entity09.appId = String.valueOf(8);
        entity09.name = "item" + 9;
        entity09.iconDrawable = R.drawable.icon_app_09;

        AppsItemEntity entity10 = new AppsItemEntity();
        entity10.appId = String.valueOf(9);
        entity10.name = "item" + 10;
        entity10.iconDrawable = R.drawable.icon_app_10;

        listAllItems.add(entity01);
        listAllItems.add(entity02);
        listAllItems.add(entity03);
        listAllItems.add(entity04);
        listAllItems.add(entity05);
        listAllItems.add(entity06);
        listAllItems.add(entity07);
        listAllItems.add(entity08);
        listAllItems.add(entity09);
        listAllItems.add(entity10);

        return listAllItems;
    }

}
