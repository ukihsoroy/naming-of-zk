package org.ko.namingofzk.sdk.server.zk;

import org.ko.namingofzk.sdk.server.properties.ZkProperties;
import org.ko.namingofzk.zk.util.ZkClientCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ServerRegisterZkConfig {

    @Autowired
    private ZkProperties zkProperties;

    @PostConstruct
    public void doConstruct() throws Exception {
        System.out.println("初始化：PostConstruct");

        //初始化zk client
        ZkClientCrud<String> zkClientCrud = new ZkClientCrud<>(
                zkProperties.getConn(),
                zkProperties.getSessionTimeOut());



        //初始化创建节点
        zkClientCrud.createEphemeral(zkProperties.getServer().getPath(), true);
        zkClientCrud.writeData(zkProperties.getServer().getPath(), zkProperties.getServer().getAddress());
    }



}
