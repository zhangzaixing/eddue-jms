package com.eddue.jms.core.producer;

import java.io.Serializable;

/**
 * 队列消息发送接口，数据发送到指定的topic
 *
 */
public interface TopicMessageProducer {

	/**
	 * 发送对象信息
	 * @param topic
	 * @param message
	 */
	public void sendObject(String topic, Serializable message) ;

	/**
	 * 发送字符串
	 * @param topic
	 * @param message
	 */
	public void sendText(String topic, String message) ;
	

	/**
	 * 发送比特流
	 * @param topic
	 * @param message
	 */
	public void sendByte(String topic, byte[] message);
	
}