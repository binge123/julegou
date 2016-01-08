package com.best.bean;

/**
 * Created by dell2 on 2016/1/6.
 */
public class DingDan {
    private String shopimg;
    private String shopname;
    private String shopprice;
    private String buypeople;
    private String dingdanstate;
    private String buypepphone;
    private String buypepaddress;

    public DingDan() {
    }

    public DingDan(String shopimg, String shopname, String shopprice, String buypeople, String dingdanstate, String buypepphone, String buypepaddress) {
        this.shopimg = shopimg;
        this.shopname = shopname;
        this.shopprice = shopprice;
        this.buypeople = buypeople;
        this.dingdanstate = dingdanstate;
        this.buypepphone = buypepphone;
        this.buypepaddress = buypepaddress;
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

    public String getShopprice() {
        return shopprice;
    }

    public void setShopprice(String shopprice) {
        this.shopprice = shopprice;
    }

    public String getBuypeople() {
        return buypeople;
    }

    public void setBuypeople(String buypeople) {
        this.buypeople = buypeople;
    }

    public String getDingdanstate() {
        return dingdanstate;
    }

    public void setDingdanstate(String dingdanstate) {
        this.dingdanstate = dingdanstate;
    }

    public String getBuypepphone() {
        return buypepphone;
    }

    public void setBuypepphone(String buypepphone) {
        this.buypepphone = buypepphone;
    }

    public String getBuypepaddress() {
        return buypepaddress;
    }

    public void setBuypepaddress(String buypepaddress) {
        this.buypepaddress = buypepaddress;
    }
}

