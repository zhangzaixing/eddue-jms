package com.eddue.jms.core.producer;

import java.io.Serializable;

/**
 * @author zzx
 * @Description 队列消息发送接口
 * @company www.eddue.com
 * @since 2018/11/13 15:09
 */
public interface MessageProducer {
    /**
     *发送对象类型消息
     * @param message
     */
    void sendObject(Serializable message);

    /**
     *发送文本类型消息
     * @param message
     */
    void sendText(String message);

    /**
     *发送字节类型消息
     * @param message
     */
    void sendByte(byte[] message);
}
