package com.eddue.jms.core.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

import static java.lang.Long.MAX_VALUE;

/**
 * @author zzx
 * @Description 消息生产者
 * 参考http://kafka.apache.org/0110/documentation.html#api
 *
 * @company www.eddue.com
 * @since 2018/11/12 16:46
 *
 */
public class KafkaMessageSimpleProducerTest {
    public static void main(String[] args) {

        Producer<String, String> producer = null;
        try {
            Properties props = new Properties();
            /**
             * kafka服务器地址
             */
            props.put("bootstrap.servers", "192.168.0.239:9092,192.168.0.239:9093,192.168.0.239:9094");

            /**
             * acks对应的属性值又[all, -1, 0, 1]
             * acks=[all, -1]意味着leader要等待至少一个follower副本完整复制完成确认
             * acks=0意味着生产者不会等待，接收者确认，此时retries属性无效，生产者无法获知服务端错误
             * acks=1意味着leader副本确认完成，无须等待follower复制确认返回
             */
            props.put("acks", "1");

            /**
             * 发送可能错误的请求，重试次数
             */
            props.put("retries", 0);

            /**
             * 将数据批量处理，按照设置的默认字节大小
             * 数据超过默认批处理字节大小，将不会分批处理
             * 设置为0，不开启批处理
             */
            props.put("batch.size", 16384);

            /**
             *延时发送时间设置，当发送数据大于batch.size，会立即发送
             * 当数据小于batch.size，数据会根据linger.ms延时一定时间发送
             *
             */
            props.put("linger.ms", 0);

            /**
             * 生产者可以使用总字节的内存来缓冲等待发送到服务器的记录
             */
            props.put("buffer.memory", 33554432);

            /**
             * kafka对应key数据序列化方式
             */
            props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");

            /**
             * kafka对应value数据序列化方式
             */
            props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

            producer = new KafkaProducer<String, String>(props);
            String msg = "";
            for (int i = 0; i < 100; i++) {
                msg = "message " + i;
                producer.send(new ProducerRecord<String, String>("test2", msg));
                System.out.println("send:" + msg);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            producer.close();
        }

        try {
            Thread.sleep(MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
