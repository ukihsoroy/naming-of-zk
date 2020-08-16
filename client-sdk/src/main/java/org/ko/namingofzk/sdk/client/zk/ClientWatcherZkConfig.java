package org.ko.namingofzk.sdk.client.zk;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.ZkConnection;
import org.ko.namingofzk.sdk.client.properties.ZkProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class ClientWatcherZkConfig {

    @Autowired
    private ZkProperties zkProperties;

    @Autowired
    private ServerAddressHolder holder;

    private ZkClient zkClient;

    @PostConstruct
    public void doConstruct() throws Exception {
        System.out.println("初始化：PostConstruct");

        zkClient = new ZkClient(
                new ZkConnection(zkProperties.getConn()),
                zkProperties.getSessionTimeOut());

        //确定主服务
        String key = zkClient.readData(zkProperties.getClient().getPath());
        holder.setKey(key);

        //确定全部服务
        zkClient.getChildren(zkProperties.getClient().getPath()).forEach(x -> holder.set(x, zkClient.readData(x)));

        //监听父节点
        zkClient.subscribeDataChanges(zkProperties.getClient().getPath(), new IZkDataListener() {
            @Override
            public void handleDataChange(String dataPath, Object key) throws Exception {
                holder.setKey(key.toString());
            }

            @Override
            public void handleDataDeleted(String dataPath) throws Exception {
                System.out.println("server remove!");
            }
        });

        //监听孩子节点变化
        zkClient.subscribeChildChanges(zkProperties.getClient().getPath(), new IZkChildListener() {
            @Override
            public void handleChildChange(String parentPath, List<String> currentChilds) throws Exception {
                currentChilds.forEach(x -> holder.set(x, zkClient.readData(x)));
            }
        });

    }

}
