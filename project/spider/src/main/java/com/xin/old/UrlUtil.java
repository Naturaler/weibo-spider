package com.xin.old;

import java.io.*;
import java.net.*;
import java.util.List;
import java.util.Map;

/**
 * Created by r.x on 2017/6/2.
 */
public class UrlUtil {
    /*public static String[] HOSTS;
    public static int[] PORTS;
    static {
        List<Map<String, String>> proxys = Util.getProxyFromFile();
        HOSTS = new String[proxys.size()];
        PORTS = new int[proxys.size()];
        int index = 0;
        for (Map<String, String> proxy : proxys) {
            HOSTS[index] = proxy.get("host");
            PORTS[index] = Integer.parseInt(proxy.get("port"));
            index++;
        }
    }*/

    /*public static void main(String[] args) throws IllegalStateException, IOException {
//        doGet();
    }*/

    // 通过HttpUrlConnection的方式发送get请求
    /*public static String doGet(String address, int index) {
        try {
            // 设置tor代理
//            System.setProperty("http.proxyHost", "127.0.0.1");
//            System.setProperty("http.proxyPort", "9150");

//            System.setProperty("socksProxySet", "true");
//            System.setProperty("socksProxyHost", "127.0.0.1");
//            System.setProperty("socksProxyPort", "9150");

            // 创建连接
//            URL url = new URL("http://api.xicidaili.com/free2016.txt");//请求地址
//            URL url = new URL("https://m.weibo.cn/api/container/getIndex?containerid=231051_-_followers_-_2246744534&luicode=10000011&lfid=1005052246744534&featurecode=20000320&type=user&page=3");//请求地址
            URL url = new URL(address);
//            Proxy proxy = new Proxy(Proxy.Type.SOCKS, new InetSocketAddress("127.0.0.1", 9150));
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection(proxy);
            HttpURLConnection connection = null;
            if (index < 0) {
                Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("59.78.37.165", 1080));
                connection = (HttpURLConnection) url.openConnection(proxy);
            }else {
                while (!Util.testProxy(HOSTS[index], PORTS[index])) {
                    index ++;
                }
                Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(HOSTS[index], PORTS[index]));
                connection = (HttpURLConnection) url.openConnection(proxy);
            }
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("GET");//这里是请求方式 ，或者"POST"
            connection.setUseCaches(false);
            // URLConnection.setInstanceFollowRedirects是成员函数，仅作用于当前函数
            connection.setInstanceFollowRedirects(false);
            // 设置user-agent
//            connection.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,**///*;q=0.8");
//            connection.setRequestProperty("Accept-Encoding", "gzip, deflate, sdch");
//            connection.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6");
//            connection.setRequestProperty("Cache-Control", "max-age=0");
//            connection.setRequestProperty("Connection", "keep-alive");
//            connection.setRequestProperty("Cookie", "Hm_lvt_0cf76c77469e965d2957f0553e6ecf59=1496239779,1496284339,1496373507; Hm_lpvt_0cf76c77469e965d2957f0553e6ecf59=1496373507");
//            connection.setRequestProperty("Host", "api.xicidaili.com");
//            connection.setRequestProperty("If-Modified-Since", "Fri, 02 Jun 2017 03:16:01 GMT");
//            connection.setRequestProperty("Referer", "http://www.xicidaili.com/api");
//            connection.setRequestProperty("Upgrade-Insecure-Requests", "1");
//            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36");
//            connection.connect();
            
            /*InputStream inputStream = null;
            try {
                inputStream = connection.getInputStream();
            }catch (IOException e) {
                e.printStackTrace();
                connection.disconnect();
                System.out.println(" ！===== 被禁了！休眠5秒再继续！=====！");
                try {
                    // 休眠5秒后再继续
                    Thread.sleep(5000);
                    index ++;
                    while (!Util.testProxy(HOSTS[index], PORTS[index])) {
                        index ++;
                    }
                    System.out.println("successful index:" + index);
                    System.out.println("successful host:" + HOSTS[index] + ":" + PORTS[index]);
                    doGet(address, index);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }

            // 读取响应
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//            String result = ConvertStream2Json(connection.getInputStream());
            String result = null;
//            System.out.println(result);
            reader.close();
            // 断开连接
            connection.disconnect();
            return result;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }*/

    /*public static String doGetWithTor(String address) {
        try {
            // 设置tor代理
//            System.setProperty("socksProxySet", "true");
//            System.setProperty("socksProxyHost", "127.0.0.1");
//            System.setProperty("socksProxyPort", "9050");

            // 创建连接
            URL url = new URL(address);
            Proxy proxy = new Proxy(Proxy.Type.SOCKS, new InetSocketAddress("127.0.0.1", 9050));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection(proxy);
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("GET");//这里是请求方式 ，或者"POST"
            connection.setUseCaches(false);
            // URLConnection.setInstanceFollowRedirects是成员函数，仅作用于当前函数
            connection.setInstanceFollowRedirects(false);
            // 设置user-agent
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36");
            connection.connect();

            InputStream inputStream = null;
            try {
                inputStream = connection.getInputStream();
            }catch (IOException e) {
                e.printStackTrace();
                System.out.println(" ！===== 被禁了！休眠5秒再继续！=====！");
                try {
                    // 休眠5秒后再继续
                    Thread.sleep(5000);
                    doGetWithTor(address);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }

            // 读取响应
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//            String result = ConvertStream2Json(connection.getInputStream());
            String result = null;
//            System.out.println(result);
            reader.close();
            // 断开连接
            connection.disconnect();
            return result;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }*/

    // 通过HttpUrlConnection的方式发送Post请求
    /*@SuppressWarnings("unused")
    public static void doPost() throws IllegalStateException, IOException {
        try {
            // 创建连接
            URL url = new URL("http://127.0.0.1:8080/Text");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setRequestProperty("Content-Type", "application/json");//以json的方式传递参数
            connection.setInstanceFollowRedirects(false);
//			connection.setConnectTimeout(2000);
//			connection.setReadTimeout(3000);
            connection.setRequestProperty("Charsert", "UTF-8");
            connection.connect();

            // POST请求
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            out.write("{\"name\",\"admin\"}".getBytes("UTF-8"));//参数需要json格式(其实就是一个字符串)
            out.flush();
            out.close();

            // 读取响应
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//            String result = ConvertStream2Json(connection.getInputStream());
            String result = null;
            System.out.println(result);
            reader.close();
            // 断开连接
            connection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

	/**
            * 将流转换成String
	 *
             * @param inputStream
	 * @return
             */
    /*private static String ConvertStream2Json(InputStream inputStream) {
        String jsonStr = "";
        // ByteArrayOutputStream相当于内存输出流
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        // 将输入流转移到内存输出流中
        try {
            while ((len = inputStream.read(buffer, 0, buffer.length)) != -1) {
                out.write(buffer, 0, len);
            }
            // 将内存流转换为字符串
            jsonStr = new String(out.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonStr;
    }*/
}
