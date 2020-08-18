package org.ko.namingofzk.sdk.client.zk;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.ZkConnection;
import org.ko.namingofzk.sdk.client.properties.ZkProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

        //确定全部服务
        String clientPath = zkProperties.getClient().getPath();
        List<String> childrens = zkClient.getChildren(clientPath);

        if (!childrens.isEmpty()) {
            List<String> urls = childrens.stream().map(childrenPath -> {
                String path = zkProperties.getClient().getPath() + "/" + childrenPath;
                return zkClient.readData(path).toString();
            }).collect(Collectors.toList());
            urls.forEach(System.out::println);
            holder.set(urls);
        }

        //监听孩子节点变化
        zkClient.subscribeChildChanges(zkProperties.getClient().getPath(), new IZkChildListener() {
            @Override
            public void handleChildChange(String parentPath, List<String> currentChilds) throws Exception {
                System.out.println("---child change---");
                holder.clear();
                if (!currentChilds.isEmpty()) {
                    List<String> urls = currentChilds.stream().map(childrenPath -> {
                        String path = zkProperties.getClient().getPath() + "/" + childrenPath;
                        return zkClient.readData(path).toString();
                    }).collect(Collectors.toList());
                    urls.forEach(System.out::println);
                    holder.set(urls);
                }
            }
        });

    }

}
