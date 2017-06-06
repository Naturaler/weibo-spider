package com.xin;

import com.xin.old.Util;

/**
 * Created by r.x on 2017/6/1.
 */
public class Demo {
    /*private static final String HOST = "112.195.101.120";
    private static final int PORT = 808;

    // 测试代理ip是否能连通
    public static void main(String[] args) {
        Util.getProxyFromFile();
    }*/

    /*private static void test() {
        HttpHost proxy = new HttpHost("127.0.0.1", 8118, "http");

        DefaultHttpClient httpclient = new DefaultHttpClient();
        try {
            httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);

            HttpHost target = new HttpHost("www.google.com", 80, "http");
            HttpGet req = new HttpGet("/");

            System.out.println("executing request to " + target + " via " + proxy);
            HttpResponse rsp = httpclient.execute(target, req);
        } finally {
            // When HttpClient instance is no longer needed,
            // shut down the connection manager to ensure
            // immediate deallocation of all system resources
            httpclient.getConnectionManager().shutdown();
        }
    }*/

    /*public static void main() throws IOException
    {
        final String TORCHECK_HOSTNAME = "httptest.silvertunnel-ng.org";
        final TcpipNetAddress TORCHECK_NETADDRESS = new TcpipNetAddress(TORCHECK_HOSTNAME, 80);
        // create connection
        final NetSocket topSocket = NetFactory.getInstance().getNetLayerById(NetLayerIDs.TOR_OVER_TLS_OVER_TCPIP)
                .createNetSocket(null, null, TORCHECK_NETADDRESS);
        HttpUtil.getInstance();
        // communicate with the remote side
        final byte[] httpResponse = HttpUtil.get(topSocket, TORCHECK_NETADDRESS, "/checktor.php", 5000);
        String httpResponseStr = ByteArrayUtil.showAsString(httpResponse);
        System.out.println("http response body: " + httpResponseStr);
        if ("Congratulations. Your browser is configured to use Tor.".equals(httpResponseStr))
        {
            System.out.println("works");
        }
        else
        {
            System.out.println("something went wrong");
        }
    }*/
}
