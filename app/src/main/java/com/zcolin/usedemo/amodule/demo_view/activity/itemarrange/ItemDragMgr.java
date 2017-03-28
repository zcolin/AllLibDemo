/*
 * *********************************************************
 *   author   colin
 *   company  fosung
 *   email    wanglin2046@126.com
 *   date     17-3-28 上午11:04
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
public class ItemDragMgr {

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
        if (SPUtil.contains("APP_ITEM_DRAG_MY")) {
            myAppsStr = SPUtil.getString("APP_ITEM_DRAG_MY", "");
        } else {
            myAppsStr = "0,1,2,3,4,5,6";
        }

        String[] myAppsArray = myAppsStr.split(",");
        List<String> listMyAppsOrder = Arrays.asList(myAppsArray);
        ArrayList<AppsItemEntity> listMyItems = new ArrayList<>();
        for (String s : listMyAppsOrder) {
            for (AppsItemEntity appsEntity : listAllItems) {
                if (appsEntity.appId.equals(s)) {
                    listMyItems.add(appsEntity);
                    break;
                }
            }
        }
        return listMyItems;
    }

    /**
     * 获取存储的 其他Item
     */
    public static ArrayList<AppsItemEntity> getOtherItems() {
        return getOtherItems(getAllItems());
    }

    /**
     * 获取存储的 其他Item
     */
    public static ArrayList<AppsItemEntity> getOtherItems(ArrayList<AppsItemEntity> listAllItems) {
        String otherAppsStr = "";
        if (SPUtil.contains("APP_ITEM_DRAG_OTHER")) {
            otherAppsStr = SPUtil.getString("APP_ITEM_DRAG_OTHER", "");
        } else {
            otherAppsStr = "7,8,9";
        }

        String[] myAppsArray = otherAppsStr.split(",");
        List<String> listMyAppsOrder = Arrays.asList(myAppsArray);
        ArrayList<AppsItemEntity> listOtherItems = new ArrayList<>();
        for (String s : listMyAppsOrder) {
            for (AppsItemEntity appsEntity : listAllItems) {
                if (appsEntity.appId.equals(s)) {
                    listOtherItems.add(appsEntity);
                    break;
                }
            }
        }
        return listOtherItems;
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

    /**
     * 保存应用
     */
    public static void saveItems(ArrayList<AppsItemEntity> listMyItems, ArrayList<AppsItemEntity> listOtherItems) {
        StringBuilder sBuilder = new StringBuilder();
        for (AppsItemEntity entity : listMyItems) {
            sBuilder.append(entity.appId)
                    .append(",");
        }

        if (sBuilder.length() > 0) {
            sBuilder.delete(sBuilder.length() - 1, sBuilder.length());
        }
        SPUtil.putString("APP_ITEM_DRAG_MY", sBuilder.toString());


        sBuilder = new StringBuilder();
        for (AppsItemEntity entity : listOtherItems) {
            sBuilder.append(entity.appId)
                    .append(",");
        }

        if (sBuilder.length() > 0) {
            sBuilder.delete(sBuilder.length() - 1, sBuilder.length());
        }
        SPUtil.putString("APP_ITEM_DRAG_OTHER", sBuilder.toString());
    }

    /**
     * Item 点击事件
     */
    public static void onItemClick(Activity activity, AppsItemEntity entity) {
        ToastUtil.toastShort(entity.appId);
    }
}
