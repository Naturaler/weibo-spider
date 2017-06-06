package com.sendi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sendi.entity.sendi.ProxyServer;
import com.sendi.entity.sendi.User;
import com.sendi.entity.weibo.*;
import com.sendi.util.FileUtil;
import com.sendi.util.RequestUtil;
import org.apache.commons.net.telnet.TelnetClient;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by r.x on 2017/6/2.
 */
public class SpiderMain {
    private static Logger logger = Logger.getLogger("SpiderMain");
    
    private static final String TARGET_FANS = "fans";
    private static final String TARGET_FOLLOWERS = "followers";
    private static final String FOLLOWER_FEATURE_CODE = "2000032"; // 粉丝列表
    private static final String FOLLOW_FEATURE_CODE = "20000320"; // 关注列表
    private static final List<ProxyServer> proxys;
    private static int index; // 记录代理的下标：当ip被禁或代理不可用时，从proxys中选择下一个代理
    public static String currentProxyHost;
    public static int currentProxyPort;
    private static final String MONTH_DAY_TIME_REGEX = "^\\d+-\\d+\\s+\\d+:\\d+";
    private static final Pattern MONTH_DAY_TIME_PATTERN = Pattern.compile(MONTH_DAY_TIME_REGEX);
    private static final SimpleDateFormat CREATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    static {
        proxys = FileUtil.getProxyFromFile();
    }
    
    public static void main(String[] args) {
        //
//        System.setProperty("https.protocols", "TLSv1.1");
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        logger.info("start time:" + format.format(date));
//        Queue<String> ids = FileUtil.getWeiboIds();
//        logger.info("total id size:" + ids.size());
//        for (String id : ids) {
//            User user = new User();
//            user.setId(id);
//            getFollowCount(user, id);
//            FileUtil.save(user);
//        }
        List<String> ids = FileUtil.getWeiboIdsInList();
        logger.info("total id size:" + ids.size());
        for (String id : ids) {
            if (hadGot(id)) {
                continue;
            }
            User user = new User();
            user.setId(id);
            getFollowCount(user, id);
            FileUtil.save(user);
        }
        logger.info("sleep count:" + RequestUtil.sleepCount);

    }
    
    public static void updateProxy() {
        currentProxyHost = proxys.get(index).getHost();
        currentProxyPort = proxys.get(index).getPort();
        if (!testProxy()) {
            index++;
            updateProxy();
        }
        index++;
    }

