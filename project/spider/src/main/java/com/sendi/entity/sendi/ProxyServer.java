package com.sendi.entity.sendi;

/**
 * Created by r.x on 2017/6/2.
 */
public class ProxyServer {
    private String host;
    private int port;

    public ProxyServer(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public String toString() {
        return "ProxyServer{" +
                "host='" + host + '\'' +
                ", port=" + port +
                '}';
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
