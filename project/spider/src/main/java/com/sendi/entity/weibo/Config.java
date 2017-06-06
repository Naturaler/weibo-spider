package com.sendi.entity.weibo;

/**
 * Created by r.x on 2017/5/29.
 */
public class Config {
    private boolean login;
    private String st;
    private String uid;
    private String wm;
    private String loginUrl;
    private String wx_callback;
    private String wx_authorize;
    private String passport_login_url;

    @Override
    public String toString() {
        return "Config{" +
                "login=" + login +
                ", st='" + st + '\'' +
                ", uid='" + uid + '\'' +
                ", wm='" + wm + '\'' +
                ", loginUrl='" + loginUrl + '\'' +
                ", wx_callback='" + wx_callback + '\'' +
                ", wx_authorize='" + wx_authorize + '\'' +
                ", passport_login_url='" + passport_login_url + '\'' +
                '}';
    }

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }

    public String getSt() {
        return st;
    }

    public void setSt(String st) {
        this.st = st;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getWm() {
        return wm;
    }

    public void setWm(String wm) {
        this.wm = wm;
    }

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    public String getWx_callback() {
        return wx_callback;
    }

    public void setWx_callback(String wx_callback) {
        this.wx_callback = wx_callback;
    }

    public String getWx_authorize() {
        return wx_authorize;
    }

    public void setWx_authorize(String wx_authorize) {
        this.wx_authorize = wx_authorize;
    }

    public String getPassport_login_url() {
        return passport_login_url;
    }

    public void setPassport_login_url(String passport_login_url) {
        this.passport_login_url = passport_login_url;
    }
}
