package com.eddue.jms.core.kafka.producer;

import com.eddue.jms.core.producer.KeyMessageProducer;
import com.eddue.jms.core.utils.ByteUtils;
import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;

import java.io.Serializable;

/**
 * @author Cruise.Xu
 * 
 */
@SuppressWarnings("deprecation")
public class KeyKafkaMessageProducer implements KeyMessageProducer {

	private Producer<String, byte[]> producer;

	public void setProducer(Producer<String, byte[]> producer) {
		this.producer = producer;
	}
	
	@Override
	public void sendByte(String topic, String key, byte[] message) {
			KeyedMessage<String, byte[]> producerData 
				= new KeyedMessage<String, byte[]>(topic, key, message);
			producer.send(producerData);
	}

	@Override
	public void sendObject(String topic, String key, Serializable message) {
			KeyedMessage<String, byte[]> producerData 
				= new KeyedMessage<String, byte[]>(topic, key, ByteUtils.toBytes(message));
			producer.send(producerData);
	}

	@Override
	public void sendText(String topic, String key, String message) {
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
