package com.sendi.entity.weibo;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

import java.util.Map;

/**
 * Created by r.x on 2017/5/29.
 */
public class IndexInfo {
    private UserInfo userInfo;
    private String fans_scheme;
    private String follow_scheme;
    private Map tabsInfo;
    @JsonUnwrapped
    private ResponseInfo responseInfo;
//    private Integer ok;
//    private Integer seeLevel;
//    private Integer showAppTips;
//    private String scheme;

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String getFans_scheme() {
        return fans_scheme;
    }

    public void setFans_scheme(String fans_scheme) {
        this.fans_scheme = fans_scheme;
    }

    public String getFollow_scheme() {
        return follow_scheme;
    }

    public void setFollow_scheme(String follow_scheme) {
        this.follow_scheme = follow_scheme;
    }

    public Map getTabsInfo() {
        return tabsInfo;
    }

    public void setTabsInfo(Map tabsInfo) {
        this.tabsInfo = tabsInfo;
    }

    public ResponseInfo getResponseInfo() {
        return responseInfo;
    }

    public void setResponseInfo(ResponseInfo responseInfo) {
        this.responseInfo = responseInfo;
    }
}
