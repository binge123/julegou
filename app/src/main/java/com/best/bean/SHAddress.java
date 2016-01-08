package com.best.bean;

/**
 * Created by Administrator on 2016/1/7.
 */
public class SHAddress {
    private String userName;
    private String userPhone;
    private String areaId1;
    private String areaId2;
    private String areaId3;
    private String address;

    public SHAddress() {
    }

    public SHAddress(String userName, String userPhone, String areaId1, String areaId2, String areaId3, String address) {
        this.userName = userName;
        this.userPhone = userPhone;
        this.areaId1 = areaId1;
        this.areaId2 = areaId2;
        this.areaId3 = areaId3;
        this.address = address;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getAreaId1() {
        return areaId1;
    }

    public void setAreaId1(String areaId1) {
        this.areaId1 = areaId1;
    }

    public String getAreaId2() {
        return areaId2;
    }

    public void setAreaId2(String areaId2) {
        this.areaId2 = areaId2;
    }

    public String getAreaId3() {
        return areaId3;
    }

    public void setAreaId3(String areaId3) {
        this.areaId3 = areaId3;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
