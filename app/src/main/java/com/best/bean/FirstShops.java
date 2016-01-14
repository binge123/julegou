package com.best.bean;

/**
 * Created by dell2 on 2016/1/4.
 */
public class FirstShops {
    private String goodsName;
    private String marketPrice;
    private String shopPrice;
    private String goodsImg;
    private String shopid;
    private String goodsid;
    private String goodsStock;

    public FirstShops() {
    }

    public FirstShops(String goodsName, String marketPrice, String shopPrice, String goodsImg,String shopid,String goodsid,String goodsStock) {
        this.goodsName = goodsName;
        this.marketPrice = marketPrice;
        this.shopPrice = shopPrice;
        this.goodsImg = goodsImg;
        this.shopid = shopid;
        this.goodsid = goodsid;
        this.goodsStock = goodsStock;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
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

    public String getMarketprice() {
        return marketPrice;
    }

    public void setMarketprice(String marketprice) {
        this.marketPrice = marketprice;
    }

    public String getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(String goodsid) {
        this.goodsid = goodsid;
    }

    public String getShopid() {
        return shopid;
    }

    public void setShopid(String shopid) {
        this.shopid = shopid;
    }

    public String getGoodsStock() {
        return goodsStock;
    }

    public void setGoodsStock(String goodsStock) {
        this.goodsStock = goodsStock;
    }
}
