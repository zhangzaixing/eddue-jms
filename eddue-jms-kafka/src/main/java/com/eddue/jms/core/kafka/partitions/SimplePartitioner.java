package com.eddue.jms.core.kafka.partitions;

import com.eddue.jms.core.utils.RandomUtils;
import com.eddue.jms.core.utils.StringUtils;
import kafka.producer.Partitioner;
import kafka.utils.VerifiableProperties;


/**
 * @author cruise.xu
 * 
 */
@SuppressWarnings("deprecation")
public class SimplePartitioner implements Partitioner {

	public SimplePartitioner(VerifiableProperties props) {
	}

	@Override
	public int partition(Object key, int numPartitions) {
		if (StringUtils.isEmpty(key) || key.equals("-1")) {
			return RandomUtils.genRandom(numPartitions);
		} else {
			int partitionKey = key.hashCode();
			int partition = partitionKey % numPartitions;
			return Math.abs(partition);
		}
	}

}
