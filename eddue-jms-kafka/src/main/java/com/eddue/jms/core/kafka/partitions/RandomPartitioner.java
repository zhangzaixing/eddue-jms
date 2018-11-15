package com.eddue.jms.core.kafka.partitions;

import com.eddue.jms.core.utils.RandomUtils;
import kafka.producer.Partitioner;
import kafka.utils.VerifiableProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author cruise.xu
 * 
 */
@SuppressWarnings("deprecation")
public class RandomPartitioner implements Partitioner {
	
	private final static Logger logger = LoggerFactory.getLogger(RandomPartitioner.class);

	public RandomPartitioner(VerifiableProperties props) {
	}

	@Override
	public int partition(Object key, int numPartitions) {
		int partition = RandomUtils.genRandom(numPartitions);
		if(logger.isDebugEnabled()) {
			logger.debug("The numPartitions = " + numPartitions + " and Random partition = " + partition);
		}
		return partition;
	}

}
