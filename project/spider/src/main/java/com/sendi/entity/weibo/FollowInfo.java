package com.sendi.entity.weibo;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

import java.util.List;
import java.util.Map;

/**
 * Created by r.x on 2017/5/29.
 */
public class FollowInfo {
    private Map cardlistInfo; // todo 细节待完善
    private List cards; // todo 细节待完善
    @JsonUnwrapped
    private ResponseInfo responseInfo;

    @Override
    public String toString() {
        return "FollowInfo{" +
                "cardlistInfo=" + cardlistInfo +
                ", cards=" + cards +
                ", responseInfo=" + responseInfo +
                '}';
    }

    public Map getCardlistInfo() {
        return cardlistInfo;
    }

    public void setCardlistInfo(Map cardlistInfo) {
        this.cardlistInfo = cardlistInfo;
    }

    public List getCards() {
        return cards;
    }

    public void setCards(List cards) {
        this.cards = cards;
    }

    public ResponseInfo getResponseInfo() {
        return responseInfo;
    }

    public void setResponseInfo(ResponseInfo responseInfo) {
        this.responseInfo = responseInfo;
    }
}
