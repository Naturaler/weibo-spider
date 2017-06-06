package com.xin.old;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by r.x on 2017/5/29.
 */
public class WeiBoRequest {
    public static int index = 0;
    public static String[] HOSTS;
    public static int[] PORTS;

    static  {
        init();
        System.out.println("HOSTS:");
        for (String host : HOSTS) {
            System.out.print(host + ",");
        }
        System.out.println();
        System.out.println("PORTS:");
        for (int port : PORTS) {
            System.out.print(port + ",");
        }
        System.out.println();
    }

    public static String getResponse(String url, int index) {
        // 执行请求前，先验证代理是否可用
//        boolean isConnect = Util.testProxy(HOSTS[index], PORTS[index]);
//        if (!isConnect) {
//            getResponse(url, ++index);
//        }
//        String host = "36.66.208.10";
//        int port = 8080;
//        OkHttpClient client = new OkHttpClient.Builder()
//                .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(host, port)))
////                .addInterceptor()
//                .build();
//        Request request = new Request.Builder()
//                .url(url)
//                .build();
        Response response = null;
//            System.out.println("getResponse index:" + index);
//            System.out.println("getResponse current host:" + HOSTS[index] + " current port:" + PORTS[index]);
        response = exeHttp(HOSTS[index], PORTS[index], url);
        int responseCode = response.code();
        switch (responseCode) {
            case 200:
                return response.body().toString();
            case 403:
                System.out.println(" ====== getResponse failed ! ======");
                return getResponse(url, ++index);
            default:
                System.out.println(" ====== getResponse failed ! something other happen ======");
                break;
        }
        return null;
    }

    private static Response exeHttp(String host, int port, String url) {
        OkHttpClient client = new OkHttpClient.Builder()
//                .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(host, port)))
//                .proxy(new Proxy(Proxy.Type.SOCKS, new InetSocketAddress("127.0.0.1", 9150)))
                // 设置超时
                .readTimeout(30L, TimeUnit.SECONDS)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .build();
        try {
            return client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 初始化HOSTS和PORTS数组
     */
    private static void init() {
//        List<Map<String, String>> proxys = Util.getProxyFromFile();
//        HOSTS = new String[proxys.size()];
//        PORTS = new int[proxys.size()];
//        int index = 0;
//        for (Map<String, String> proxy : proxys) {
//            HOSTS[index] = proxy.get("host");
//            PORTS[index] = Integer.parseInt(proxy.get("port"));
//            index++;
//        }
    }
}
