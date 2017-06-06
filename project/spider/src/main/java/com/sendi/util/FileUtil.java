package com.sendi.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sendi.entity.sendi.ProxyServer;
import com.sendi.entity.sendi.User;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.*;
import java.util.logging.Logger;

/**
 * Created by r.x on 2017/6/2.
 */
public class FileUtil {
    private static Logger logger = Logger.getLogger("FileUtil");

    public final static String REPOSITORY_PATH = "E:\\software\\intelliJ IDEA\\project\\spider\\data\\";
    private final static String FILE = "waitids";
    private final static String RESOURCE_PATH = "/src/main/resources/";
    private final static ObjectMapper mapper = new ObjectMapper();

    public static Queue<String> getWeiboIds() {
        Properties properties = System.getProperties();
        final String PROJECT_PATH = properties.get("user.dir").toString();
        File weiboIdsFile = new File(PROJECT_PATH + RESOURCE_PATH + FILE);
        ObjectMapper mapper = new ObjectMapper();
        try {
            Map<String, List<String>> map = mapper.readValue(weiboIdsFile, Map.class);
            List<String> list = map.get("ids");
            Queue<String> queue = new ArrayDeque<>();
            for (String id : list) {
                queue.add(id);
            }
//            list.forEach(id -> queue.add(id));
            return queue;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayDeque<>();
    }

    public static List<String> getWeiboIdsInList() {
        Properties properties = System.getProperties();
        final String PROJECT_PATH = properties.get("user.dir").toString();
        File weiboIdsFile = new File(PROJECT_PATH + RESOURCE_PATH + FILE);
        ObjectMapper mapper = new ObjectMapper();
        try {
            Map<String, List<String>> map = mapper.readValue(weiboIdsFile, Map.class);
            List<String> list = map.get("ids");
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.EMPTY_LIST;
    }

    public static void save(User user) {
        logger.info("ready to write to file:" + user);
        String filename = REPOSITORY_PATH + user.getId() + ".json";
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(new File(filename));
            FileChannel channel = fos.getChannel();
//            ByteBuffer src = Charset.forName("utf8").encode(user.toString());
            ByteBuffer src = Charset.forName("utf8").encode(mapper.writeValueAsString(user));
            // 字节缓冲的容量和limit会随着数据长度变化，不是固定不变的
            int length = 0;
            while ((length = channel.write(src)) != 0) {
                /*
                 * 注意，这里不需要clear，将缓冲中的数据写入到通道中后 第二次接着上一次的顺序往下读
                 */
                logger.info("");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        logger.info("save done ! next one !");
        logger.info("request count : " + RequestUtil.count);
    }

    /**
     * 从文件中读取代理ip和端口
     * @return
     */
    public static List<ProxyServer> getProxyFromFile() {
        List<ProxyServer> proxys = new ArrayList<>();
        final String path = "E:\\software\\intelliJ IDEA\\project\\spider\\src\\main\\resources\\proxy";
        File file = new File(path);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String tmp;
            while ((tmp = reader.readLine()) != null) {
                String[] value = tmp.split(":");
                ProxyServer proxyServer = new ProxyServer(value[0], Integer.valueOf(value[1]));
                proxys.add(proxyServer);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("proxys:" + proxys);
        return proxys;
    }
}
