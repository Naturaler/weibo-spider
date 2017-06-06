package com.sendi.entity.weibo;

/**
 * Created by r.x on 2017/5/29.
 */

import java.util.List;

/**
 * 移动端微博用户信息
 */
public class UserInfo {
    private String id; // 微博id
    private String screen_name; // 昵称
    private String profile_image_url; //
    private String profile_url; // 主页
    private Integer statuses_count; //
    private Boolean verified; //
    private Integer verified_type; //
    private Integer verified_type_ext; //
    private String verified_reason; //
    private String description; //
    private String gender; // 性别
    private Integer mbtype; //
    private Integer urank; //
    private Integer mbrank; //
    private Boolean follow_me; //
    private Boolean following; //
    private Integer followers_count; // 粉丝数量
    private Integer follow_count; // 关注数
    private String cover_image_phone; //
    private List toolbar_menus; //
    private String fans_scheme; //
    private String follow_scheme; //

    @Override
    public String toString() {
        return "UserInfo{" +
                "id='" + id + '\'' +
                ", screen_name='" + screen_name + '\'' +
                ", profile_image_url='" + profile_image_url + '\'' +
                ", profile_url='" + profile_url + '\'' +
                ", statuses_count=" + statuses_count +
                ", verified=" + verified +
                ", verified_type=" + verified_type +
                ", verified_type_ext=" + verified_type_ext +
                ", verified_reason='" + verified_reason + '\'' +
                ", description='" + description + '\'' +
                ", gender='" + gender + '\'' +
                ", mbtype=" + mbtype +
                ", urank=" + urank +
                ", mbrank=" + mbrank +
                ", follow_me=" + follow_me +
                ", following=" + following +
                ", followers_count=" + followers_count +
                ", follow_count=" + follow_count +
                ", cover_image_phone='" + cover_image_phone + '\'' +
                ", toolbar_menus=" + toolbar_menus +
                ", fans_scheme='" + fans_scheme + '\'' +
                ", follow_scheme='" + follow_scheme + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getScreen_name() {
        return screen_name;
    }

    public void setScreen_name(String screen_name) {
        this.screen_name = screen_name;
    }

    public Integer getFollowers_count() {
        return followers_count;
    }

    public void setFollowers_count(Integer followers_count) {
        this.followers_count = followers_count;
    }

    public Integer getFollow_count() {
        return follow_count;
    }

    public void setFollow_count(Integer follow_count) {
        this.follow_count = follow_count;
    }

    public String getProfile_url() {
        return profile_url;
    }

    public void setProfile_url(String profile_url) {
        this.profile_url = profile_url;
    }

    public String getProfile_image_url() {
        return profile_image_url;
    }

    public void setProfile_image_url(String profile_image_url) {
        this.profile_image_url = profile_image_url;
    }

    public Integer getStatuses_count() {
        return statuses_count;
    }

    public void setStatuses_count(Integer statuses_count) {
        this.statuses_count = statuses_count;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public Integer getVerified_type() {
        return verified_type;
    }

    public void setVerified_type(Integer verified_type) {
        this.verified_type = verified_type;
    }

    public Integer getVerified_type_ext() {
        return verified_type_ext;
    }

    public void setVerified_type_ext(Integer verified_type_ext) {
        this.verified_type_ext = verified_type_ext;
    }

    public String getVerified_reason() {
        return verified_reason;
    }

    public void setVerified_reason(String verified_reason) {
        this.verified_reason = verified_reason;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getMbtype() {
        return mbtype;
    }

    public void setMbtype(Integer mbtype) {
        this.mbtype = mbtype;
    }

    public Integer getUrank() {
        return urank;
    }

    public void setUrank(Integer urank) {
        this.urank = urank;
    }

    public Integer getMbrank() {
        return mbrank;
    }

    public void setMbrank(Integer mbrank) {
        this.mbrank = mbrank;
    }

    public Boolean getFollow_me() {
        return follow_me;
    }

    public void setFollow_me(Boolean follow_me) {
        this.follow_me = follow_me;
    }

    public Boolean getFollowing() {
        return following;
    }

    public void setFollowing(Boolean following) {
        this.following = following;
    }

    public String getCover_image_phone() {
        return cover_image_phone;
    }

    public void setCover_image_phone(String cover_image_phone) {
        this.cover_image_phone = cover_image_phone;
    }

    public List getToolbar_menus() {
        return toolbar_menus;
    }

    public void setToolbar_menus(List toolbar_menus) {
        this.toolbar_menus = toolbar_menus;
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
}
