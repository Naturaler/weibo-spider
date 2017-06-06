package com.sendi.entity.sendi;

/**
 * Created by r.x on 2017/5/30.
 */

import com.sendi.entity.weibo.MBlog;

import java.util.List;
import java.util.Map;

/**
 * 爬取结果实体
 */
public class User {
    private String id; // 微博id
    private Map<String, String> info; // 用户信息
    private List<String> followers; // 粉丝列表
    private List<String> follows; // 关注列表
    private List<MBlog> weibos; // 微博内容

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", info=" + info +
                ", followers=" + followers +
                ", follows=" + follows +
                ", weibos=" + weibos +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, String> getInfo() {
        return info;
    }

    public void setInfo(Map<String, String> info) {
        this.info = info;
    }

    public List<String> getFollowers() {
        return followers;
    }

    public void setFollowers(List<String> followers) {
        this.followers = followers;
    }

    public List<String> getFollows() {
        return follows;
    }

    public void setFollows(List<String> follows) {
        this.follows = follows;
    }

    public List<MBlog> getWeibos() {
        return weibos;
    }

    public void setWeibos(List<MBlog> weibos) {
        this.weibos = weibos;
    }
}
