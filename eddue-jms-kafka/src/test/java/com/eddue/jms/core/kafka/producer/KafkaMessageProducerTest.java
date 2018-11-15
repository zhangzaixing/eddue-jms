package com.eddue.jms.core.kafka.producer;


import com.eddue.jms.core.producer.MessageProducer;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/kafka-producer.xml" })
public class KafkaMessageProducerTest {

	private static Logger logger = Logger.getLogger(KafkaMessageSimpleProducerTest.class.getName());
    
	@Resource
	private MessageProducer messageProducer;

	@Test
	public void testAddOpinion1() {
		try {
			long t = System.currentTimeMillis();
			for (int i = 1; i <= 100; i++) {
				messageProducer.sendText("xxxxxxxxxxxxxxxxxxxxx " + i);
				Thread.sleep(1000);
			}
			System.out.println(System.currentTimeMillis() - t);
			logger.error("------------------");
			System.in.read();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
