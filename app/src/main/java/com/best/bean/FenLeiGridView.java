package com.best.bean;

/**
 * Created by dell2 on 2016/1/10.
 */
public class FenLeiGridView {
    private String shopimg;
    private String shopname;

    public FenLeiGridView() {
    }

    public FenLeiGridView(String shopimg, String shopname) {
        this.shopimg = shopimg;
        this.shopname = shopname;
    }

    public String getShopimg() {
        return shopimg;
    }

    public void setShopimg(String shopimg) {
        this.shopimg = shopimg;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }
}
