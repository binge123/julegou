package com.best.bean;

/**
 * Created by dell2 on 2015/12/21.
 */
public class GridViewItem {
    private String image;
    private String miaoshu;
    private String money;
    private String shangdianname;
    private String address;

    public GridViewItem() {
    }

    public GridViewItem(String image, String miaoshu, String money, String shangdianname, String address) {
        this.image = image;
        this.miaoshu = miaoshu;
        this.money = money;
        this.shangdianname = shangdianname;
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMiaoshu() {
        return miaoshu;
    }

    public void setMiaoshu(String miaoshu) {
        this.miaoshu = miaoshu;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getShangdianname() {
        return shangdianname;
    }

    public void setShangdianname(String shangdianname) {
        this.shangdianname = shangdianname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
