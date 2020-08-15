package org.ko.namingofzk.zk.util;

import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;
import org.junit.jupiter.api.Test;

public class ZkClientTests {

    @Test
    public void zkClientTest() {
        ZkClient zkClient = new ZkClient("39.105.164.165:2181", 100000);
        zkClient.create("/root","mydata", CreateMode.PERSISTENT);//创建目录并写入数据
        String data=zkClient.readData("/root");
        System.out.println(data);
        zkClient.delete("/root");//删除目录
    }
}
