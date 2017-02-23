package com.itheima.rookiemall.bean;

import org.itheima.recycler.bean.BasePageBean;

import java.util.List;

/**
 * Created by lyl on 2017/1/1.
 */

public class HomeHotBean2 extends BasePageBean<HomeHotBean2.ListBean>{


    /**
     * copyright : 本API接口只允许菜鸟窝(http://www.cniao5.com)用户使用,其他机构或者个人使用均为侵权行为
     * list : [{"campaignId":1,"categoryId":5,"id":1,"imgUrl":"http://7mno4h.com2.z0.glb.qiniucdn.com/s_recommend_55c1e8f7N4b99de71.jpg","name":"联想（Lenovo）拯救者14.0英寸游戏本（i7-4720HQ 4G 1T硬盘 GTX960M 2G独显 FHD IPS屏 背光键盘）黑","price":5979,"sale":8654},{"campaignId":1,"categoryId":6,"id":2,"imgUrl":"http://7mno4h.com2.z0.glb.qiniucdn.com/s_recommend_rBEhWlMFnG0IAAAAAAIqnbSuyAAAAIxLwJ57aQAAiq1705.jpg","name":"奥林巴斯（OLYMPUS）E-M10-1442-EZ 微单电电动变焦套机 银色 内置WIFI 翻转触摸屏 EM10复古高雅","price":3799,"sale":3020},{"campaignId":3,"categoryId":6,"id":3,"imgUrl":"http://7mno4h.com2.z0.glb.qiniucdn.com/s_recommend_548574edNc366ff4a.jpg","name":"Apple iPad mini 2 ME276CH/A （配备 Retina 显示屏 7.9英寸 16G WLAN 机型 深空灰色）","price":1938,"sale":9138},{"campaignId":10,"categoryId":6,"id":4,"imgUrl":"http://7mno4h.com2.z0.glb.qiniucdn.com/s_recommend_55ac5bc9N7dc9657c.jpg","name":"华为（HUAWEI）P8max 移动联通双4G 双卡双待 星光银）","price":3388,"sale":6631},{"campaignId":4,"categoryId":6,"id":5,"imgUrl":"http://7mno4h.com2.z0.glb.qiniucdn.com/s_recommend_5535f890Ndfd89dff.jpg","name":"联想（Lenovo）7英寸平板电脑 （四核1.3GHz 1G/8G 蓝牙 GPS ） A7-10 (WiFi版 8G存储 黑色) 官方标配","price":499,"sale":5742},{"campaignId":7,"categoryId":5,"id":6,"imgUrl":"http://7mno4h.com2.z0.glb.qiniucdn.com/s_recommend_55ec08c8Nd34f91bc.jpg","name":"未来人类（Terrans Force）X599 15.6英寸游戏本（E3-1231V3 8G 1TB GTX970M 6G独显 3K高清屏）黑","price":9999,"sale":8814},{"campaignId":18,"categoryId":5,"id":7,"imgUrl":"http://7mno4h.com2.z0.glb.qiniucdn.com/s_recommend_5604b257Ne3fd1a5e.jpg","name":"华硕（ASUS）飞行堡垒FX50JX 15.6英寸游戏笔记本电脑（i5-4200H 4G 7200转500G GTX950M 2G独显 全高清）","price":4799,"sale":6844},{"campaignId":9,"categoryId":5,"id":8,"imgUrl":"http://7mno4h.com2.z0.glb.qiniucdn.com/s_recommend_5456e410N65ff4160.jpg","name":"得力（deli）33123 至尊金贵系列大型办公家用保管箱 全钢防盗 震动报警 高65CM","price":698,"sale":7777},{"campaignId":4,"categoryId":5,"id":9,"imgUrl":"http://7mno4h.com2.z0.glb.qiniucdn.com/s_recommend_5411486aN1d4ddc5d.jpg","name":"AMD Athlon X4（速龙四核）860K盒装CPU （Socket FM2+/3.7GHz/4M/95W）","price":29,"sale":8355},{"campaignId":1,"categoryId":5,"id":10,"imgUrl":"http://7mno4h.com2.z0.glb.qiniucdn.com/s_recommend_54b78bf0N24c00fc2.jpg","name":"金士顿（Kingston）DTM30R 16GB USB3.0 精致炫薄金属U盘","price":42.9,"sale":8442},{"campaignId":3,"categoryId":5,"id":11,"imgUrl":"http://7mno4h.com2.z0.glb.qiniucdn.com/s_recommend_54695388N06e5b769.jpg","name":"三星（SAMSUNG）S22B310B 21.5英寸宽屏LED背光液晶显示器","price":799,"sale":7145},{"campaignId":6,"categoryId":1,"id":12,"imgUrl":"http://7mno4h.com2.z0.glb.qiniucdn.com/s_recommend_5519fdd0N02706f5e.jpg","name":"希捷（seagate）Expansion 新睿翼1TB 2.5英寸 USB3.0 移动硬盘 (STEA1000400)","price":399,"sale":402},{"campaignId":2,"categoryId":5,"id":13,"imgUrl":"http://7mno4h.com2.z0.glb.qiniucdn.com/s_recommend_55558580Nbb8545f3.jpg","name":"联想（ThinkCentre）E73S（10EN001ACD） 小机箱台式电脑 （I3-4160T 4GB 500GB 核显 Win7）23.8英寸显示器","price":3599,"sale":571},{"campaignId":4,"categoryId":5,"id":14,"imgUrl":"http://7mno4h.com2.z0.glb.qiniucdn.com/s_recommend_5563e1d4Ncc22964e.jpg","name":"新观 LED随身灯充电宝LED灯 LED护眼灯 阅读灯 下单备注颜色 无备注颜色随机发","price":1,"sale":1652},{"campaignId":2,"categoryId":5,"id":15,"imgUrl":"http://7mno4h.com2.z0.glb.qiniucdn.com/s_recommend_5583931aN31c0a2cf.jpg","name":"旅行港版转换插头插座 港版充电器转换头 电源三转二","price":1,"sale":6547},{"campaignId":13,"categoryId":5,"id":16,"imgUrl":"http://7mno4h.com2.z0.glb.qiniucdn.com/s_recommend_55c1adc2Nf655de47.jpg","name":"极米 XGIMI Z4X LED投影仪 无屏电视 微型投影仪 家用","price":2699,"sale":7778},{"campaignId":10,"categoryId":5,"id":17,"imgUrl":"http://7mno4h.com2.z0.glb.qiniucdn.com/s_recommend_55f9280eN31523bd6.jpg","name":"惠普（HP）超薄系列 HP14g-ad005TX 14英寸超薄笔记本（i5-5200U 4G 500G 2G独显 DVD刻录 蓝牙 win8.1）银色","price":3299,"sale":9248},{"campaignId":1,"categoryId":1,"id":18,"imgUrl":"http://7mno4h.com2.z0.glb.qiniucdn.com/s_recommend_5604aab9N7a91521b.jpg","name":"华硕（ASUS）经典系列X554LP 15.6英寸笔记本 （i5-5200U 4G 500G R5-M230 1G独显 蓝牙 Win8.1 黑色）","price":2999,"sale":2906},{"campaignId":2,"categoryId":5,"id":19,"imgUrl":"http://7mno4h.com2.z0.glb.qiniucdn.com/s_rmd53edbf09N01b79405.jpg","name":"海尔（Haier）云悦mini 2W(Win8.1)迷你主机(Intel四核J1900 4G 500G WIFI USB3.0 Win8.1)迷你电脑","price":1699,"sale":6786},{"campaignId":14,"categoryId":5,"id":20,"imgUrl":"http://7mno4h.com2.z0.glb.qiniucdn.com/s_rmd55619f15Nf1e9c48f.jpg","name":"橙派（CPAD）E3 1231 V3/8G/GTX960-4G/游戏电脑主机/DIY组装机","price":3479,"sale":5211}]
     * orders : []
     */

    private String copyright;
    private List<ListBean> list;
    private List<?> orders;

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public List<?> getOrders() {
        return orders;
    }

    public void setOrders(List<?> orders) {
        this.orders = orders;
    }

    @Override
    public List<ListBean> getItemDatas() {
        return list;
    }

    public static class ListBean {
        /**
         * campaignId : 1
         * categoryId : 5
         * id : 1
         * imgUrl : http://7mno4h.com2.z0.glb.qiniucdn.com/s_recommend_55c1e8f7N4b99de71.jpg
         * name : 联想（Lenovo）拯救者14.0英寸游戏本（i7-4720HQ 4G 1T硬盘 GTX960M 2G独显 FHD IPS屏 背光键盘）黑
         * price : 5979.0
         * sale : 8654
         */

        private int campaignId;
        private int categoryId;
        private int id;
        private String imgUrl;
        private String name;
        private double price;
        private int sale;

        public int getCampaignId() {
            return campaignId;
        }

        public void setCampaignId(int campaignId) {
            this.campaignId = campaignId;
        }

        public int getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(int categoryId) {
            this.categoryId = categoryId;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getSale() {
            return sale;
        }

        public void setSale(int sale) {
            this.sale = sale;
        }
    }
}
