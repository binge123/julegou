package com.best.bean;

/**
 * Created by dell2 on 2016/1/8.
 */
public class GoShopingCar {
    private String shopimg;
    private String shopname;
    private String shopprice;
    private String youstate;

    public GoShopingCar() {
    }

    public GoShopingCar(String shopimg, String shopname, String shopprice, String youstate) {
        this.shopimg = shopimg;
        this.shopname = shopname;
        this.shopprice = shopprice;
        this.youstate = youstate;
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

    public String getYoustate() {
        return youstate;
    }

    public void setYoustate(String youstate) {
        this.youstate = youstate;
    }
}
