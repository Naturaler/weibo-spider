package com.xin;

import org.junit.Test;

import java.io.*;
import java.net.*;

/**
 * Created by r.x on 2017/6/3.
 */
public class Demo02 {
    @Test
    public void exeRequest() {
        HttpURLConnection connection = getConnection();
        try {
            connection.setConnectTimeout(10000);
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("GET");//这里是请求方式 ，或者"POST"
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(false);
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36");
            connection.connect();

            InputStream inputStream = connection.getInputStream();
            System.out.println(inputStream);
            // 读取响应
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
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
            System.out.println("finally result:" + jsonStr);
            reader.close();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private HttpURLConnection getConnection() {
        try {
//            URL url = new URL("https://3g2upl4pq6kufc4m.onion/");
            URL url = new URL("http://duskgytldkxiuqc6.onion/");
            Proxy proxy = new Proxy(Proxy.Type.SOCKS, new InetSocketAddress("127.0.0.1", 9150));
            return (HttpURLConnection) url.openConnection(proxy);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
