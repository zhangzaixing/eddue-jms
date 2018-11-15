package com.eddue.jms.core.kafka.consumer;


import org.apache.log4j.Logger;

public class KafkaMessageConsumer extends BytesMessageConsumer {

	private static Logger logger = Logger.getLogger(KafkaMessageConsumer.class.getName());

	@Override
	public void processMessage(byte[] message) {
		try {
			String object = new String(message);
			logger.error(object.toString());
			Thread.sleep(1000);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}
