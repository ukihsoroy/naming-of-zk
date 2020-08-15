package org.ko.namingofzk.zk.util;

import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;

public class Client {

    public static void main(String[] args) {
        ZkClient zkClient = new ZkClient("39.105.164.165:2181", 100000);
        zkClient.create("/root","mydata", CreateMode.PERSISTENT);//创建目录并写入数据
        String data=zkClient.readData("/root");
        System.out.println(data);
        zkClient.delete("/root");//删除目录
    }

}
