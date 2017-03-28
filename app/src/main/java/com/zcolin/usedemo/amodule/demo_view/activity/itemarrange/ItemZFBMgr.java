/*
 * *********************************************************
 *   author   colin
 *   company  fosung
 *   email    wanglin2046@126.com
 *   date     17-3-28 上午10:56
 * ********************************************************
 */

package com.zcolin.usedemo.amodule.demo_view.activity.itemarrange;

import android.app.Activity;

import com.zcolin.frame.utils.SPUtil;
import com.zcolin.frame.utils.ToastUtil;
import com.zcolin.outlib.views.itemarrange.AppsItemEntity;

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
        entity.iconDrawable = com.zcolin.usedemo.R.drawable.ic_launcher;
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
        ToastUtil.toastShort(entity.appId);
    }

    public static ArrayList<AppsItemEntity> getAllItems() {
        ArrayList<AppsItemEntity> listAllItems = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            AppsItemEntity entity = new AppsItemEntity();
            entity.appId = String.valueOf(i);
            entity.name = "item" + i;
            entity.iconDrawable = com.zcolin.usedemo.R.drawable.ic_launcher;
            listAllItems.add(entity);
        }
        return listAllItems;
    }
}
