package org.ko.namingofzk.sdk.server;

import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;
import org.junit.jupiter.api.Test;

public class ZkClientTests {

    @Test
    public void testZkClient() {
        ZkClient zkClient = new ZkClient("");//建立连接
        // zkClient.exists('/root'); //判断目录是否存在
        zkClient.create("/root","mydata", CreateMode.PERSISTENT);//创建目录并写入数据
        String data=zkClient.readData("/root");
        System.out.println(data);
        zkClient.delete("/root");//删除目录
        // zkClient.deleteRecursive("/root");//递归删除节目录

    }

}
