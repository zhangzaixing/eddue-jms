package com.eddue.jms.core.kafka.producer;


import com.eddue.jms.core.producer.MessageProducer;
import com.eddue.jms.core.producer.TopicMessageProducer;
import com.eddue.jms.core.utils.ByteUtils;
import com.eddue.jms.core.utils.StringUtils;
import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;

import java.io.Serializable;

/**
 * @author zzx
 * @Description 消息发送
 * @company www.eddue.com
 * @since 2018/11/14 09:36
 */
public class TopicKafkaMessageProducer implements TopicMessageProducer {

    private Producer<String, byte[]> producer;

    private String key = "-1";

    public void setProducer(Producer<String, byte[]> producer) {
        this.producer = producer;
    }

    public void setKey(String key) {
        if (StringUtils.isEmpty(key)) {
            this.key = null;
        } else {
            this.key = key;
        }
    }

    @Override
    public void sendByte(String topic, byte[] message) {
        KeyedMessage<String, byte[]> producerData
                = new KeyedMessage<String, byte[]>(topic, key, message);
        producer.send(producerData);
    }

    @Override
    public void sendObject(String topic, Serializable message) {
        KeyedMessage<String, byte[]> producerData
                = new KeyedMessage<String, byte[]>(topic, key, ByteUtils.toBytes(message));
        producer.send(producerData);

    }

    @Override
    public void sendText(String topic, String message) {
        KeyedMessage<String, byte[]> producerData
                = new KeyedMessage<String, byte[]>(topic, key, message.getBytes());
        producer.send(producerData);
    }

    public void destory() {
        try {
            producer.close();
        } catch (Exception e) {
        }
    }
}
