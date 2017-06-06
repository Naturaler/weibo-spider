package com.xin.old;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sendi.entity.sendi.User;
import com.sendi.entity.weibo.FollowInfo;
import com.sendi.entity.weibo.IndexInfo;
import com.sendi.entity.weibo.UserInfo;
import okhttp3.*;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by r.x on 2017/5/28.
 */
public class Main {
    /*public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static final String WEIBO_ID = "2246744534";
//    private static UserInfo userInfo;
    private static IndexInfo indexInfo;


    private final int PAGE_SIZE = 15;
    private int count = 0;
    private static final String TOR_IP = "127.0.0.1";
    private static final String TOR_PORT = "9150";

    public static void main(String[] args) {
//        System.setProperty("java.net.preferIPv4Stack" , "true");
//        System.setProperty("socksProxyHost", TOR_IP);
//        System.setProperty("socksProxyPort", TOR_PORT);
//        Util.getProxy();
        Main main = new Main();
//        main.getOne("2955190997");
        main.getAll();
//        TorClient
    }

    // 单独测试
    private void getOne(String id) {
        User user = new User();
        user.setId(id);
        getFollowCount(user, id);
        FileUtil.save(user);
    }

    public void getAll() {
        // 全部id
        *//*Queue<String> ids = FileUtil.getWeiboIds();
        for (String id : ids) {
            User user = new User();
            user.setId(id);
            getFollowCount(user, id);
            FileUtil.save(user);
        }*//*
    }*/




}
