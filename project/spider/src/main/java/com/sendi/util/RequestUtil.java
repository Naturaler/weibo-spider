package com.sendi.util;

import com.sendi.SpiderMain;
import org.junit.Test;

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

/**
 * Created by r.x on 2017/6/2.
 */
public class RequestUtil {
    private static Logger logger = Logger.getLogger("RequestUtil");
    public static int count = 0; // 统计发送请求的次数
    public static int sleepCount = 0;
    public static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final String[] USER_AGENTS = {
            "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_8; en-us) AppleWebKit/534.50 (KHTML, like Gecko) Version/5.1 Safari/534.50",
            "Mozilla/5.0 (Windows; U; Windows NT 6.1; en-us) AppleWebKit/534.50 (KHTML, like Gecko) Version/5.1 Safari/534.50",
            "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)",
            "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0; Trident/4.0)",
    };

    @Test
    public void test() {
        System.setProperty("https.protocols", "TLSv1.1");
        //http://duskgytldkxiuqc6.onion/
//        String address = "https://3g2upl4pq6kufc4m.onion/";
        // tor网络测试url
//        String address = "http://icxe4yp32mq6gm6n.onion/";
//        String address = "http://duskgytldkxiuqc6.onion/";
        String address = "http://icxe4yp32mq6gm6n.onion/";
        System.out.println("===========");
//        String address = "https://m.weibo.cn/api/container/getIndex?type=uid&value=2246744534&containerid=1005052246744534&retcode=6102";
        String result = exeRequest(address);
        System.out.println("finally result:" + result);
        System.out.println("===========");
    }

    public static void main(String[] args) {
        System.setProperty("https.protocols", "TLSv1.1");
        System.out.println("======main=====");
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String address = "https://m.weibo.cn/api/container/getIndex?type=uid&value=2246744534&containerid=1005052246744534&retcode=6102";
                String result = exeRequest(address);
                System.out.println("finally result:" + result);
            }
        });
        thread.start();
        System.out.println("=====main======");
    }

    public static String exeRequest(String address) {
        String proxyHost = SpiderMain.currentProxyHost;
        int proxyPort = SpiderMain.currentProxyPort;
        try {
            HttpURLConnection connection = null;
//            if (proxyHost == null) {
//                connection = getConnectionByTorProxy(address);
//            }else {
//                connection = getConnectionByTorProxy(address, proxyHost, proxyPort);
//            }
            connection = getConnectionByTorProxy(address, proxyHost, proxyPort);
            // 超时设置：毫秒
//            connection.setConnectTimeout(1000 * 10);
//            connection.setReadTimeout(1000 * 10);
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("GET");//这里是请求方式 ，或者"POST"
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(false);
            // firefox: user-agent Mozilla/5.0 (Windows NT 10.0; WOW64; rv:53.0) Gecko/20100101 Firefox/53.0
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36");
//            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:53.0) Gecko/20100101 Firefox/53.0");
            // 每请求500次，休眠5分钟
//            if (count >= 500) {
//                logger.info(" ===== request reach 500 times , take a rest for 5 minutes ===== ");
//                logger.info("rest start time:" + format.format(new Date()));
//                Thread.sleep(6000 * 10 * 5);
//                count = 0;
//                sleepCount ++;
//            }
            try {
                // 每次请求休眠1秒
//                Thread.sleep(1000);
                connection.connect();
//            }catch (SocketTimeoutException e) {
            }catch (SocketException | SocketTimeoutException e) {
//                logger.info("exeRequest message:" + e.getMessage());
                logger.info(" ===== proxy timeout =====");
                // socket超时的异常信息：SocketTimeoutException: connect timed out
                e.printStackTrace();
                // todo 可能是ip被禁了
//                connection.disconnect();
                // 更换代理
//                SpiderMain.updateProxy();
                return exeRequest(address);
            }
            count++;
            InputStream inputStream = null;
            try {
                inputStream = connection.getInputStream();
//                connection.disconnect();
            }catch (IOException e) {
                logger.info("exeRequest message:" + e.getMessage());
                logger.info(" ===== ip is banned =====");
                logger.info(" ===== total count : " + count + "======");
                Date date = new Date();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                logger.info(" ===== ip banned time:" + format.format(date));
                e.printStackTrace();
                // todo 可能是ip被禁了
                // 可以爬n条，休眠m秒，或者遇到403后，再休眠x秒（遇到403后，需要休眠较长时间）
                // ip被禁后的异常信息：Server returned HTTP response code: 403 for URL
                // 请求了1132条后，被禁了
                // 请求了700+条后，被禁了
//                try {
//                    Thread.sleep(6000 * 10 * 30);
//                } catch (InterruptedException e1) {
//                    e1.printStackTrace();
//                }
//                connection.disconnect();
                // 更换代理
//                SpiderMain.updateProxy();
                logger.info(" ===== restart time:" + format.format(new Date()));
                return exeRequest(address);
//                return null;
            }
            if (inputStream == null) {
                inputStream = connection.getInputStream();
            }
            // 读取响应
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String result = convertStream2Json(connection.getInputStream());
            reader.close();
            // 断开连接
            connection.disconnect();
            return result;
        } catch (MalformedURLException e) {
            e.printStackTrace();
//            return exeRequest(address);
        } catch (IOException e) {
            e.printStackTrace();
//            return exeRequest(address);
        }
        return null;
    }

    private static HttpURLConnection getConnectionByTorProxy(String address) {
        try {
            URL url = new URL(address);
            return (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static HttpURLConnection getConnectionByTorProxy(String address, String proxyHost, int proxyPort) {
        try {
            URL url = new URL(address);
//            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort));
            Proxy proxy = new Proxy(Proxy.Type.SOCKS, new InetSocketAddress("127.0.0.1", 9150));
            return (HttpURLConnection) url.openConnection(proxy);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static HttpURLConnection getConnectionByHttpProxy(String address, String proxyHost, int proxyPort) {
        try {
            URL url = new URL(address);
//            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort));
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("117.90.5.204", 9000));
            return (HttpURLConnection) url.openConnection(proxy);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String convertStream2Json(InputStream inputStream) {
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
    }
}
