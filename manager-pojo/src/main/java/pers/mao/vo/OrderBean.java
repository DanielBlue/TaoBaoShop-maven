package pers.mao.vo;


import pers.mao.pojo.Product;

import java.util.List;


public class OrderBean {
    private Integer id;
    //订单id
    private String oid;
    //淘宝订单号
    private String taobao_code;
    //快递号
    private String express_code;

    private String alipay_code;
    //总价
    private String total_price;
    //日期
    private String date;

    private String orderState;

    private String remark;


    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    //关联的产品
    private List<Product> productList;

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getTaobao_code() {
        return taobao_code;
    }

    public void setTaobao_code(String taobao_code) {
        this.taobao_code = taobao_code;
    }

    public String getExpress_code() {
        return express_code;
    }

    public void setExpress_code(String express_code) {
        this.express_code = express_code;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
