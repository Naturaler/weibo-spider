package com.xin.old;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sendi.entity.sendi.User;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.*;

/**
 * Created by r.x on 2017/5/28.
 */
public class FileUtil {
    private final static String FILE = "waitids";
    private final static String RESOURCE_PATH = "/src/main/resources/";

    public static void main(String[] args) {
        Queue<String> queue = getWeiboIds();
        System.out.println("queue:" + queue);
    }

    public static Queue<String> getWeiboIds() {
        List<Byte> content = new ArrayList<>();
//        StringBuilder content = new StringBuilder();
        Properties properties = System.getProperties();
        final String PROJECT_PATH = properties.get("user.dir").toString();
        Queue<String> weiboIds = new ArrayDeque<>();
        File weiboIdsFile = new File(PROJECT_PATH + RESOURCE_PATH + FILE);
        RandomAccessFile randomAccessFile = null;
        try {
            randomAccessFile = new RandomAccessFile(weiboIdsFile, "r");
            FileChannel channel = randomAccessFile.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            // 从channel中读取指定字节数到buffer中
            int bytesRead = channel.read(buffer);
//            System.out.println("bytesRead:" + bytesRead);
            while (bytesRead != -1) {
                // 调用flip方法，准备从buffer中读取数据
                buffer.flip();
                while (buffer.hasRemaining()) {
                    content.add(buffer.get());
                }
                // 将未处理的数据移到前面
                buffer.compact();
                bytesRead = channel.read(buffer);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//        System.out.println("content:" + content.toString());
        byte[] bytes = new byte[content.size()];
        for (int i = 0; i < content.size(); i++) {
            bytes[i] = content.get(i);
        }
        String s = new String(bytes);
//        System.out.println(s);
        ObjectMapper mapper = new ObjectMapper();
//        mapper.readValue(s);
        /*try {
            Map<String, List<String>> map = mapper.readValue(weiboIdsFile, Map.class);
//            System.out.println("ids:" + map.get("ids"));
            List<String> list = map.get("ids");
            Queue<String> queue = new ArrayDeque<>();
            list.forEach(id -> queue.add(id));
            return queue;
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        return new ArrayDeque<>();
    }


}
