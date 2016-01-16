package com.best.bean;

/**
 * Created by dell2 on 2016/1/14.
 */
public class PingLun {
    private String username;
    private String userimg;
    private String content;
    private String time;

    public PingLun(String username, String userimg, String content, String time) {
        this.username = username;
        this.userimg = userimg;
        this.content = content;
        this.time = time;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserimg() {
        return userimg;
    }

    public void setUserimg(String userimg) {
        this.userimg = userimg;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
