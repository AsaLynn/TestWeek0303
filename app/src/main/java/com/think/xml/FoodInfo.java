package com.think.xml;

import java.util.List;

/**
 * Created by think on 2018/1/10.
 */

public class FoodInfo {

    @Override
    public String toString() {
        return "FoodInfo{" +
                "ret=" + ret +
                ", data=" + data +
                '}';
    }

    /**
     * data : [{"collect_num":"145","food_str":"西葫芦 西红柿 盐 生抽 香醋","id":"11020","num":145,"pic":"http://www.qubaobei.com/ios/cf/uploadfile/132/12/11020.jpg","title":"番茄小炒西葫芦"},{"collect_num":"144","food_str":"去皮花生 红豆 芋头 冰糖","id":"31281","num":144,"pic":"http://www.qubaobei.com/ios/cf/uploadfile/132/32/31281.jpg","title":"花生芋头红豆糖水"},{"collect_num":"139","food_str":"文蛤 鸡蛋 盐 香油 小葱","id":"10417","num":139,"pic":"http://www.qubaobei.com/ios/cf/uploadfile/132/11/10417.jpg","title":"文蛤蒸蛋"},{"collect_num":"138","food_str":"嫩鸡 鲜茶树菇 青椒 红椒 姜","id":"2973","num":138,"pic":"http://www.qubaobei.com/ios/cf/uploadfile/132/3/2973.jpg","title":"茶树菇干锅鸡"},{"collect_num":"137","food_str":"新鲜毛豆 大个成熟番茄 半肥瘦猪肉末 姜末 盐","id":"10106","num":137,"pic":"http://www.qubaobei.com/ios/cf/uploadfile/132/11/10106.jpg","title":"肉末番茄烧毛豆"},{"collect_num":"135","food_str":"包菜 五花肉 干辣椒 花椒 大蒜","id":"11771","num":135,"pic":"http://www.qubaobei.com/ios/cf/uploadfile/132/12/11771.jpg","title":"手撕包菜小炒肉"},{"collect_num":"126","food_str":"牛腩 白萝卜 生姜 花椒 盐","id":"6910","num":126,"pic":"http://www.qubaobei.com/ios/cf/uploadfile/132/7/6910.jpg","title":"白萝卜牛腩汤"},{"collect_num":"126","food_str":"低粉 黄油 糖 鸡蛋 芝麻","id":"901","num":126,"pic":"http://www.qubaobei.com/ios/cf/uploadfile/132/1/901.jpg","title":"香脆蛋卷"},{"collect_num":"125","food_str":"嫩鸡 姜 洋葱 葱花 香菜","id":"2978","num":125,"pic":"http://www.qubaobei.com/ios/cf/uploadfile/132/3/2978.jpg","title":"电饭煲盐焗鸡"},{"collect_num":"121","food_str":"五花肉 青椒 蒜 生抽","id":"10184","num":121,"pic":"http://www.qubaobei.com/ios/cf/uploadfile/132/11/10184.jpg","title":"辣椒炒肉"},{"collect_num":"120","food_str":"韩式年糕 韩式辣酱 白菜 肉丝 红萝卜","id":"2744","num":120,"pic":"http://www.qubaobei.com/ios/cf/uploadfile/132/3/2744.jpg","title":"韩式炒年糕"},{"collect_num":"117","food_str":"甜橙 牛奶 蜂蜜","id":"23824","num":117,"pic":"http://www.qubaobei.com/ios/cf/uploadfile/132/24/23824.jpg","title":"果汁牛奶"},{"collect_num":"115","food_str":"绿豆芽 小葱 花椒 白醋 糖","id":"10372","num":115,"pic":"http://www.qubaobei.com/ios/cf/uploadfile/132/11/10372.jpg","title":"醋溜绿豆芽"},{"collect_num":"113","food_str":"糯米 面粉 肉丁 香菇 青豆","id":"2501","num":113,"pic":"http://www.qubaobei.com/ios/cf/uploadfile/132/3/2501.jpg","title":"糯米鸡"},{"collect_num":"111","food_str":"牛肉 洋葱 老姜 辣椒酱 老抽","id":"3700","num":111,"pic":"http://www.qubaobei.com/ios/cf/uploadfile/132/4/3700.jpg","title":"洋葱炒牛肉"}]
     * ret : 1
     */

    private int ret;
    private List<DataBean> data;

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        @Override
        public String toString() {
            return "DataBean{" +
                    "collect_num='" + collect_num + '\'' +
                    ", food_str='" + food_str + '\'' +
                    ", id='" + id + '\'' +
                    ", num=" + num +
                    ", pic='" + pic + '\'' +
                    ", title='" + title + '\'' +
                    '}';
        }

        /**
         * collect_num : 145
         * food_str : 西葫芦 西红柿 盐 生抽 香醋
         * id : 11020
         * num : 145
         * pic : http://www.qubaobei.com/ios/cf/uploadfile/132/12/11020.jpg
         * title : 番茄小炒西葫芦
         */

        private String collect_num;
        private String food_str;
        private String id;
        private int num;
        private String pic;
        private String title;
        private String favorites;

        public String getFavorites() {
            return favorites;
        }

        public void setFavorites(String favorites) {
            this.favorites = favorites;
        }

        public String getCollect_num() {
            return collect_num;
        }

        public void setCollect_num(String collect_num) {
            this.collect_num = collect_num;
        }

        public String getFood_str() {
            return food_str;
        }

        public void setFood_str(String food_str) {
            this.food_str = food_str;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
