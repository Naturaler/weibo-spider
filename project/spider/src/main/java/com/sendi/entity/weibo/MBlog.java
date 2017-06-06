package com.sendi.entity.weibo;

import java.util.Date;

/**
 * Created by r.x on 2017/6/5.
 * 微博内容
 */
public class MBlog {
    private Integer attitudes_count;
    private String bid;
    private Integer comments_count;
    private String created_at;
    private Boolean favorited;
    private String id;
    private String idstr;
    private Boolean longText;
    private Integer mblogtype;
    private String mid;
    private Long pid;
    private String raw_text;
    private Integer reposts_count;
    private RetweetedStatus retweeted_status;
    private String source;
    private String text;
    private Object user;
    private Object visible;

    @Override
    public String toString() {
        return "MBlog{" +
                "attitudes_count=" + attitudes_count +
                ", bid='" + bid + '\'' +
                ", comments_count=" + comments_count +
                ", created_at='" + created_at + '\'' +
                ", favorited=" + favorited +
                ", id='" + id + '\'' +
                ", idstr='" + idstr + '\'' +
                ", longText=" + longText +
                ", mblogtype=" + mblogtype +
                ", mid='" + mid + '\'' +
                ", pid=" + pid +
                ", raw_text='" + raw_text + '\'' +
                ", reposts_count=" + reposts_count +
                ", retweeted_status=" + retweeted_status +
                ", source='" + source + '\'' +
                ", text='" + text + '\'' +
                ", user=" + user +
                ", visible=" + visible +
                '}';
    }

    public Integer getAttitudes_count() {
        return attitudes_count;
    }

    public void setAttitudes_count(Integer attitudes_count) {
        this.attitudes_count = attitudes_count;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public Integer getComments_count() {
        return comments_count;
    }

    public void setComments_count(Integer comments_count) {
        this.comments_count = comments_count;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public Boolean getFavorited() {
        return favorited;
    }

    public void setFavorited(Boolean favorited) {
        this.favorited = favorited;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdstr() {
        return idstr;
    }

    public void setIdstr(String idstr) {
        this.idstr = idstr;
    }

    public Boolean getLongText() {
        return longText;
    }

    public void setLongText(Boolean longText) {
        this.longText = longText;
    }

    public Integer getMblogtype() {
        return mblogtype;
    }

    public void setMblogtype(Integer mblogtype) {
        this.mblogtype = mblogtype;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getRaw_text() {
        return raw_text;
    }

    public void setRaw_text(String raw_text) {
        this.raw_text = raw_text;
    }

    public Integer getReposts_count() {
        return reposts_count;
    }

    public void setReposts_count(Integer reposts_count) {
        this.reposts_count = reposts_count;
    }

    public Object getRetweeted_status() {
        return retweeted_status;
    }

    public void setRetweeted_status(RetweetedStatus retweeted_status) {
        this.retweeted_status = retweeted_status;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Object getUser() {
        return user;
    }

    public void setUser(Object user) {
        this.user = user;
    }

    public Object getVisible() {
        return visible;
    }

    public void setVisible(Object visible) {
        this.visible = visible;
    }
}