    /**
     * 获取用户粉丝数和关注数
     * 用户首页：https://m.weibo.cn/u/{weiboId}
     * 获取用户粉丝数和关注数：https://m.weibo.cn/api/container/getIndex?type=uid&value={weiboId}&containerid=100505{weiboId}
     * @param weiboId 用户微博id
     * @return
     */
    private static Map<String, Integer> getFollowCount(User user, String weiboId) {
        String url = "https://m.weibo.cn/api/container/getIndex?type=uid&value="
                + weiboId + "&containerid=100505" + weiboId + "&retcode=6102";
        logger.info("getFollowCount url:" + url);
        String result = RequestUtil.exeRequest(url);
        logger.info("getFollowCount result：" + result);
        if (result == null) {
            result = RequestUtil.exeRequest(url);
        }
        if (result.indexOf("errmsg") > 0 && result.indexOf("errno") > 0) {
            logger.info("id error:" + result);
            return Collections.EMPTY_MAP;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        IndexInfo indexInfo = null;
        try {
            indexInfo = objectMapper.readValue(result, IndexInfo.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            UserInfo userInfo = indexInfo.getUserInfo();
            Integer follow_count = userInfo.getFollow_count();
            Integer follower_count = userInfo.getFollowers_count();
            Map<String, Integer> map = new HashMap<>();
            // 关注数
            map.put("follow_count", follow_count);
            // 粉丝数
            map.put("follower_count", follower_count);
            // 粉丝列表
            getFollowerList(user, userInfo);
            // 关注列表
            getFollowList(user, userInfo);
            // 用户详细信息
            getUserInfo(user, userInfo);
            // 微博内容
            getWeiboContent(user, userInfo);
            return map;
        }catch (Exception e) {
            e.printStackTrace();
            return Collections.EMPTY_MAP;
        }
    }
    /**
     * 获取用户关注列表
     * 问题：不能获取全部关注信息！在微博页面操作也不行！
     * url：https://m.weibo.cn/api/container/getIndex?containerid=231051_-_followers_-_2246744534&luicode=10000011&lfid=1005052246744534&featurecode=20000320&type=user&page=3
     *
     */
    private static boolean getFollowList(User user, UserInfo userInfo) {
        List<String> follows = new ArrayList<>();
        String[] containeridAndluicode = getContaineridAndluicode(userInfo.getFollow_scheme());
        int pagination = 1;
        boolean hasMore = true;
        while (hasMore) {
            String url = createFollowOrFollowerUrl(userInfo.getId(), TARGET_FOLLOWERS, containeridAndluicode[0],
                    containeridAndluicode[1], pagination++);
            logger.info("getFollowList url:" + url);
//            String response = RequestUtil.exeRequest(url);
            String response = RequestUtil.exeRequest(url);
            if (response == null) {
                response = RequestUtil.exeRequest(url);
            }
            hasMore = parseResponse(response, follows);
        }
        user.setFollows(follows);
        return true;
    }

    /**
     * 获取用户粉丝列表
     * 问题：不能获取全部粉丝信息！在微博页面操作也不行！貌似最多只能看10页
     * url：https://m.weibo.cn/api/container/getIndex?containerid=231051_-_followers_-_2246744534&luicode=10000011&lfid=1005052246744534&featurecode=20000320&type=user&page=3
     *
     */
    private static boolean getFollowerList(User user, UserInfo userInfo) {
        List<String> followers = new ArrayList<>();
        String[] containeridAndluicode = getContaineridAndluicode(userInfo.getFans_scheme());
        int pagination = 1;
        boolean hasMore = true;
        while (hasMore) {
            String url = createFollowOrFollowerUrl(userInfo.getId(), TARGET_FANS, containeridAndluicode[0],
                    containeridAndluicode[1], pagination++);
            logger.info("getFollowerList url:" + url);
//            String response = RequestUtil.exeRequest(url);
            String response = RequestUtil.exeRequest(url);
            if (response == null) {
                response = RequestUtil.exeRequest(url);
            }
            hasMore = parseResponse(response, followers);
        }
        user.setFollowers(followers);
        return true;
    }

    /**
     * 获取微博内容
     * 1742566624
     * url:https://m.weibo.cn/api/container/getIndex?type=uid&value={weiboId}&containerid=107603{weiboId}&page=6
     * @param userInfo
     */
    private static boolean getWeiboContent(User user, UserInfo userInfo) {
        List<MBlog> weibos = new ArrayList<>();
        int pagination = 1;
        // 每次加载的内容条数不定，不计算总页码，直到无法取得更多内容为止
        while (true) {
            String url = "https://m.weibo.cn/api/container/getIndex?" +
                    "type=uid" +
                    "&value=" + userInfo.getId() +
                    "&containerid=" + "107603" + userInfo.getId() +
                    "&page=" + pagination++ +
                    "&retcode=6102";
            logger.info("getWeiboContent url:" + url);
//            String response = RequestUtil.exeRequest(url);
            String response = RequestUtil.exeRequest(url);
            if (response == null) {
                response = RequestUtil.exeRequest(url);
            }
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                FollowInfo followInfo = objectMapper.readValue(response, FollowInfo.class);
                if (followInfo.getCards().size() < 1) {
                    break;
                }
                List<Map<String, Object>> cards = (List<Map<String, Object>>) followInfo.getCards();
                for (Map<String, Object> card : cards) {
                    Integer card_type = (Integer) card.get("card_type");
                    if (card_type == 9) {
                        ObjectMapper mapper = new ObjectMapper();
//                        logger.info("getWeiboContent mblog:" + card.get("mblog").toString());
//                        MBlog mBlog = mapper.readValue(card.get("mblog").toString(), MBlog.class);
                        Map<String, Object> map = (Map<String, Object>) card.get("mblog");
                        MBlog mBlog = new MBlog();
                        mBlog.setId(map.get("id").toString());
                        mBlog.setMid(map.get("mid").toString());
                        mBlog.setIdstr(map.get("idstr").toString());
                        mBlog.setText(map.get("text").toString());
                        mBlog.setSource(map.get("source").toString());
                        mBlog.setFavorited((Boolean) map.get("favorited"));
                        mBlog.setUser(map.get("user"));
                        mBlog.setPid((Long) map.get("pid"));
                        if (map.get("retweeted_status") != null) {
                            Map<String, Object> retweeted_status = (Map<String, Object>) map.get("retweeted_status");
                            RetweetedStatus retweetedStatus = new RetweetedStatus();
                            retweetedStatus.setId(retweeted_status.get("id").toString());
                            retweetedStatus.setMid(retweeted_status.get("mid").toString());
                            retweetedStatus.setIdstr(retweeted_status.get("idstr").toString());
                            retweetedStatus.setText(retweeted_status.get("text").toString());
                            retweetedStatus.setTextLength((Integer) retweeted_status.get("textLength"));
                            retweetedStatus.setSource(retweeted_status.get("source").toString());
                            retweetedStatus.setFavorited((Boolean) retweeted_status.get("favorited"));
                            retweetedStatus.setUser(retweeted_status.get("user"));
                            retweetedStatus.setReposts_count((Integer) retweeted_status.get("reposts_count"));
                            retweetedStatus.setComments_count((Integer) retweeted_status.get("comments_count"));
                            retweetedStatus.setAttitudes_count((Integer) retweeted_status.get("attitudes_count"));
                            retweetedStatus.setLongText((Boolean) retweeted_status.get("isLongText"));
                            retweetedStatus.setVisible(retweeted_status.get("visible"));
                            retweetedStatus.setPage_info(retweeted_status.get("page_info"));
                            retweetedStatus.setBid(retweeted_status.get("bid").toString());
                            // 格式化时间
                            String actualCreateAt = formatDatetime(retweeted_status.get("created_at").toString());
                            retweetedStatus.setCreated_at(actualCreateAt);

                            mBlog.setRetweeted_status(retweetedStatus);
                        }
                        mBlog.setReposts_count((Integer) map.get("reposts_count"));
                        mBlog.setComments_count((Integer) map.get("comments_count"));
                        mBlog.setAttitudes_count((Integer) map.get("attitudes_count"));
                        mBlog.setLongText((Boolean) map.get("isLongText"));
                        mBlog.setVisible(map.get("visible"));
                        mBlog.setMblogtype((Integer) map.get("mblogtype"));
                        if (map.get("raw_text") != null) {
                            mBlog.setRaw_text(map.get("raw_text").toString());
                        }
                        mBlog.setBid(map.get("bid").toString());
                        // 统一时间格式
                        String actualCreateTime = formatDatetime(map.get("created_at").toString());
                        mBlog.setCreated_at(actualCreateTime);
//                        weibos.add(card.get("mblog"));
                        weibos.add(mBlog);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                user.setWeibos(weibos);
                user.setId(user.getId() + "_error");
                return false;
            }
        }
        user.setWeibos(weibos);
        return true;
    }

    /**
     * 获取用户详细信息
     * url:https://m.weibo.cn/api/container/getIndex?containerid=230283{weiboId}_-_INFO&luicode=10000011&lfid=230283{weiboId}&featurecode=20000320&type=uid&value={weiboId}
     * todo 解析userinfo与url中containerid和luicode的关系，防止写死导致无法取得结果
     * @param userInfo
     */
    private static boolean getUserInfo(User user, UserInfo userInfo) {
        Map<String, String> info = new HashMap<>();
//        String[] containeridAndluicode = getContaineridAndluicode(userInfo.getFans_scheme());
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append("https://m.weibo.cn/api/container/getIndex?");
//        url.append("containerid=").append(containeridAndluicode[0]).append(userInfo.getId()).append("_-_INFO");
        urlBuilder.append("containerid=").append("230283").append(userInfo.getId()).append("_-_INFO");
//        url.append("&title=").append();
//        url.append("&luicode=").append(containeridAndluicode[1]);
        urlBuilder.append("&luicode=").append("10000011");
//        url.append("&lfid=").append("230283").append(userInfo.getId()); // 可有可无
//        url.append("&featurecode=").append("20000180"); // 使用该code需要额外添加title参数
        urlBuilder.append("&featurecode=").append("20000320");
        urlBuilder.append("&type=uid");
        urlBuilder.append("&value=").append(userInfo.getId());
        urlBuilder.append("&retcode=6102");
        logger.info("getUserInfo url:" + urlBuilder);
        String url = urlBuilder.toString();
        String response = RequestUtil.exeRequest(url);
        if (response == null) {
            response = RequestUtil.exeRequest(url);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            FollowInfo followInfo = objectMapper.readValue(response, FollowInfo.class);
            List<Map<String, Object>> cards = followInfo.getCards();
            for (Map<String, Object> card : cards) {
                List<Map<String, Object>> card_group = (List<Map<String, Object>>) card.get("card_group");
                if (card_group.size() < 1) {
                    continue;
                }
                for (Map<String, Object> map : card_group) {
                    // 只有card_type为41时，才有item_name参数
                    if (map.get("card_type") != null && (Integer)map.get("card_type") == 41) {
                        info.put(map.get("item_name").toString(), map.get("item_content").toString());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            user.setInfo(info);
            user.setId(user.getId() + "_error");
            return false;
        }
        user.setInfo(info);
        return true;
    }

    /**
     * 解析返回结果
     * @param response
     */
    private static boolean parseResponse(String response, List<String> weiboIds) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            FollowInfo followInfo = objectMapper.readValue(response, FollowInfo.class);
            if (followInfo.getCards() != null) {
                Map<String, Object> map = (Map<String, Object>) followInfo.getCards().get(0);
                List<Map<String, Object>> card_group = (List<Map<String, Object>>) map.get("card_group");
                if (card_group != null) {
                    for (Map<String, Object> cardInfo : card_group) {
                        Map<String, Object> user = (Map<String, Object>) cardInfo.get("user");
                        weiboIds.add(user.get("id").toString());
                    }
                }
                return true;
            }
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据粉丝或关注schema，解析出containerid和luicode
     * @param schema 粉丝或关注的schema，可从userinfo中得到
     * @return String[0] = containerid; String[1] = luicode
     */
    private static String[] getContaineridAndluicode(String schema) {
        final String REGEX = "containerid=(?<containerid>\\d+).+&luicode=(?<luicode>\\d+).+";
        Matcher matcher = Pattern.compile(REGEX, Pattern.CASE_INSENSITIVE).matcher(schema);
        if (matcher.find()) {
            String containerid = matcher.group("containerid");
            String luicode = matcher.group("luicode");
            String[] result = new String[2];
            result[0] = containerid;
            result[1] = luicode;
            return result;
        }else {
            logger.info(" ===== getContaineridAndluicode failed ===== ");
            return null;
        }
    }

    /**
     * 创建获取粉丝列表或关注列表的url
     * @param weiboId 微博id
     * @param target 枚举值：fans或followers，分别代表粉丝和关注
     * @param containerid
     * @param luicode
     * @param page 页码，每页15个数据
     * @return
     */
    private static String createFollowOrFollowerUrl(String weiboId, String target, String containerid, String luicode, int page) {
        StringBuilder url = new StringBuilder();
        url.append("https://m.weibo.cn/api/container/getIndex?containerid=").append(containerid);
        url.append("_-_").append(target);
        url.append("_-_").append(weiboId);
        url.append("&luicode=").append(luicode);
        url.append("&lfid=100505").append(weiboId);
        switch (target) {
            case TARGET_FANS :
                url.append("&featurecode=").append(FOLLOWER_FEATURE_CODE);
                break;
            case TARGET_FOLLOWERS :
                url.append("&featurecode=").append(FOLLOW_FEATURE_CODE);
                break;
            default:
                logger.info(" ===== createFollowOrFollowerUrl failed ===== ");
        }
        url.append("&type=user");
        url.append("&page=").append(page);
        return url.toString();
    }

    /**
     * 测试给定的代理ip和端口是否能连通
     * @return
     */
    public static boolean testProxy() {
        String host = currentProxyHost;
        int port = currentProxyPort;
        logger.info("testProxy:"+host + ":" + port);
        TelnetClient client = new TelnetClient();
        // 超时设置：单位毫秒
        client.setDefaultTimeout(3000);
        try {
            client.connect(host, port);
            return true;
        } catch (IOException e) {
//            e.printStackTrace();
            logger.info(" ===== testProxy:"+host + ":" + port + " failed =====");
        }
        return false;
    }

    /**
     * 四种时间格式：
     * a、15分钟前
     * b、今天 14:20
     * c、06-04 23:37
     * d、2016-12-24 01:46
     * 统一转换为：2016-12-24 01:46
     * @param create_at
     */
    public static String formatDatetime(String create_at) {
        CREATE_TIME_FORMAT.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        if (create_at.indexOf("分钟前") > 0) { // 15分钟前
            int index = create_at.indexOf("分钟前");
            String minutes = create_at.substring(0, index).trim();
            long current = new Date().getTime();
            long actualCreateTime = current - Integer.parseInt(minutes) * 60 * 1000;
            Date date = new Date(actualCreateTime);
            return CREATE_TIME_FORMAT.format(date);
        }else if (create_at.indexOf("今天") >= 0) { // 今天 14:20
            String actualMinute = create_at.substring(create_at.length() - 5, create_at.length());
            String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            return currentDate + " " + actualMinute;
        }else {
            Matcher matcher = MONTH_DAY_TIME_PATTERN.matcher(create_at);
            if (matcher.find()) { // 06-04 23:37
                String currentYear = new SimpleDateFormat("yyyy").format(new Date());
                return currentYear + "-" + create_at;
            }
        }
        return create_at;
    }

    /**
     * 判断该微博id是否已爬取
     * @param weiboId
     * @return
     */
    private static boolean hadGot(String weiboId) {
        String path = FileUtil.REPOSITORY_PATH + weiboId + ".json";
        File file = new File(path);
        return file.exists();
    }
}
