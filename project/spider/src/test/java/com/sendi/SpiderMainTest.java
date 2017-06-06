package com.sendi;

import com.sendi.entity.weibo.MBlog;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * SpiderMain Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>06/05/2017</pre>
 */
public class SpiderMainTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: main(String[] args)
     */
    @Test
    public void testMain() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: updateProxy()
     */
    @Test
    public void testUpdateProxy() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: testProxy()
     */
    @Test
    public void testTestProxy() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: formatDatetime(MBlog mBlog)
     */
    @Test
    public void testFormatDatetime() throws Exception {
        String mBlog = "15分钟前";
        System.out.println(SpiderMain.formatDatetime(mBlog));

        mBlog = "今天 14:20";
        SpiderMain.formatDatetime(mBlog);
        System.out.println(SpiderMain.formatDatetime(mBlog));

        mBlog = "06-04 23:37";
        SpiderMain.formatDatetime(mBlog);
        System.out.println(SpiderMain.formatDatetime(mBlog));

        mBlog = "2016-12-24 01:46";
        SpiderMain.formatDatetime(mBlog);
        System.out.println(SpiderMain.formatDatetime(mBlog));
    }


    /**
     * Method: getFollowCount(User user, String weiboId)
     */
    @Test
    public void testGetFollowCount() throws Exception {
//TODO: Test goes here... 
/* 
try { 
   Method method = SpiderMain.getClass().getMethod("getFollowCount", User.class, String.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }

    /**
     * Method: getFollowList(User user, UserInfo userInfo)
     */
    @Test
    public void testGetFollowList() throws Exception {
//TODO: Test goes here... 
/* 
try { 
   Method method = SpiderMain.getClass().getMethod("getFollowList", User.class, UserInfo.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }

    /**
     * Method: getFollowerList(User user, UserInfo userInfo)
     */
    @Test
    public void testGetFollowerList() throws Exception {
//TODO: Test goes here... 
/* 
try { 
   Method method = SpiderMain.getClass().getMethod("getFollowerList", User.class, UserInfo.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }

    /**
     * Method: getWeiboContent(User user, UserInfo userInfo)
     */
    @Test
    public void testGetWeiboContent() throws Exception {
//TODO: Test goes here... 
/* 
try { 
   Method method = SpiderMain.getClass().getMethod("getWeiboContent", User.class, UserInfo.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }

    /**
     * Method: getUserInfo(User user, UserInfo userInfo)
     */
    @Test
    public void testGetUserInfo() throws Exception {
//TODO: Test goes here... 
/* 
try { 
   Method method = SpiderMain.getClass().getMethod("getUserInfo", User.class, UserInfo.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }

    /**
     * Method: parseResponse(String response, List<String> weiboIds)
     */
    @Test
    public void testParseResponse() throws Exception {
//TODO: Test goes here... 
/* 
try { 
   Method method = SpiderMain.getClass().getMethod("parseResponse", String.class, List<String>.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }

    /**
     * Method: getContaineridAndluicode(String schema)
     */
    @Test
    public void testGetContaineridAndluicode() throws Exception {
//TODO: Test goes here... 
/* 
try { 
   Method method = SpiderMain.getClass().getMethod("getContaineridAndluicode", String.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }

    /**
     * Method: createFollowOrFollowerUrl(String weiboId, String target, String containerid, String luicode, int page)
     */
    @Test
    public void testCreateFollowOrFollowerUrl() throws Exception {
//TODO: Test goes here... 
/* 
try { 
   Method method = SpiderMain.getClass().getMethod("createFollowOrFollowerUrl", String.class, String.class, String.class, String.class, int.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }

    /**
     * Method: hadGot(String weiboId)
     */
    @Test
    public void testHadGot() throws Exception {
//TODO: Test goes here... 
/* 
try { 
   Method method = SpiderMain.getClass().getMethod("hadGot", String.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }

} 
