package com.xin.old;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.net.telnet.TelnetClient;

import java.io.*;
import java.util.*;

/**
 * Created by r.x on 2017/6/1.
 */
public class Util {


    /**
     * 获取代理ip和端口
     * @return
     */
    public static List<Map<String, Object>> getProxy() {
//        final String url = "http://api.xicidaili.com/free2016.txt";
        final String url = "http://api.xicidaili.com";
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder()
                .url(url)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String body = response.body().toString();
            System.out.println(body);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.EMPTY_LIST;
    }


}
