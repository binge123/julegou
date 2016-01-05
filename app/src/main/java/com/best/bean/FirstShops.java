package com.best.bean;

/**
 * Created by dell2 on 2016/1/4.
 */
public class FirstShops {
    private String goodsName;
    private String goodsSpec;
    private String shopPrice;
    private String goodsImg;

    public FirstShops() {
    }

    public FirstShops(String goodsName, String goodsSpec, String shopPrice, String goodsImg) {
        this.goodsName = goodsName;
        this.goodsSpec = goodsSpec;
        this.shopPrice = shopPrice;
        this.goodsImg = goodsImg;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsSpec() {
        return goodsSpec;
    }

    public void setGoodsSpec(String goodsSpec) {
        this.goodsSpec = goodsSpec;
    }

    public String getShopPrice() {
        return shopPrice;
    }

    public void setShopPrice(String shopPrice) {
        this.shopPrice = shopPrice;
    }

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }
}
