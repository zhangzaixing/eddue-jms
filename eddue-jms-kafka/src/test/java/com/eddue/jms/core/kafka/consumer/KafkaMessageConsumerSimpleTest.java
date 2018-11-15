package com.eddue.jms.core.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

import static java.lang.Long.MAX_VALUE;

/**
 * @author zzx
 * @Description kafka消费者
 * http://kafka.apache.org/0110/documentation.html#api
 * @company www.eddue.com
 * @since 2018/11/12 17:22
 * @see
 */
public class KafkaMessageConsumerSimpleTest {
    public static void main(String[] args) {
        try {
            Properties properties = new Properties();

            /**
             * kafka服务器地址
             */
            properties.put("bootstrap.servers", "192.168.0.239:9092,192.168.0.239:9093,192.168.0.239:9094");

            /**
             * kafka消费组id
             */
            properties.put("group.id", "group-2");

            /**
             * kafka消费的offset定期自动提交
             */
            properties.put("enable.auto.commit", "true");

            /**
             *kafka消费的offset定期自动提交对应的时间间隔
             */
            properties.put("auto.commit.interval.ms", 1000);

            /**
             *auto.offset.reset属性值对应[latest, earliest, none]
             *当kafka消费组上偏移量找不到，当前offerset设置为最早的offerset
             */
            properties.put("auto.offset.reset", "earliest");

            /**
             * 心跳kafka状态监测间隔时间
             */
            properties.put("session.timeout.ms", 30000);

            /**
             * kafka对应key数据反序列化方式
             */
            properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

            /**
             * kafka对应key数据反序列化方式
             */
            properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

            KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(properties);
            kafkaConsumer.subscribe(Arrays.asList("test2"));
            while (true) {
                ConsumerRecords<String, String> records = kafkaConsumer.poll(100);
                for (ConsumerRecord<String, String> record : records) {
                    System.out.printf("offset = %d, value = %s", record.offset(), record.value());
                    System.out.println();
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
