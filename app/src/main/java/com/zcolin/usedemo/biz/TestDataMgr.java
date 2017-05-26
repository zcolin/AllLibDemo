/*
 * *********************************************************
 *   author   colin
 *   company  fosung
 *   email    wanglin2046@126.com
 *   date     17-5-23 下午1:54
 * ********************************************************
 */

package com.zcolin.usedemo.biz;

import com.zcolin.usedemo.db.entity.Employee;
import com.zcolin.usedemo.entity.ListItemData;

import java.util.ArrayList;
import java.util.Date;

/**
 * 测试数据生成类
 */
public class TestDataMgr {

    /**
     * 制造假数据
     */
    public static Employee getTestEmployee() {
        Employee employee = new Employee();
        Date date = new Date();
        long t = date.getTime();

        employee.setId(t % 20);
        employee.setCompany("fosung");
        employee.setName("张" + t % 15);
        employee.setGroup(t % 2 == 0 ? "部门一" : "部门二");
        employee.setDate(date);
        return employee;
    }

    /**
     * 制造列表页显示的假数据
     */
    public static ArrayList<ListItemData> getTextList(int page) {
        ArrayList<ListItemData> dataList = new ArrayList<>();
        int start = 20 * (page - 1);
        for (int i = start; i < 20 * page; i++) {
            ListItemData data = new ListItemData();
            data.title = "Frist" + i;
            data.id = i;
            dataList.add(data);
        }
        return dataList;
    }

    /***
     * 获取Banner的地址假数据
     */
    public static ArrayList<String> getBannerImageUrlList() {
        String url_1 = "http://img.ycwb.com/news/attachement/jpg/site2/20110226/90fba60155890ed3082500.jpg";
        String url_2 = "http://cdn.duitang.com/uploads/item/201112/04/20111204012148_wkT88.jpg";
        String url_3 = "http://img6.faloo.com/Picture/680x580/0/449/449476.jpg";
        ArrayList<String> listUrl = new ArrayList<>();
        listUrl.add(url_1);
        listUrl.add(url_2);
        listUrl.add(url_3);
        return listUrl;
    }

    /***
     * 获取Banner的地址假数据
     */
    public static String getTextSwitcherText() {
        return "只要用过mvp这个问题可能很多人都知道。写mvp的时候，presenter会持有view，如果presenter有后台异步的长时间的动作，" +
                "比如网络请求，这时如果返回退出了Activity，后台异步的动作不会立即停止，这里就会有内存泄漏的隐患，所以会在presenter中加入" +
                "一个销毁view的方法。现在就在之前的项目中做一下修改";
    }
}

