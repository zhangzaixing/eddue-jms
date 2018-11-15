package com.eddue.jms.core.kafka.consumer;


import com.eddue.jms.core.consumer.IMessageConusmer;

/**
 * @author Cruise.Xu
 * 
 */
public abstract class BytesMessageConsumer extends BaseMessageConsumer implements IMessageConusmer<byte[]> {

	@Override
	public void processByteMessage(byte[] message) {
		processMessage(message);
	}
	
}