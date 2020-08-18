package org.ko.namingofzk.zk.util;

import org.I0Itec.zkclient.ZkClient;
import org.junit.jupiter.api.Test;

public class ZkClientTests {

    @Test
    public void zkClientTest() {
        ZkClient zkClient = new ZkClient("", 100000);
//        zkClient.createPersistent("/root/naming/service1/server1",true);//创建目录并写入数据
//        zkClient.writeData("/root/naming/service1/server1", "http://127.0.0.1:8081/serve");
//        String data = zkClient.readData("/root/naming/service1/server1");
//        System.out.println(data);
        zkClient.delete("/root/naming/service1/server1");//删除目录
    }

    @Test
    public void readDataTest() {
        ZkClient zkClient = new ZkClient("", 100000);
        String data = zkClient.readData("/root/naming/service1/server1");
        System.out.println(data);
    }
}
