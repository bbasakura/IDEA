package com.itheima.realboard.domain;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * @ProjectName: gossip-parent
 * @Package: com.itheima.realboard.domain
 * @ClassName: PaymentInfo
 * @Description:
 * @Author: sakura
 * @CreateDate: 2019/7/18 0018 20:20
 * @Version: 1.0
 */
public class PaymentInfo  implements Serializable {

    private static final long serialVersionUID = -7958315778386204397L;
    private String orderId;//订单编号
    private Date createOrderTime;//订单创建时间
    private String paymentId;//支付编号
    private Date paymentTime;//支付时间
    private String productId;//商品编号
    private String productName;//商品名称
    private long productPrice;//商品价格
    private long promotionPrice;//促销价格
    private String shopId;//商铺编号
    private String shopName;//商铺名称
    private String shopMobile;//商铺电话

    /**
     * 订单支付价格
     */
    private long payPrice;
    /**
     * 订单数量
     */
    private int num;

    /**
     * <Province>19</Province>
     * <City>1657</City>
     * <County>4076</County>
     */
    private String province; //省
    private String city; //市
    private String county;//县

    //102,144,114 商品分类
    private String catagorys;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCatagorys() {
        return catagorys;
    }

    public void setCatagorys(String catagorys) {
        this.catagorys = catagorys;
    }

    /**
     * 空参构造函数
     */
    public PaymentInfo() {
    }

    /**
     * 带参数的构造方法
     * @param orderId
     * @param createOrderTime
     * @param paymentId
     * @param paymentTime
     * @param productId
     * @param productName
     * @param productPrice
     * @param promotionPrice
     * @param shopId
     * @param shopName
     * @param shopMobile
     * @param payPrice
     * @param num
     */
    public PaymentInfo(String orderId, Date createOrderTime, String paymentId, Date paymentTime, String productId, String productName, long productPrice, long promotionPrice, String shopId, String shopName, String shopMobile, long payPrice, int num) {
        this.orderId = orderId;
        this.createOrderTime = createOrderTime;
        this.paymentId = paymentId;
        this.paymentTime = paymentTime;
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.promotionPrice = promotionPrice;
        this.shopId = shopId;
        this.shopName = shopName;
        this.shopMobile = shopMobile;
        this.payPrice = payPrice;
        this.num = num;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getCreateOrderTime() {
        return createOrderTime;
    }

    public void setCreateOrderTime(Date createOrderTime) {
        this.createOrderTime = createOrderTime;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public long getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(long productPrice) {
        this.productPrice = productPrice;
    }

    public long getPromotionPrice() {
        return promotionPrice;
    }

    public void setPromotionPrice(long promotionPrice) {
        this.promotionPrice = promotionPrice;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopMobile() {
        return shopMobile;
    }

    public void setShopMobile(String shopMobile) {
        this.shopMobile = shopMobile;
    }

    public long getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(long payPrice) {
        this.payPrice = payPrice;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "PaymentInfo{" +
                "orderId='" + orderId + '\'' +
                ", createOrderTime=" + createOrderTime +
                ", paymentId='" + paymentId + '\'' +
                ", paymentTime=" + paymentTime +
                ", productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", promotionPrice=" + promotionPrice +
                ", shopId='" + shopId + '\'' +
                ", shopName='" + shopName + '\'' +
                ", shopMobile='" + shopMobile + '\'' +
                ", payPrice=" + payPrice +
                ", num=" + num +
                '}';
    }

    /**
     * 随机参数一个订单的方法
     * @return
     */
    public String random() {
        this.orderId = UUID.randomUUID().toString().replaceAll("-", "");
        this.paymentId = UUID.randomUUID().toString().replaceAll("-", "");
        this.productPrice = new Random().nextInt(1000);
        this.promotionPrice = new Random().nextInt(500);
        this.payPrice = new Random().nextInt(480);
        this.shopId = new Random().nextInt(200000)+"";
        //需要随机生成商品id和商品数量
        this.productId = new Random().nextInt(3000000)+"";
        this.num = new Random().nextInt(20);

        this.catagorys = new Random().nextInt(10000)+","+new Random().nextInt(10000)+","+new Random().nextInt(10000);
        this.province = new Random().nextInt(23)+"";
        this.city = new Random().nextInt(265)+"";
        this.county = new Random().nextInt(1489)+"";

        String date = "2015-11-11 12:22:12";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            this.createOrderTime = simpleDateFormat.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //返回订单的json数据格式
        JSONObject obj = new JSONObject();
        String jsonString = obj.toJSONString(this);
        return jsonString;
        //  return new Gson().toJson(this);
    }



}
