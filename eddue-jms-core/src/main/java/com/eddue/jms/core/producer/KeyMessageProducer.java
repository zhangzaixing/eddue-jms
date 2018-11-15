package com.eddue.jms.core.producer;

import java.io.Serializable;

/**
 * 队列消息发送接口,数据发送到指定的分区和topic
 *
 */
public interface KeyMessageProducer {

	/**
	 * 发送对象信息
	 * @param topic
	 * @param partitionsKey
	 * @param message
	 */
	public void sendObject(String topic, String partitionsKey, Serializable message);

	/**
	 * 发送字符串
	 * @param topic
	 * @param partitionsKey
	 * @param message
	 */
	public void sendText(String topic, String partitionsKey, String message);

	/**
	 * 发送比特流
	 * @param topic
	 * @param partitionsKey
	 * @param message
	 */
	public void sendByte(String topic, String partitionsKey, byte[] message);
	
}