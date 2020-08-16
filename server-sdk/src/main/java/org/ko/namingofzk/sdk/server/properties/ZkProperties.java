package org.ko.namingofzk.sdk.server.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "zk")
public class ZkProperties {

    /**
     * 连接串
     */
    private String conn;

    /**
     * 超时时间
     */
    private int sessionTimeOut;

    /**
     * 服务器配置
     */
    private ServerProperties server = new ServerProperties();

    public String getConn() {
        return conn;
    }

    public void setConn(String conn) {
        this.conn = conn;
    }

    public int getSessionTimeOut() {
        return sessionTimeOut;
    }

    public void setSessionTimeOut(int sessionTimeOut) {
        this.sessionTimeOut = sessionTimeOut;
    }

    public ServerProperties getServer() {
        return server;
    }

    public void setServer(ServerProperties server) {
        this.server = server;
    }
}
