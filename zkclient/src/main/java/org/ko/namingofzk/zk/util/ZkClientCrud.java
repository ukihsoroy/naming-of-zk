package org.ko.namingofzk.zk.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.ZkSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * zk conn
 * @param <T>
 */
public class ZkClientCrud<T> {

    private final ObjectMapper mapper = new ObjectMapper();

    private final ZkClient zkClient;

    private final Class<T> clazz;

    final static Logger logger = LoggerFactory.getLogger(ZkClientCrud.class);

    /**
     * 获取zk 操作
     * @param connectString 连接串
     * @param sessionTimeout 超时时间
     * @param zkSerializer 序列化
     * @param clazz 封装类型
     */
    public ZkClientCrud(String connectString, int sessionTimeout, ZkSerializer zkSerializer, Class<T> clazz) {
        logger.info("链接zk开始");
        zkClient = new ZkClient(connectString, sessionTimeout, sessionTimeout, zkSerializer);
        this.clazz = clazz;
    }

    public void createEphemeral(String path,Object data){
        zkClient.createEphemeral(path,data);
    }

    /***
     * 支持创建递归方式
     * @param path
     * @param createParents
     */
    public void createPersistent(String path, boolean createParents){
        zkClient.createPersistent(path, createParents);
    }

    /***
     * 创建节点 跟上data数据
     * @param path
     * @param data
     */
    public void createPersistent(String path,Object data){
        zkClient.createPersistent(path,data);
    }

    public boolean exists(String path) {
        return zkClient.exists(path);
    }

    /***
     * 子节点
     * @param path
     * @return
     */
    public List<String> getChildren(String path){
        return zkClient.getChildren(path);
    }

    public T readData(String path){
        String data = zkClient.readData(path).toString();
        try {
            return mapper.readValue(data, clazz);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void writeData(String path, Object source){
        try {
            String data = mapper.writeValueAsString(source);
            zkClient.writeData(path, data);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    //递归删除
    public  void deleteRecursive(String path){
        zkClient.deleteRecursive(path);
    }

    public ZkClient getZkClient() {
        return zkClient;
    }
}
