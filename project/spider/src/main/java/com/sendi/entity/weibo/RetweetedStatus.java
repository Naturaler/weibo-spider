package com.sendi.entity.weibo;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

/**
 * Created by r.x on 2017/6/5.
 */
public class RetweetedStatus {
    private String created_at;
    private String id;
    private String mid;
    private String idstr;
    private String text;
    private Integer textLength;
    private String source;
    private Boolean favorited;
    private Object user;
    private Integer reposts_count;
    private Integer comments_count;
    private Integer attitudes_count;
    private Boolean longText;
    private Object visible;
    private Object page_info;
    private String bid;

    @Override
    public String toString() {
        return "RetweetedStatus{" +
                "created_at='" + created_at + '\'' +
                ", id='" + id + '\'' +
                ", mid='" + mid + '\'' +
                ", idstr='" + idstr + '\'' +
                ", text='" + text + '\'' +
                ", textLength=" + textLength +
                ", source='" + source + '\'' +
                ", favorited=" + favorited +
                ", user=" + user +
                ", reposts_count=" + reposts_count +
                ", comments_count=" + comments_count +
                ", attitudes_count=" + attitudes_count +
                ", longText=" + longText +
                ", visible=" + visible +
                ", page_info=" + page_info +
                ", bid='" + bid + '\'' +
                '}';
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getIdstr() {
        return idstr;
    }

    public void setIdstr(String idstr) {
        this.idstr = idstr;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getTextLength() {
        return textLength;
    }

    public void setTextLength(Integer textLength) {
        this.textLength = textLength;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Boolean getFavorited() {
        return favorited;
    }

    public void setFavorited(Boolean favorited) {
        this.favorited = favorited;
    }

    public Object getUser() {
        return user;
    }

    public void setUser(Object user) {
        this.user = user;
    }

    public Integer getReposts_count() {
        return reposts_count;
    }

    public void setReposts_count(Integer reposts_count) {
        this.reposts_count = reposts_count;
    }

    public Integer getComments_count() {
        return comments_count;
    }

    public void setComments_count(Integer comments_count) {
        this.comments_count = comments_count;
    }

    public Integer getAttitudes_count() {
        return attitudes_count;
    }

    public void setAttitudes_count(Integer attitudes_count) {
        this.attitudes_count = attitudes_count;
    }

    public Boolean getLongText() {
        return longText;
    }

    public void setLongText(Boolean longText) {
        this.longText = longText;
    }

    public Object getVisible() {
        return visible;
    }

    public void setVisible(Object visible) {
        this.visible = visible;
    }

    public Object getPage_info() {
        return page_info;
    }

    public void setPage_info(Object page_info) {
        this.page_info = page_info;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }
}
