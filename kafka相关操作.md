### 启动Kafka服务

bin/kafka-server-start.sh config/server-1.properties &

### 停止Kafka服务

netstat -anp | grep 8088

kill -9 pid

本地测试发现无法生效：

/bin/kafka-server-stop.sh config/server.properties 

### 查看topic列表 

 bin/kafka-topics.sh --list --zookeeper 192.168.0.239:2181

### 查看某个topic详细信息

 bin/kafka-topics.sh --describe --zookeeper 192.168.0.239:2181 --topic test2

### 创建topic

bin/kafka-topics.sh --create --zookeeper 192.168.0.239:2181 --replication-factor 1 --partitions 1 --topic test

### 删除topic

bin/kafka-topics.sh --delete --topic test --zookeeper 192.168.0.239:2181

### 给topic发送消息

bin/kafka-console-producer.sh --broker-list 192.168.0.239:9092 --topic test

### 创建一个消费者

方式1：

bin/kafka-console-consumer.sh --bootstrap-server 192.168.0.239:9092 --topic test --from-beginning

方式2：

bin/kafka-console-consumer.sh --zookeeper 192.168.0.239:2181 --topic test2 --from-beginning

注意：方式1无法被kafka监控KafkaOffsetMonitor工具管理，方式2则可以

 ### 创建多个broker集群

  cp config/server.properties config/server-1.properties

  cp config/server.properties config/server-2.properties

修改下面几个配置：

  config/server.properties:

```properties
listeners=PLAINTEXT://192.168.0.239:9092
advertised.listeners=PLAINTEXT://192.168.0.239:9092
```

  config/server-1.properties:

```properties
broker.id=1
listeners=PLAINTEXT://192.168.0.239:9093
advertised.listeners=PLAINTEXT://192.168.0.239:9093
log.dirs=/tmp/kafka-logs-1
```

config/server-2.properties:

```properties
broker.id=2
listeners=PLAINTEXT://192.168.0.239:9094
advertised.listeners=PLAINTEXT://192.168.0.239:9092
log.dirs=/tmp/kafka-logs-2
```

### 创建3个复制,16个分区集群的topic

bin/kafka-topics.sh --create --zookeeper 192.168.0.239:2181 --replication-factor 3 --partitions 16 --topic my-replicated-topic

相关参考资料：

http://kafka.apache.org/

https://github.com/zhangzaixing/KafkaOffsetMonitor

