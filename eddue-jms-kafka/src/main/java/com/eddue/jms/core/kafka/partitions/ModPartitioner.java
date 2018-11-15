package com.eddue.jms.core.kafka.partitions;

import com.eddue.jms.core.utils.RandomUtils;
import kafka.producer.Partitioner;
import kafka.utils.VerifiableProperties;

/**
 * @author cruise.xu
 * 
 */
@SuppressWarnings("deprecation")
public class ModPartitioner implements Partitioner {

	public ModPartitioner(VerifiableProperties props) {
	}

	@Override
	public int partition(Object key, int numPartitions) {
		if (null == key) {
			return RandomUtils.genRandom(numPartitions);
		} else if (key instanceof String) {
			int partition = Integer.parseInt(key.toString()) % numPartitions;
			return Math.abs(partition);
		} else if (key instanceof Integer) {
			int partition = (Integer) key % numPartitions;
			return Math.abs(partition);
		} else if (key instanceof Long) {
			Long partition = (Long) key % numPartitions;
			return Math.abs(partition.intValue());
		} else {
			return RandomUtils.genRandom(numPartitions);
		}
	}

}
