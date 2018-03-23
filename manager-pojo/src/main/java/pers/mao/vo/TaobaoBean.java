package pers.mao.vo;

import java.util.List;

public class TaobaoBean {

    /**
     * order_array : [{"freight":"0.00","product_array":[{"product_desc":"伊丽莎白圈 狗狗项圈狗头套猫咪项圈宠物狗脖套猫头套防咬狗用品","product_price":"6.90"}],"total_price":"6.90"},{"freight":"0.00","product_array":[{"product_desc":"【9.9包邮】 犬夜叉 动漫海报 八张装墙纸/壁画/墙贴/贴画","product_price":"9.90"}],"total_price":"9.90"},{"freight":"0.00","product_array":[{"product_desc":"湖南特产好照头猪脆骨500g约64包独立小包装香辣脆骨加鱼仔小吃","product_price":"29.80"},{"product_desc":"湖南特产好照头猪脆骨500g约64包独立小包装香辣脆骨加鱼仔小吃","product_price":"29.80"}],"total_price":"59.60"}]
     * total_price : 76.40
     */

    private String order_id;
    private String total_price;
    private long date;
    private List<OrderArrayBean> order_array;

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }

    public List<OrderArrayBean> getOrder_array() {
        return order_array;
    }

    public void setOrder_array(List<OrderArrayBean> order_array) {
        this.order_array = order_array;
    }

    public static class OrderArrayBean {
        /**
         * freight : 0.00
         * product_array : [{"product_desc":"伊丽莎白圈 狗狗项圈狗头套猫咪项圈宠物狗脖套猫头套防咬狗用品","product_price":"6.90"}]
         * total_price : 6.90
         */

        private String freight;
        private String total_price;
        private String alipay_code;
        private List<ProductArrayBean> product_array;

        public String getFreight() {
            return freight;
        }

        public void setFreight(String freight) {
            this.freight = freight;
        }

        public String getAlipay_code() {
            return alipay_code;
        }

        public void setAlipay_code(String alipay_code) {
            this.alipay_code = alipay_code;
        }

        public String getTotal_price() {
            return total_price;
        }

        public void setTotal_price(String total_price) {
            this.total_price = total_price;
        }

        public List<ProductArrayBean> getProduct_array() {
            return product_array;
        }

        public void setProduct_array(List<ProductArrayBean> product_array) {
            this.product_array = product_array;
        }

        public static class ProductArrayBean {
            /**
             * product_desc : 伊丽莎白圈 狗狗项圈狗头套猫咪项圈宠物狗脖套猫头套防咬狗用品
             * product_price : 6.90
             */

            private String product_desc;
            private String product_price;

            public String getProduct_desc() {
                return product_desc;
            }

            public void setProduct_desc(String product_desc) {
                this.product_desc = product_desc;
            }

            public String getProduct_price() {
                return product_price;
            }

            public void setProduct_price(String product_price) {
                this.product_price = product_price;
            }
        }
    }
}
