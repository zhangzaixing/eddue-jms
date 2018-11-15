package com.eddue.jms.core.kafka.consumer;


import com.eddue.jms.core.consumer.AbstractMessageConusmer;
import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.message.MessageAndMetadata;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SuppressWarnings("deprecation")
public abstract class MessageAndMetadataConsumer extends AbstractMessageConusmer<MessageAndMetadata<byte[], byte[]>> {
		
	@Resource
	private ConsumerConfig consumerConfig;
	
	protected String topic;
    
	protected Integer partitionsNum;
	
	private ConsumerConnector connector;
	
	private ExecutorService executor;
			
	protected void init() {
		
		Map<String, Integer> topicCountMap = new HashMap<String, Integer>();

		connector = Consumer.createJavaConsumerConnector(consumerConfig);
		
		String[] topics = topic.split(",");
		for (int i = 0; i < topics.length; i++) {
			topicCountMap.put(topics[i], partitionsNum);
		}

		Map<String, List<KafkaStream<byte[], byte[]>>> topicMessageStreams
			= connector.createMessageStreams(topicCountMap);
		List<KafkaStream<byte[], byte[]>> streams = new ArrayList<KafkaStream<byte[], byte[]>>();
		for (int i = 0; i < topics.length; i++) {
			streams.addAll(topicMessageStreams.get(topics[i]));
		}

		executor = Executors.newFixedThreadPool(partitionsNum * topics.length);
	    for (final KafkaStream<byte[], byte[]> stream : streams) {
	    	executor.submit(new Runnable() {
				public void run() {
                    ConsumerIterator<byte[], byte[]> it = stream.iterator();
					while (it.hasNext()) {
						MessageAndMetadata<byte[], byte[]> message = it.next();
						processMessage(message);
					}
                }
            });
	    }
	    	    
	    Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {

	    	@Override
	    	public void run() {
	    		executor.shutdown();
	    	}
	    }));
	}	

	public void setConsumerConfig(ConsumerConfig consumerConfig) {
		this.consumerConfig = consumerConfig;
	}

	public void setTopic(String topic) {
		this.topic = topic.trim().replaceAll(" ", "");
	}

	public void setPartitionsNum(Integer partitionsNum) {
		this.partitionsNum = partitionsNum;
	}
	
	public void destroy() {
		if(null != connector) {
			connector.shutdown();
		}
		if (null != executor) {
			executor.shutdown();
		}
	}
	
}
