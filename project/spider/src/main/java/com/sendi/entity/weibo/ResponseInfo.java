package com.sendi.entity.weibo;

/**
 * Created by r.x on 2017/5/29.
 */
public class ResponseInfo {
    private Integer ok;
    private Integer seeLevel;
    private Integer showAppTips;
    private String scheme;

    @Override
    public String toString() {
        return "ResponseInfo{" +
                "ok=" + ok +
                ", seeLevel=" + seeLevel +
                ", showAppTips=" + showAppTips +
                ", scheme='" + scheme + '\'' +
                '}';
    }

    public Integer getOk() {
        return ok;
    }

    public void setOk(Integer ok) {
        this.ok = ok;
    }

    public Integer getSeeLevel() {
        return seeLevel;
    }

    public void setSeeLevel(Integer seeLevel) {
        this.seeLevel = seeLevel;
    }

    public Integer getShowAppTips() {
        return showAppTips;
    }

    public void setShowAppTips(Integer showAppTips) {
        this.showAppTips = showAppTips;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }
}
