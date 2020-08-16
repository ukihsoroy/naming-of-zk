# naming-of-zk

通过zk命名服务，维护服务端具体机器位置。实现客户端注册；


1. 服务器初始化后，注册到对应server集群；

```
servers --> service1 --> server1/server2
servers --> service2 --> server2/server3
```

2. client节点启动后，读取zk对应的server地址，缓存本地；创建监听，当节点变化后更新地址数据发起请求；
3. servers与zk之间维护心跳；
