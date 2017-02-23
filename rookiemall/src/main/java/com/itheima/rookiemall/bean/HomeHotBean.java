package com.itheima.rookiemall.bean;

import org.itheima.recycler.bean.BasePageBean;

import java.util.List;

/**
 * Created by lyl on 2016/10/3.
 */

public class HomeHotBean extends BasePageBean<HomeHotBean.ItemHomeHotBean> {
    //    @SerializedName("totalCount")
//    public int totalCount;
//    @SerializedName("currentPage")
//    public int currentPage;
//    @SerializedName("totalPage")
//    public int totalPage;
//    @SerializedName("pageSize")
//    public int pageSize;
    public List<ItemHomeHotBean> list;

    @Override
    public List<HomeHotBean.ItemHomeHotBean> getItemDatas() {
        return list;
    }


    public static class ItemHomeHotBean {
        public int id;
        public String name;
        public String imgUrl;
        public String description;
        public double price;
        public int sale;

        @Override
        public String toString() {
            return "ItemHomeHotBean{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", imgUrl='" + imgUrl + '\'' +
                    ", description='" + description + '\'' +
                    ", price=" + price +
                    ", sale=" + sale +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "HomeHotBean{" +
                "list=" + list +
                '}';
    }
}
